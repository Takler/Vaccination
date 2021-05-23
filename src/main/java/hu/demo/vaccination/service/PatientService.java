package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import hu.demo.vaccination.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.getPatients();
    }

    public Patient getPatient(int id) {
        return patientRepository.getPatient(id);
    }

    public boolean createPatient(PatientCreateData data) {
        int id = data.getId();
        if (patientRepository.isPatientDeleted(id)) {
            return patientRepository.unDeletePatient(id) && patientRepository.updatePatient(id, data);
        }
        return patientRepository.createPatient(data);
    }

    public boolean updatePatient(int id, PatientCreateData data) {
        return patientRepository.updatePatient(id, data);
    }

    public boolean deletePatient(int id) {
        return patientRepository.deletePatient(id);
    }
}
