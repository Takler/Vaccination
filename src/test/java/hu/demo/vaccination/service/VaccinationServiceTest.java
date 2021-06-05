package hu.demo.vaccination.service;

import hu.demo.vaccination.config.VaccinationTestHelper;
import hu.demo.vaccination.config.VaccineTestHelper;
import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.domain.Vaccine;
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

    @BeforeEach
    public void init() {
        vaccinationService = new VaccinationService(vaccinationRepository, patientService, vaccineService, shiftService,
                centerService, doctorService);

        patients = VaccinationTestHelper.PATIENT_DATA;
        vaccinations = VaccinationTestHelper.VACCINATION_DATA;
        vaccines = VaccineTestHelper.VACCINE_DATA;
    }

    @Test
    public void getFirstVaccinatedPercentageTest_defaultArgs() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(0, 0, false, false);
        Assertions.assertEquals(76.19, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_minAge60() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(60, 0, false, false);
        Assertions.assertEquals(75.0, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_maxAge40() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(0, 40, false, false);
        Assertions.assertEquals(77.78, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_chronicTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(0, 0, true, false);
        Assertions.assertEquals(77.78, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_pregnantTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(0, 0, false, true);
        Assertions.assertEquals(80.0, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_minAge20maxAge65() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(20, 65, false, false);
        Assertions.assertEquals(88.89, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_pregnantTrueChronicTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(0, 0, true, true);
        Assertions.assertEquals(100.0, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_minAge40ChronicTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(40, 0, true, false);
        Assertions.assertEquals(71.43, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_maxAge50PregnantTrue() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(0, 50, false, true);
        Assertions.assertEquals(100.0, result);
    }

    @Test
    public void getFirstVaccinatedPercentageTest_emptyResult() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        double result = vaccinationService.getFirstVaccinatedPercentage(150, 0, false, false);
        Assertions.assertEquals(0.0, result);
    }

    @Test
    void getFullVaccinatedPercentageTest_defaultArgs() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        double result = vaccinationService.getFullVaccinatedPercentage(0, 0, false, false);
        Assertions.assertEquals(38.1, result);
    }

    @Test
    void getVaccinatedByVaccine() {
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
    void getNumberOfVaccinationsForPeriod_thisWeek() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Assertions.assertEquals(14, vaccinationService.getNumberOfVaccinationsForPeriod(LocalDate.now().minusDays(7), LocalDate.now()));
    }

    @Test
    void getNumberOfVaccinationsForPeriod_twoDaysAgo() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Assertions.assertEquals(3, vaccinationService.getNumberOfVaccinationsForPeriod(LocalDate.now().minusDays(2), LocalDate.now().minusDays(2)));
    }

    @Test
    void getNumberOfVaccinationsForPeriod_wrongDate() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Assertions.assertEquals(0, vaccinationService.getNumberOfVaccinationsForPeriod(LocalDate.now().minusDays(5), LocalDate.now().minusDays(6)));
    }

    @Test
    void getNumberOfVaccinationsForPeriod_lastWeek() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Assertions.assertEquals(11, vaccinationService.getNumberOfVaccinationsForPeriod(LocalDate.now().minusDays(14), LocalDate.now().minusDays(7)));
    }
}