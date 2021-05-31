package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.VaccinationCreateData;
import hu.demo.vaccination.dto.vaccination.AggregatedFieldData;
import hu.demo.vaccination.dto.vaccination.CountPercentageData;
import hu.demo.vaccination.dto.vaccination.VaccinationInfoData;
import hu.demo.vaccination.dto.vaccination.VaccinationNameInfoData;
import hu.demo.vaccination.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class VaccinationService implements CrudOperation<Vaccination, VaccinationCreateData>, InfoOperation<VaccinationInfoData, VaccinationNameInfoData> {

    private final VaccinationRepository vaccinationRepository;
    private final PatientService patientService;
    private final VaccineService vaccineService;
    private final ShiftService shiftService;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository, PatientService patientService,
                              VaccineService vaccineService, ShiftService shiftService) {
        this.vaccinationRepository = vaccinationRepository;
        this.patientService = patientService;
        this.vaccineService = vaccineService;
        this.shiftService = shiftService;
    }

    public double getFirstVaccinatedPercentage(int minAge, int maxAge, boolean chronic, boolean pregnant) {
        List<Patient> patients = patientService.findAll();

        patients = filterPatientsByAgeAndCondition(patients, minAge, maxAge, chronic, pregnant);
        List<Integer> filteredPatientIds = getPatientIds(patients);

        Set<Integer> filteredRegisteredPatients = new HashSet<>(filteredPatientIds);
        Set<Integer> vaccinatedRegisteredPatients = new HashSet<>(getVaccinatedPatientsIds());
        vaccinatedRegisteredPatients.retainAll(filteredRegisteredPatients);

        try {
            return Math.round(vaccinatedRegisteredPatients.size() * 10000 / (double) filteredRegisteredPatients.size()) / 100.0;
        } catch (ArithmeticException e) {
            return 0.0;
        }
    }

    public double getFullVaccinatedPercentage(int minAge, int maxAge, boolean chronic, boolean pregnant) {
        int numberOfFilteredPatients = getPatientIds(filterPatientsByAgeAndCondition(patientService.findAll(),
                minAge, maxAge, chronic, pregnant)).size();
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
                .collect(Collectors.toMap(
                        Vaccination::getPatientId,
                        Vaccination::getVaccineId,
                        (patient1, patient2) -> patient1));

        int numberOfFullVaccinated = (int) patientIdsVaccinesIds.entrySet().stream()
                .filter(map -> countOfVaccinationsPerPatient.get(map.getKey()) >= numberOfShotsNeededPerVaccine.get(map.getValue()))
                .count();

        try {
            return Math.round(numberOfFullVaccinated * 10000 / (double) numberOfFilteredPatients) / 100.0;
        } catch (ArithmeticException e) {
            return 0.0;
        }
    }

    public List<AggregatedFieldData> getVaccinatedByVaccine() {
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

        int totalCountVaccinated = countOfVaccinationsPerVaccine.values().stream().reduce(0L, Long::sum).intValue();

        Map<Integer, String> vaccineIdName = vaccineService.findAll().stream()
                .collect(Collectors.toMap(
                        Vaccine::getId,
                        Vaccine::getName
                ));

        List<AggregatedFieldData> result = countOfVaccinationsPerVaccine.entrySet().stream()
                .map(map -> new AggregatedFieldData(vaccineIdName.get(map.getKey()),
                        new CountPercentageData(map.getValue().intValue(),
                                Math.round(countOfVaccinationsPerVaccine.get(map.getKey()) * 10000 / (double) totalCountVaccinated) / 100.0)))
                .collect(Collectors.toList());

        return result;
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
        return vaccinationRepository.createVaccination(data);
    }

    @Override
    public boolean update(int id, VaccinationCreateData data) {
        return vaccinationRepository.updateVaccination(id, data);
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

    @Override
    public VaccinationInfoData getInfo(int id) {
        VaccinationInfoData vaccinationInfoData = new VaccinationInfoData();
        Vaccination vaccination = getById(id);
        vaccinationInfoData.setId(vaccination.getId());
        vaccinationInfoData.setVaccine(vaccineService.getById(vaccination.getVaccineId()));
        vaccinationInfoData.setShift(shiftService.getInfo(vaccination.getShiftId()));
        vaccinationInfoData.setDate(vaccination.getDate());
        vaccinationInfoData.setDeleted(vaccination.isDeleted());
        return vaccinationInfoData;
    }

    @Override
    public VaccinationNameInfoData getNameInfo(int id) {
        return null;
    }


}
