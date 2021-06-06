package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.dto.doctor.DoctorCreateUpdateData;
import hu.demo.vaccination.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements CrudOperation<Doctor, DoctorCreateUpdateData>, LastNameable {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getById(int doctorId) {
        return doctorRepository.getById(doctorId);
    }

    @Override
    public boolean save(DoctorCreateUpdateData doctorCreateUpdateData) {
        return doctorRepository.save(doctorCreateUpdateData);
    }

    @Override   // TODO Miért adjuk meg külön az id -t?
    public boolean update(int doctorId, DoctorCreateUpdateData doctorCreateUpdateData) {
        return doctorRepository.update(doctorId, doctorCreateUpdateData);
    }

    @Override
    public boolean delete(int doctorId) {
        return doctorRepository.delete(doctorId);
    }

    @Override
    public String getName(int doctorId) {
        return doctorRepository.getName(doctorId);
    }

    @Override
    public List<String> getLastName(String firstName) {
        return null;
    }
}
