package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.VaccineCreateData;
import hu.demo.vaccination.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VaccineService {

    private final VaccineRepository vaccineRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public List<Vaccine> getVaccines() {
        return vaccineRepository.getVaccines();
    }

    public Vaccine getVaccine(int id) {
        return vaccineRepository.getVaccine(id);
    }

    public boolean createVaccine(VaccineCreateData data) {
        return vaccineRepository.createVaccine(data);
    }

    public boolean updateVaccine(int id, VaccineCreateData data) {
        return vaccineRepository.updateVaccine(id, data);
    }

    public boolean deleteVaccine(int id) {
        return vaccineRepository.deleteVaccine(id);
    }
}
