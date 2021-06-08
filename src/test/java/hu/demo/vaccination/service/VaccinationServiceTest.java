package hu.demo.vaccination.service;

import hu.demo.vaccination.config.VaccinationTestHelper;
import hu.demo.vaccination.config.VaccineTestHelper;
import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.vaccination.VaccinationCreateData;
import hu.demo.vaccination.dto.vaccination.AggregatedFieldData;
import hu.demo.vaccination.dto.vaccination.CountPercentageData;
import hu.demo.vaccination.repository.VaccinationRepository;
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
class VaccinationServiceTest {

    VaccinationService vaccinationService;
    @Mock
    VaccinationRepository vaccinationRepository;
    @Mock
    PatientService patientService;
    @Mock
    VaccineService vaccineService;
    @Mock
    ShiftService shiftService;
    @Mock
    CenterService centerService;
    @Mock
    DoctorService doctorService;

    List<Patient> patients;
    List<Vaccination> vaccinations;
    List<Vaccine> vaccines;
    List<Shift> shifts;

    @BeforeEach
    public void init() {
        vaccinationService = new VaccinationService(vaccinationRepository, patientService, vaccineService, shiftService,
                centerService, doctorService);

        patients = VaccinationTestHelper.PATIENT_DATA;
        vaccinations = VaccinationTestHelper.VACCINATION_DATA;
        vaccines = VaccineTestHelper.VACCINE_DATA;
        shifts = VaccinationTestHelper.SHIFT_DATA;
    }

