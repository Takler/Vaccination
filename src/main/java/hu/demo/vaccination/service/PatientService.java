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
public class PatientService implements CrudOperation<Patient, PatientCreateData>, HasLastName {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<String> getLastName(String firstName) {
        return patientRepository.getLastName(firstName);
    }

    @Override
    public String getName(int id) {
        return patientRepository.getName(id);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getById(int id) {
        return patientRepository.getById(id);
    }

    @Override
    public boolean save(PatientCreateData data) {
        return patientRepository.save(data);
    }

    @Override
    public boolean update(int id, PatientCreateData data) {
        return patientRepository.update(id, data);
    }

    @Override
    public boolean delete(int id) {
        return patientRepository.delete(id);
    }
}
