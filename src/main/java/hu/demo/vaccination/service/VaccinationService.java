package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.dto.VaccinationCreateData;
import hu.demo.vaccination.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class VaccinationService implements CrudOperation<Vaccination, VaccinationCreateData> {

    private final VaccinationRepository vaccinationRepository;
    private final PatientService patientService;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository, PatientService patientService) {
        this.vaccinationRepository = vaccinationRepository;
        this.patientService = patientService;
    }

    public double getFirstVaccinatedPercentage(int minAge, int maxAge, boolean chronic, boolean pregnant) {
        List<Patient> patients = patientService.findAll();

        if (chronic) {
            patients = chronicPatients(patients);
        }
        if (pregnant) {
            patients = pregnantPatients(patients);
        }
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
        List<Integer> filteredPatientIds = getPatientIds(patients);

        List<Vaccination> vaccinations = vaccinationRepository.getVaccinations();
        Set<Integer> firstVaccinatedPatients = vaccinations.stream()
                .mapToInt(Vaccination::getId)
                .collect(HashSet::new, HashSet::add, HashSet::addAll);

        Set<Integer> filteredRegisteredPatients = new HashSet<>(filteredPatientIds);
        Set<Integer> vaccinatedRegisteredPatients = new HashSet<>(firstVaccinatedPatients);
        vaccinatedRegisteredPatients.retainAll(filteredRegisteredPatients);

        return (double) vaccinatedRegisteredPatients.size() / (double) filteredRegisteredPatients.size();
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

    private List<Patient> chronicPatients(List<Patient> patients) {
        return patients.stream().filter(Patient::isUnderlyingMedicalCondition).collect(Collectors.toList());
    }

    private List<Patient> pregnantPatients(List<Patient> patients) {
        return patients.stream().filter(Patient::isPregnant).collect(Collectors.toList());
    }

    private List<Integer> getPatientIds(List<Patient> patients) {
        return patients.stream().mapToInt(Patient::getId).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

}
