package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.VaccineCreateData;
import hu.demo.vaccination.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VaccineService implements CrudOperation<Vaccine, VaccineCreateData>, Nameable {

    private final VaccineRepository vaccineRepository;
    private final VaccinationService vaccinationService;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository, @Lazy VaccinationService vaccinationService) {
        this.vaccineRepository = vaccineRepository;
        this.vaccinationService = vaccinationService;
    }

    public List<Vaccine> getVaccineForPatient(Patient patient) {
        return null;
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

    private List<Vaccine> getAllApplicableVaccineForPatient(Patient patient) {
        List<Vaccine> vaccines = findAll().stream()
                .filter(Vaccine::isApplicable)
                .collect(Collectors.toList());
        vaccines = vaccines.stream()
                .filter(vaccine ->
                        LocalDate.now().minusYears(vaccine.getAgeLimitMin()).isBefore(patient.getDateOfBirth()) &&
                                LocalDate.now().minusYears(vaccine.getAgeLimitMax()).isAfter(patient.getDateOfBirth()))
                .filter(vaccine -> !patient.isPregnant() || vaccine.isApplicableForPregnant())
                .filter(vaccine -> !patient.isUnderlyingMedicalCondition() || vaccine.isApplicableForChronic())
                .collect(Collectors.toList());
        return vaccines;
    }
}
