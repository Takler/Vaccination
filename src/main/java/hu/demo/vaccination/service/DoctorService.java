package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
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

    public int updateDoctor(Doctor doctor) {
        return doctorRepository.updateDoctor(doctor);
    }

    public int deletedDoctor(int id) {
        return doctorRepository.deleteDoctor(id);
    }
}
