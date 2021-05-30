package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.repository.VaccinationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
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

    List<Patient> patients;
    List<Vaccination> vaccinations;
    List<Vaccine> vaccines;

    @BeforeEach
    public void init() {
        vaccinationService = new VaccinationService(vaccinationRepository, patientService, vaccineService);

        patients = new ArrayList<>();
        Patient patient1 = new Patient();
        patient1.setId(1);
        patient1.setGender("female");
        patient1.setDateOfBirth(LocalDate.now().minusYears(16).minusDays(5));
        patient1.setZipCode("1234");
        patient1.setPregnant(false);
        patient1.setUnderlyingMedicalCondition(false);
        patients.add(patient1);

        Patient patient2 = new Patient();
        patient2.setId(2);
        patient2.setGender("male");
        patient2.setDateOfBirth(LocalDate.now().minusYears(16).minusDays(150));
        patient2.setZipCode("2345");
        patient2.setPregnant(false);
        patient2.setUnderlyingMedicalCondition(false);
        patients.add(patient2);

        Patient patient3 = new Patient();
        patient3.setId(3);
        patient3.setGender("female");
        patient3.setDateOfBirth(LocalDate.now().minusYears(16).minusDays(300));
        patient3.setZipCode("3456");
        patient3.setPregnant(false);
        patient3.setUnderlyingMedicalCondition(false);
        patients.add(patient3);

        Patient patient4 = new Patient();
        patient4.setId(4);
        patient4.setGender("male");
        patient4.setDateOfBirth(LocalDate.now().minusYears(17).minusDays(125));
        patient4.setZipCode("4567");
        patient4.setPregnant(false);
        patient4.setUnderlyingMedicalCondition(true);
        patients.add(patient4);

        Patient patient5 = new Patient();
        patient5.setId(5);
        patient5.setGender("female");
        patient5.setDateOfBirth(LocalDate.now().minusYears(17).minusDays(356));
        patient5.setZipCode("5678");
        patient5.setPregnant(true);
        patient5.setUnderlyingMedicalCondition(false);
        patients.add(patient5);

        Patient patient6 = new Patient();
        patient6.setId(6);
        patient6.setGender("female");
        patient6.setDateOfBirth(LocalDate.now().minusYears(20).minusDays(50));
        patient6.setZipCode("1234");
        patient6.setPregnant(true);
        patient6.setUnderlyingMedicalCondition(false);
        patients.add(patient6);

        Patient patient7 = new Patient();
        patient7.setId(7);
        patient7.setGender("male");
        patient7.setDateOfBirth(LocalDate.now().minusYears(25).minusDays(85));
        patient7.setZipCode("6789");
        patient7.setPregnant(false);
        patient7.setUnderlyingMedicalCondition(false);
        patients.add(patient7);

        Patient patient8 = new Patient();
        patient8.setId(8);
        patient8.setGender("male");
        patient8.setDateOfBirth(LocalDate.now().minusYears(30).minusDays(258));
        patient8.setZipCode("7890");
        patient8.setPregnant(false);
        patient8.setUnderlyingMedicalCondition(true);
        patients.add(patient8);

        Patient patient9 = new Patient();
        patient9.setId(9);
        patient9.setGender("female");
        patient9.setDateOfBirth(LocalDate.now().minusYears(35).minusDays(182));
        patient9.setZipCode("8901");
        patient9.setPregnant(true);
        patient9.setUnderlyingMedicalCondition(false);
        patients.add(patient9);

        Patient patient10 = new Patient();
        patient10.setId(10);
        patient10.setGender("female");
        patient10.setDateOfBirth(LocalDate.now().minusYears(40).minusDays(71));
        patient10.setZipCode("1234");
        patient10.setPregnant(true);
        patient10.setUnderlyingMedicalCondition(true);
        patients.add(patient10);

        Patient patient11 = new Patient();
        patient11.setId(11);
        patient11.setGender("male");
        patient11.setDateOfBirth(LocalDate.now().minusYears(45).minusDays(5));
        patient11.setZipCode("9123");
        patient11.setPregnant(false);
        patient11.setUnderlyingMedicalCondition(true);
        patients.add(patient11);

        Patient patient12 = new Patient();
        patient12.setId(12);
        patient12.setGender("male");
        patient12.setDateOfBirth(LocalDate.now().minusYears(50).minusDays(86));
        patient12.setZipCode("2345");
        patient12.setPregnant(false);
        patient12.setUnderlyingMedicalCondition(false);
        patients.add(patient12);

        Patient patient13 = new Patient();
        patient13.setId(13);
        patient13.setGender("female");
        patient13.setDateOfBirth(LocalDate.now().minusYears(55).minusDays(164));
        patient13.setZipCode("3456");
        patient13.setPregnant(true);
        patient13.setUnderlyingMedicalCondition(false);
        patients.add(patient13);

        Patient patient14 = new Patient();
        patient14.setId(14);
        patient14.setGender("female");
        patient14.setDateOfBirth(LocalDate.now().minusYears(60).minusDays(58));
        patient14.setZipCode("4567");
        patient14.setPregnant(false);
        patient14.setUnderlyingMedicalCondition(true);
        patients.add(patient14);

        Patient patient15 = new Patient();
        patient15.setId(15);
        patient15.setGender("female");
        patient15.setDateOfBirth(LocalDate.now().minusYears(65).minusDays(134));
        patient15.setZipCode("1234");
        patient15.setPregnant(false);
        patient15.setUnderlyingMedicalCondition(false);
        patients.add(patient15);

        Patient patient16 = new Patient();
        patient16.setId(16);
        patient16.setGender("male");
        patient16.setDateOfBirth(LocalDate.now().minusYears(70).minusDays(254));
        patient16.setZipCode("5678");
        patient16.setPregnant(false);
        patient16.setUnderlyingMedicalCondition(true);
        patients.add(patient16);

        Patient patient17 = new Patient();
        patient17.setId(17);
        patient17.setGender("female");
        patient17.setDateOfBirth(LocalDate.now().minusYears(75).minusDays(244));
        patient17.setZipCode("6789");
        patient17.setPregnant(false);
        patient17.setUnderlyingMedicalCondition(true);
        patients.add(patient17);

        Patient patient18 = new Patient();
        patient18.setId(18);
        patient18.setGender("male");
        patient18.setDateOfBirth(LocalDate.now().minusYears(80).minusDays(95));
        patient18.setZipCode("2345");
        patient18.setPregnant(false);
        patient18.setUnderlyingMedicalCondition(false);
        patients.add(patient18);

        Patient patient19 = new Patient();
        patient19.setId(19);
        patient19.setGender("female");
        patient19.setDateOfBirth(LocalDate.now().minusYears(85).minusDays(220));
        patient19.setZipCode("8901");
        patient19.setPregnant(false);
        patient19.setUnderlyingMedicalCondition(true);
        patients.add(patient19);

        Patient patient20 = new Patient();
        patient20.setId(20);
        patient20.setGender("female");
        patient20.setDateOfBirth(LocalDate.now().minusYears(90).minusDays(128));
        patient20.setZipCode("1234");
        patient20.setPregnant(false);
        patient20.setUnderlyingMedicalCondition(false);
        patients.add(patient20);

        Patient patient21 = new Patient();
        patient21.setId(21);
        patient21.setGender("male");
        patient21.setDateOfBirth(LocalDate.now().minusYears(95).minusDays(1));
        patient21.setZipCode("2345");
        patient21.setPregnant(false);
        patient21.setUnderlyingMedicalCondition(true);
        patients.add(patient21);

        vaccinations = new ArrayList<>();
        vaccinations.add(new Vaccination(1, 1, 3, 1, LocalDate.now().minusDays(10), false));
        vaccinations.add(new Vaccination(2, 1, 4, 2, LocalDate.now().minusDays(9), false));
        vaccinations.add(new Vaccination(3, 1, 5, 3, LocalDate.now().minusDays(8), false));
        vaccinations.add(new Vaccination(4, 3, 6, 4, LocalDate.now().minusDays(7), false));
        vaccinations.add(new Vaccination(5, 4, 7, 5, LocalDate.now().minusDays(6), false));
        vaccinations.add(new Vaccination(6, 2, 8, 6, LocalDate.now().minusDays(5), false));
        vaccinations.add(new Vaccination(7, 2, 9, 1, LocalDate.now().minusDays(10), false));
        vaccinations.add(new Vaccination(8, 1, 10, 2, LocalDate.now().minusDays(9), false));
        vaccinations.add(new Vaccination(9, 2, 11, 2, LocalDate.now().minusDays(9), false));
        vaccinations.add(new Vaccination(10, 3, 12, 4, LocalDate.now().minusDays(7), false));
        vaccinations.add(new Vaccination(11, 3, 14, 5, LocalDate.now().minusDays(6), false));
        vaccinations.add(new Vaccination(12, 6, 15, 1, LocalDate.now().minusDays(10), false));
        vaccinations.add(new Vaccination(13, 1, 17, 2, LocalDate.now().minusDays(9), false));
        vaccinations.add(new Vaccination(14, 6, 18, 3, LocalDate.now().minusDays(8), false));
        vaccinations.add(new Vaccination(15, 7, 20, 5, LocalDate.now().minusDays(6), false));
        vaccinations.add(new Vaccination(16, 2, 21, 5, LocalDate.now().minusDays(6), false));

        vaccinations.add(new Vaccination(17, 1, 4, 6, LocalDate.now().minusDays(4), false));
        vaccinations.add(new Vaccination(18, 3, 6, 6, LocalDate.now().minusDays(4), false));
        vaccinations.add(new Vaccination(19, 5, 7, 7, LocalDate.now().minusDays(3), false));
        vaccinations.add(new Vaccination(20, 3, 14, 7, LocalDate.now().minusDays(3), false));
        vaccinations.add(new Vaccination(21, 1, 17, 8, LocalDate.now().minusDays(2), false));
        vaccinations.add(new Vaccination(22, 6, 18, 8, LocalDate.now().minusDays(2), false));
        vaccinations.add(new Vaccination(23, 2, 21, 8, LocalDate.now().minusDays(2), false));

        vaccines = new ArrayList<>();
        vaccines.add(new Vaccine(1, "Pfizer", "mRNA", -70, 16, 999,
                        2, 1, 28, 42, true, true, true));
        vaccines.add(new Vaccine(2, "Moderna", "mRNA", -20, 18, 999,
                        2, 2, 28, 42, true, true, true));
        vaccines.add(new Vaccine(3, "AstraZeneca", "adenovirus", 4, 18, 999,
                        2, 3, 84, 96, true, true, true));
        vaccines.add(new Vaccine(4, "Gamaleja 1st", "adenovirus", 4, 18, 999,
                        2, 5, 21, 35, true, false, false));
        vaccines.add(new Vaccine(5, "Gamaleja 2nd", "adenovirus", 4, 18, 999,
                        2, -1, 0, 14, true, false, false));
        vaccines.add(new Vaccine(6, "Sinopharm", "unactivated virus", 4, 18, 999,
                        2, 6, 28, 42, true, false, false));
        vaccines.add(new Vaccine(7, "Janssen", "adenovirus", 4, 18, 999,
                1, -1, 0, 14, true, false, true));
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

    @Test void getFullVaccinatedPercentageTest_defaultArgs() {
        Mockito.when(vaccinationRepository.getVaccinations()).thenReturn(vaccinations);
        Mockito.when(patientService.findAll()).thenReturn(patients);
        Mockito.when(vaccineService.findAll()).thenReturn(vaccines);
        double result = vaccinationService.getFullVaccinatedPercentage(0, 0, false, false);
        Assertions.assertEquals(38.1, result);
    }
}