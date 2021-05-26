package hu.demo.vaccination.service;

import hu.demo.vaccination.dto.DoctorCreate;
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

    public int createDoctor(DoctorCreate doctorCreate) {
        return doctorRepository.createDoctor(doctorCreate);
    }

    public List<Map<String, Object>> getDoctorslist() {
        return doctorRepository.getDoctorsList();
    }

    public List<DoctorCreate> getDoctor(int id) {
        return doctorRepository.getDoctor(id);
    }

    public int updateDoctor(DoctorCreate doctorCreate) {
        return doctorRepository.updateDoctor(doctorCreate);
    }

    public int deletedDoctor(int id) {
        return doctorRepository.deleteDoctor(id);
    }
}
