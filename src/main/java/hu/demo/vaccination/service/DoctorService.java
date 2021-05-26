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

    public List<Map<String, Object>> getDoctorslist() {
       return doctorRepository.getDoctorsList();
    }

}
