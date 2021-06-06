package hu.demo.vaccination.service;

import hu.demo.vaccination.config.VaccineTestHelper;
import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccination;
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

    @Test
    void test_getVaccineForPatient_isPregnantIsChronicAge17IsVaccinatedOnce() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(55)).thenReturn(List.of(new Vaccination(100, 1, 55, 6, LocalDate.now().minusDays(20), false)));
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(55, "female", LocalDate.now().minusYears(17).minusDays(150), "1234", true, true);

        Assertions.assertEquals(List.of(new Vaccine(1, "Pfizer", "mRNA", -70, 16, 999,
                2, 28, 1, 42, true, true, true)), vaccineService.getVaccineForPatient(patient));
    }

    @Test
    void test_getVaccineForPatient_IsPregnantNotChronicAge35IsVaccinatedOnce() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(56)).thenReturn(List.of(new Vaccination(101, 3, 56, 7, LocalDate.now().minusDays(19), false)));
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(56, "female", LocalDate.now().minusYears(35).minusDays(150), "1234", true, false);

        Assertions.assertEquals(List.of(new Vaccine(3, "AstraZeneca", "adenovirus", 4, 18, 999,
                2, 84, 3, 96, true, true, true)), vaccineService.getVaccineForPatient(patient));
    }

    @Test
    void test_getVaccineForPatient_notPregnantNotChronicAge30IsVaccinatedOnceDifferentSecond() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(57)).thenReturn(List.of(new Vaccination(102, 4, 57, 8, LocalDate.now().minusDays(18), false)));
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(57, "male", LocalDate.now().minusYears(30).minusDays(150), "1234", false, false);

        Assertions.assertEquals(List.of(new Vaccine(5, "Gamaleja 2nd", "adenovirus", 4, 18, 999,
                2, 0, -1, 14, true, false, false)), vaccineService.getVaccineForPatient(patient));
    }

    @Test
    void test_getVaccineForPatient_notPregnantIsChronicAge45IsVaccinatedOnceAndFully() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(58)).thenReturn(List.of(new Vaccination(103, 7, 58, 8, LocalDate.now().minusDays(18), false)));
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(58, "male", LocalDate.now().minusYears(45).minusDays(150), "1234", false, true);

        Assertions.assertEquals(Collections.emptyList(), vaccineService.getVaccineForPatient(patient));
    }

    @Test
    void test_getVaccineForPatient_NotPregnantNotChronicAge50IsVaccinatedTwiceAndFully() {
        Mockito.when(vaccinationService.getVaccinationsByPatient(59)).thenReturn(List.of(new Vaccination(104, 1, 59, 5, LocalDate.now().minusDays(30), false),
                new Vaccination(105, 1, 59, 10, LocalDate.now().minusDays(5), false)));
        Mockito.when(vaccineService.findAll()).thenReturn(allVaccines);
        Patient patient = new Patient(59, "male", LocalDate.now().minusYears(50).minusDays(150), "1234", false, false);

        Assertions.assertEquals(Collections.emptyList(), vaccineService.getVaccineForPatient(patient));
    }
}
