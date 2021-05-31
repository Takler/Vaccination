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
    public Object getById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public boolean save(Object createData) {   //INSERT
        return false;
    }

    @Override  //...OK
    public boolean update(int id, Object createData) {
        return false;
    }

    @Override  //OK
    public boolean delete(int id) {
        return doctorRepository.delete(id);
    }


    public int createDoctor(Doctor doctor) {
        return doctorRepository.createDoctor(doctor);
    }

    public List<Map<String, Object>> getDoctorslist() {
        return doctorRepository.getDoctorsList();
    }

    public Doctor getDoctor(int id) {
        return doctorRepository.getDoctor(id);
    }

    public boolean update(Doctor doctor) {
        return doctorRepository.update(doctor);
    }


}