    @Test
    public void test_getFirstVaccinatedData_defaultArgs() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(0, 0, false, false);
        Assertions.assertEquals(new CountPercentageData(16, 76.19), result);
    }

    @Test
    public void test_getFirstVaccinatedData_minAge60() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(60, 0, false, false);
        Assertions.assertEquals(new CountPercentageData(6, 75.0), result);
    }

    @Test
    public void test_getFirstVaccinatedData_maxAge40() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(0, 40, false, false);
        Assertions.assertEquals(new CountPercentageData(7, 77.78), result);
    }

    @Test
    public void test_getFirstVaccinatedData_chronicTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(0, 0, true, false);
        Assertions.assertEquals(new CountPercentageData(7, 77.78), result);
    }

    @Test
    public void test_getFirstVaccinatedData_pregnantTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(0, 0, false, true);
        Assertions.assertEquals(new CountPercentageData(4, 80.0), result);
    }

    @Test
    public void test_getFirstVaccinatedData_minAge20maxAge65() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(20, 65, false, false);
        Assertions.assertEquals(new CountPercentageData(8, 88.89), result);
    }

    @Test
    public void test_getFirstVaccinatedData_pregnantTrueChronicTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(0, 0, true, true);
        Assertions.assertEquals(new CountPercentageData(1, 100.0), result);
    }

    @Test
    public void test_getFirstVaccinatedData_minAge40ChronicTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(40, 0, true, false);
        Assertions.assertEquals(new CountPercentageData(5, 71.43), result);
    }

    @Test
    public void test_getFirstVaccinatedData_maxAge50PregnantTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(0, 50, false, true);
        Assertions.assertEquals(new CountPercentageData(4, 100.0), result);
    }

    @Test
    public void test_getFirstVaccinatedData_emptyResult() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        CountPercentageData result = vaccinationService.getFirstVaccinatedData(150, 0, false, false);
        Assertions.assertEquals(new CountPercentageData(0, 0.0), result);
    }

    @Test
    void test_getFullVaccinatedData_defaultArgs() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        CountPercentageData result = vaccinationService.getFullVaccinatedData(0, 0, false, false);
        Assertions.assertEquals(new CountPercentageData(8, 38.1), result);
    }

    @Test
    void test_getFullVaccinatedData_min20Max40Pregnant() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        CountPercentageData result = vaccinationService.getFullVaccinatedData(20, 40, false, true);
        Assertions.assertEquals(new CountPercentageData(1, 50.0), result);
    }

    @Test
    void test_getFullVaccinatedData_max30() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        CountPercentageData result = vaccinationService.getFullVaccinatedData(0, 30, false, false);
        Assertions.assertEquals(new CountPercentageData(3, 42.86), result);
    }

    @Test
    void test_getVaccinatedByVaccine() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        List<AggregatedFieldData> result = vaccinationService.getVaccinatedPerVaccine();
        Assertions.assertEquals(
                List.of(
                        new AggregatedFieldData("Pfizer", new CountPercentageData(5, 31.25)),
                        new AggregatedFieldData("Moderna", new CountPercentageData(4, 25.0)),
                        new AggregatedFieldData("AstraZeneca", new CountPercentageData(3, 18.75)),
                        new AggregatedFieldData("Gamaleja 1st", new CountPercentageData(1, 6.25)),
                        new AggregatedFieldData("Sinopharm", new CountPercentageData(2, 12.5)),
                        new AggregatedFieldData("Janssen", new CountPercentageData(1, 6.25))
                ), result);
    }

    @Test
    void test_getStatOfVaccinationsForPeriod_thisWeek() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Assertions.assertEquals(new CountPercentageData(14, 66.67), vaccinationService.getStatOfVaccinationsForPeriod(LocalDate.now().minusDays(7), LocalDate.now()));
    }

    @Test
    void test_getStatOfVaccinationsForPeriod_twoDaysAgo() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Assertions.assertEquals(new CountPercentageData(3, 14.29), vaccinationService.getStatOfVaccinationsForPeriod(LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
    }

    @Test
    void test_getStatOfVaccinationsForPeriod_wrongDate() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Assertions.assertEquals(new CountPercentageData(0, 0.0), vaccinationService.getStatOfVaccinationsForPeriod(LocalDate.now().minusDays(5), LocalDate.now().minusDays(6)));
    }

    @Test
    void test_getStatOfVaccinationsForPeriod_lastWeek() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Assertions.assertEquals(new CountPercentageData(11, 52.38), vaccinationService.getStatOfVaccinationsForPeriod(LocalDate.now().minusDays(14), LocalDate.now().minusDays(7)));
    }

    @Test
    void test_getVaccinationsByPatient_emptyList() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Assertions.assertEquals(Collections.emptyList(), vaccinationService.getVaccinationsByPatient(16));
    }

    @Test
    void test_getVaccinationsByPatient_oneResult() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        List<Vaccination> expected = List.of(new Vaccination(6,2,8,6, LocalDate.now().minusDays(5), false));
        Assertions.assertEquals(expected, vaccinationService.getVaccinationsByPatient(8));
    }

    @Test
    void test_getVaccinationsByPatient_twoResult() {
        List<Vaccination> expected = List.of(
                new Vaccination(11, 3, 14 , 5, LocalDate.now().minusDays(6), false),
                new Vaccination(20,3,14,7, LocalDate.now().minusDays(3), false));
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Assertions.assertEquals(expected, vaccinationService.getVaccinationsByPatient(14));
    }

    @Test
    void test_save_success() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(1, 5, 8, LocalDate.now().minusDays(2));
        Mockito.when(vaccinationRepository.createVaccination(vaccinationCreateData)).thenReturn(true);

        Assertions.assertTrue(vaccinationService.save(vaccinationCreateData));
    }

    @Test
    void test_save_vaccineIdFail() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(10, 5, 8, LocalDate.now().minusDays(2));

        Assertions.assertFalse(vaccinationService.save(vaccinationCreateData));
    }

    @Test
    void test_save_patientIdFail() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(1, 150, 8, LocalDate.now().minusDays(2));

        Assertions.assertFalse(vaccinationService.save(vaccinationCreateData));
    }

    @Test
    void test_save_shiftIdFail() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(1, 5, 128, LocalDate.now().minusDays(2));

        Assertions.assertFalse(vaccinationService.save(vaccinationCreateData));
    }

    @Test
    void test_save_dateFail() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(1, 5, 8, LocalDate.now().plusDays(1));

        Assertions.assertFalse(vaccinationService.save(vaccinationCreateData));
    }

    @Test
    void test_update_success() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(2, 5, 8, LocalDate.now().minusDays(2));
        Mockito.when(vaccinationRepository.updateVaccination(3, vaccinationCreateData)).thenReturn(true);

        Assertions.assertTrue(vaccinationService.update(3, vaccinationCreateData));
    }

    @Test
    void test_update_vaccineIdFail() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(102, 5, 8, LocalDate.now().minusDays(2));

        Assertions.assertFalse(vaccinationService.update(3, vaccinationCreateData));
    }

    @Test
    void test_update_patientIdFail() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(2, 105, 8, LocalDate.now().minusDays(2));

        Assertions.assertFalse(vaccinationService.update(3, vaccinationCreateData));
    }

    @Test
    void test_update_shiftIdFail() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(2, 5, 108, LocalDate.now().minusDays(2));

        Assertions.assertFalse(vaccinationService.update(3, vaccinationCreateData));
    }

    @Test
    void test_update_dateFail() {
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(shiftService.findAll()).thenReturn(shifts);
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(2, 5, 8, LocalDate.now().plusDays(1));

        Assertions.assertFalse(vaccinationService.update(3, vaccinationCreateData));
    }

    @Test
    void test_delete_success() {
        Mockito.when(vaccinationRepository.deleteVaccination(3)).thenReturn(true);
        Mockito.when(vaccinationRepository.getVaccination(3)).thenReturn(null);

        Assertions.assertTrue(vaccinationService.delete(3));
        Assertions.assertNull(vaccinationService.getById(3));
    }

    @Test
    void test_delete_fail() {
        Mockito.when(vaccinationRepository.deleteVaccination(100)).thenReturn(false);
        Mockito.when(vaccinationRepository.getVaccination(100)).thenReturn(null);

        Assertions.assertFalse(vaccinationService.delete(100));
        Assertions.assertNull(vaccinationService.getById(100));
    }
}