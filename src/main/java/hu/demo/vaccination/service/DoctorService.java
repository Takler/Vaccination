package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorService implements CrudOperation {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor getById(int doctorId) {
        return doctorRepository.getById(doctorId);
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public boolean save(Object createData) {   // OK    //INSERT
        return doctorRepository.save((Doctor) createData);   // ? cast?
    }

    @Override  //...OK, id?
    public boolean update(int id, Object createData) {
        return false;
    }

    public boolean update(Doctor doctor) {   //OK
        return doctorRepository.update(doctor);
    }

    @Override  //OK
    public boolean delete(int id) {
        return doctorRepository.delete(id);
    }

    public List<Map<String, Object>> getDoctorslist() {
        return doctorRepository.getDoctorsList();
    }


}
