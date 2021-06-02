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
public class VaccineService implements CrudOperation<Vaccine, VaccineCreateData>, Nameable {

    private final VaccineRepository vaccineRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public List<Vaccine> findAll() {
        return vaccineRepository.getVaccines();
    }

    @Override
    public Vaccine getById(int id) {
        return vaccineRepository.getVaccine(id);
    }

    @Override
    public boolean save(VaccineCreateData data) {
        return vaccineRepository.createVaccine(data);
    }

    @Override
    public boolean update(int id, VaccineCreateData data) {
        return vaccineRepository.updateVaccine(id, data);
    }

    @Override
    public boolean delete(int id) {
        return vaccineRepository.deleteVaccine(id);
    }

    @Override
    public String getName(int id) {
        return vaccineRepository.getVaccine(id).getName();
    }
}
