package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.dto.VaccinationCreateData;
import hu.demo.vaccination.dto.VaccineCreateData;
import hu.demo.vaccination.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    public double getFirstVaccinatedPercentage(int minAge, int maxAge, boolean chronic, boolean pregnant) {
        return 0.0;
    }

    public List<Vaccination> getVaccinations() {
        return vaccinationRepository.getVaccinations();
    }

    public Vaccination getVaccination(int id) {
        return vaccinationRepository.getVaccination(id);
    }

    public boolean createVaccination(VaccinationCreateData data) {
        return vaccinationRepository.createVaccination(data);
    }

    public boolean updateVaccination(int id, VaccinationCreateData data) {
        return vaccinationRepository.updateVaccination(id, data);
    }

    public boolean deleteVaccination(int id) {
        return vaccinationRepository.deleteVaccination(id);
    }
}
