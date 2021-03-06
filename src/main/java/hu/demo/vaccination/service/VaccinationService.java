package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.*;
import hu.demo.vaccination.dto.vaccination.VaccinationCreateData;
import hu.demo.vaccination.dto.vaccination.AggregatedFieldData;
import hu.demo.vaccination.dto.vaccination.CountPercentageData;
import hu.demo.vaccination.dto.vaccination.VaccinationInfoData;
import hu.demo.vaccination.dto.vaccination.VaccinationNameInfoData;
import hu.demo.vaccination.repository.VaccinationRepository;
import hu.demo.vaccination.service.interfaces.InfoOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class VaccinationService implements InfoOperation<Vaccination, VaccinationCreateData, VaccinationInfoData, VaccinationNameInfoData> {

    private final VaccinationRepository vaccinationRepository;
    private final PatientService patientService;
    private final VaccineService vaccineService;
    private final ShiftService shiftService;
    private final CenterService centerService;
    private final DoctorService doctorService;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository, PatientService patientService,
                              VaccineService vaccineService, ShiftService shiftService, CenterService centerService,
                              DoctorService doctorService) {
        this.vaccinationRepository = vaccinationRepository;
        this.patientService = patientService;
        this.vaccineService = vaccineService;
        this.shiftService = shiftService;
        this.centerService = centerService;
        this.doctorService = doctorService;
    }

    public CountPercentageData getFirstVaccinatedData(int minAge, int maxAge, boolean chronic, boolean pregnant) {
        List<Patient> patients = patientService.findAll();

        patients = filterPatientsByAgeAndCondition(patients, minAge, maxAge, chronic, pregnant);
        List<Integer> filteredPatientIds = getPatientIds(patients);

        Set<Integer> filteredRegisteredPatients = new HashSet<>(filteredPatientIds);
        Set<Integer> vaccinatedRegisteredPatients = new HashSet<>(getVaccinatedPatientsIds());
        vaccinatedRegisteredPatients.retainAll(filteredRegisteredPatients);

        return new CountPercentageData(vaccinatedRegisteredPatients.size(),
                getPercentage(filteredRegisteredPatients.size(), vaccinatedRegisteredPatients.size()));

    }

    public CountPercentageData getFullVaccinatedData(int minAge, int maxAge, boolean chronic, boolean pregnant) {
        List<Integer> includedPatients = getPatientIds(filterPatientsByAgeAndCondition(patientService.findAll(),
                minAge, maxAge, chronic, pregnant));
        int numberOfFilteredPatients = includedPatients.size();

        List<Vaccination> vaccinations = vaccinationRepository.getVaccinations();

        Map<Integer, Integer> numberOfShotsNeededPerVaccine = vaccineService.findAll().stream()
                .collect(Collectors.toMap(
                        Vaccine::getId,
                        Vaccine::getShotsNeeded));
        Map<Integer, Long> countOfVaccinationsPerPatient = vaccinations.stream()
                .collect(Collectors.groupingBy(
                        Vaccination::getPatientId,
                        Collectors.counting()));
        Map<Integer, Integer> patientIdsVaccinesIds = vaccinations.stream()
                .filter(vaccination -> includedPatients.contains(vaccination.getPatientId()))
                .collect(Collectors.toMap(
                        Vaccination::getPatientId,
                        Vaccination::getVaccineId,
                        (patient1, patient2) -> patient1));

        int numberOfFullVaccinated = (int) patientIdsVaccinesIds.entrySet().stream()
                .filter(map -> countOfVaccinationsPerPatient.get(map.getKey()) >= numberOfShotsNeededPerVaccine.get(map.getValue()))
                .count();

        return new CountPercentageData(numberOfFullVaccinated, getPercentage(numberOfFilteredPatients, numberOfFullVaccinated));
    }

    public List<AggregatedFieldData> getVaccinatedPerVaccine() {
        List<Vaccination> vaccinations = vaccinationRepository.getVaccinations();
        Map<Integer, Long> countOfVaccinationsPerVaccine = vaccinations.stream()
                .collect(Collectors.toMap(
                        Vaccination::getPatientId,
                        Vaccination::getVaccineId,
                        (patient1, patient2) -> patient1))
                .entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.counting()));

        Map<Integer, String> vaccineIdName = vaccineService.findAll().stream()
                .collect(Collectors.toMap(
                        Vaccine::getId,
                        Vaccine::getName));

        return getAggregatedFieldDataList(countOfVaccinationsPerVaccine, vaccineIdName);
    }

    public CountPercentageData getStatOfVaccinationsForPeriod(LocalDate start, LocalDate end) {
        int numberOfVaccinationsForPeriod = (int) findAll().stream()
                .filter(vaccination -> vaccination.getDate().isAfter(start.minusDays(1)))
                .filter(vaccination -> vaccination.getDate().isBefore(end.plusDays(1)))
                .count();
        int totalNumberOfRegistered = patientService.findAll().size();
        return new CountPercentageData(numberOfVaccinationsForPeriod, getPercentage(totalNumberOfRegistered, numberOfVaccinationsForPeriod));
    }

    public List<Vaccination> getVaccinationsByPatient(int patientId) {
        return findAll().stream()
                .filter(vaccination -> vaccination.getPatientId() == patientId)
                .collect(Collectors.toList());
    }

    public List<VaccinationInfoData> getVaccinationInfoByPatient(int patientId) {
        return getVaccinationsByPatient(patientId).stream()
                .map(vaccination -> getInfo(vaccination.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public VaccinationInfoData getInfo(int id) {
        VaccinationInfoData vaccinationInfoData = new VaccinationInfoData();
        Vaccination vaccination = getById(id);
        vaccinationInfoData.setId(vaccination.getId());
        vaccinationInfoData.setPatient(patientService.getById(vaccination.getPatientId()));
        vaccinationInfoData.setVaccine(vaccineService.getById(vaccination.getVaccineId()));
        vaccinationInfoData.setShift(shiftService.getInfo(vaccination.getShiftId()));
        vaccinationInfoData.setDate(vaccination.getDate());
        vaccinationInfoData.setDeleted(vaccination.isDeleted());
        return vaccinationInfoData;
    }

    @Override
    public VaccinationNameInfoData getNameInfo(int id) {
        VaccinationNameInfoData vaccinationNameInfoData = new VaccinationNameInfoData();
        Vaccination vaccination = getById(id);
        vaccinationNameInfoData.setId(vaccination.getId());
        vaccinationNameInfoData.setVaccineName(vaccineService.getById(vaccination.getVaccineId()).getName());
        vaccinationNameInfoData.setPatientName(patientService.getName(vaccination.getPatientId()));
        vaccinationNameInfoData.setCenterName(centerService.getName(shiftService.getInfo(vaccination.getShiftId()).getCenter().getId()));
        Doctor doctor = doctorService.getById(shiftService.getInfo(vaccination.getShiftId()).getDoctor().getId());
        vaccinationNameInfoData.setDoctorName(doctor.getFirstName() + " " + doctor.getLastName());
        vaccinationNameInfoData.setDate(vaccination.getDate());
        vaccinationNameInfoData.setDeleted(vaccination.isDeleted());
        return vaccinationNameInfoData;
    }

    @Override
    public List<Vaccination> findAll() {
        return vaccinationRepository.getVaccinations();
    }

    @Override
    public Vaccination getById(int id) {
        return vaccinationRepository.getVaccination(id);
    }

    @Override
    public boolean save(VaccinationCreateData data) {
        if(validateData(data)) {
            return vaccinationRepository.createVaccination(data);
        } else {
            return false;
        }
    }

    @Override
    public boolean update(int id, VaccinationCreateData data) {
        if(validateData(data)) {
            return vaccinationRepository.updateVaccination(id, data);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        return vaccinationRepository.deleteVaccination(id);
    }

    private List<Patient> getChronicPatients(List<Patient> patients) {
        return patients.stream().filter(Patient::isUnderlyingMedicalCondition).collect(Collectors.toList());
    }

    private List<Patient> getPregnantPatients(List<Patient> patients) {
        return patients.stream().filter(Patient::isPregnant).collect(Collectors.toList());
    }

    private List<Integer> getPatientIds(List<Patient> patients) {
        return patients.stream().mapToInt(Patient::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private List<Patient> filterPatientsByAge(List<Patient> patients, int minAge, int maxAge) {
        if (minAge > 0) {
            patients = patients.stream()
                    .filter(patient -> patient.getDateOfBirth().isBefore(LocalDate.now().minusYears(minAge)))
                    .collect(Collectors.toList());
        }
        if (maxAge > 0) {
            patients = patients.stream()
                    .filter(patient -> patient.getDateOfBirth().isAfter(LocalDate.now().minusYears(maxAge)))
                    .collect(Collectors.toList());
        }
        return patients;
    }

    private List<Integer> getVaccinatedPatientsIds() {
        return vaccinationRepository.getVaccinations().stream()
                .mapToInt(Vaccination::getPatientId)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private List<Patient> filterPatientsByAgeAndCondition(List<Patient> patients, int minAge, int maxAge,
                                                          boolean chronic, boolean pregnant) {
        if (chronic) {
            patients = getChronicPatients(patients);
        }
        if (pregnant) {
            patients = getPregnantPatients(patients);
        }
        return filterPatientsByAge(patients, minAge, maxAge);
    }

    private List<AggregatedFieldData> getAggregatedFieldDataList(Map<Integer, Long> countPerEntity,
                                                                 Map<Integer, String> entityIdName) {
        int totalCount = countPerEntity.values().stream().reduce(0L, Long::sum).intValue();
        return countPerEntity.entrySet().stream()
                .map(map -> new AggregatedFieldData(entityIdName.get(map.getKey()),
                        new CountPercentageData(map.getValue().intValue(),
                                getPercentage(totalCount, countPerEntity.get(map.getKey()).intValue()))))
                .collect(Collectors.toList());
    }

    private boolean validateData(VaccinationCreateData data) {
        boolean isVaccineValid = vaccineService.findAll().stream()
                .filter(Vaccine::isApplicable)
                .mapToInt(Vaccine::getId)
                .boxed()
                .collect(Collectors.toList())
                .contains(data.getVaccineId());
        boolean isPatientValid = patientService.findAll().stream()
                .mapToInt(Patient::getId)
                .boxed()
                .collect(Collectors.toList())
                .contains(data.getPatientId());
        boolean isShiftValid = shiftService.findAll().stream()
                .mapToInt(Shift::getId)
                .boxed()
                .collect(Collectors.toList())
                .contains(data.getShiftId());
        boolean isDateValid = !data.getDate().isAfter(LocalDate.now());

        return isVaccineValid && isPatientValid && isShiftValid && isDateValid;
    }

    private double getPercentage(int totalCount, int partialCount) {
        return Math.round(partialCount * 10000 / (double) totalCount) / 100.0;
    }

}
