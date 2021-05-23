package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.VaccineCreateData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VaccineService {
    public List<Vaccine> getVaccines() {
        return null;
    }

    public Vaccine getVaccine(int id) {
        return null;
    }

    public boolean createVaccine(VaccineCreateData data) {
        return false;
    }

    public boolean updateVaccine(int id, VaccineCreateData data) {
        return false;
    }

    public boolean deleteVaccine(int id) {
        return false;
    }
}
