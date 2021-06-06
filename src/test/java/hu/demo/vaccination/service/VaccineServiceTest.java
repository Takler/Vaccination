package hu.demo.vaccination.service;

import hu.demo.vaccination.config.VaccineTestHelper;
import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.repository.VaccineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VaccineServiceTest {

    VaccineService vaccineService;
    @Mock
    VaccineRepository vaccineRepository;
    @Mock
    VaccinationService vaccinationService;

    List<Vaccine> allVaccines;

    @BeforeEach
    void init() {
        vaccineService = new VaccineService(vaccineRepository, vaccinationService);

        allVaccines = VaccineTestHelper.VACCINE_DATA;
    }

    @Test
    void test_getVaccineForPatient_notPregnantNotChronicAge30NotVaccinated() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(50)).thenReturn(Collections.emptyList());
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(50, "male", LocalDate.now().minusYears(30).minusDays(150), "1234", false, false);

        Assertions.assertEquals(allVaccines, vaccineService.getVaccineForPatient(patient));
    }

    @Test
    void test_getVaccineForPatient_isPregnantIsChronicAge17NotVaccinated() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(51)).thenReturn(Collections.emptyList());
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(51, "female", LocalDate.now().minusYears(17).minusDays(150), "1234", true, true);

        Assertions.assertEquals(VaccineTestHelper.VACCINE_FOR_YOUNG_STRICTEST, vaccineService.getVaccineForPatient(patient));
    }

    @Test
    void test_getVaccineForPatient_NotPregnantIsChronicAge65NotVaccinated() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(52)).thenReturn(Collections.emptyList());
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(52, "male", LocalDate.now().minusYears(65).minusDays(150), "1234", false, true);

        Assertions.assertEquals(VaccineTestHelper.VACCINE_FOR_CHRONIC_STRICT, vaccineService.getVaccineForPatient(patient));
    }

    @Test
    void test_getVaccineForPatient_IsPregnantNotChronicAge35NotVaccinated() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(53)).thenReturn(Collections.emptyList());
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(53, "female", LocalDate.now().minusYears(35).minusDays(150), "1234", true, false);

        Assertions.assertEquals(VaccineTestHelper.VACCINE_FOR_PREGNANT_STRICTER, vaccineService.getVaccineForPatient(patient));
    }

    @Test
    void test_getVaccineForPatient_NotPregnantNotChronicAge1000NotVaccinated() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(54)).thenReturn(Collections.emptyList());
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(54, "female", LocalDate.now().minusYears(1000).minusDays(150), "1234", false, false);

        Assertions.assertEquals(Collections.emptyList(), vaccineService.getVaccineForPatient(patient));
    }
}
