package hu.demo.vaccination.repository;

import hu.demo.vaccination.config.VaccinationTestHelper;
import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.dto.VaccinationCreateData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.time.LocalDate;

@JdbcTest
public class VaccinationRepositoryTest {

    VaccinationRepository vaccinationRepository;
    DataSource dataSource;

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:vaccination/vaccination-schema.sql")
                .addScript("classpath:vaccination/vaccination-test-data.sql")
                .build();

        vaccinationRepository = new VaccinationRepository(dataSource);
    }

    @Test
    void getVaccinationsTest() {
        Assertions.assertEquals(VaccinationTestHelper.VACCINATION_DATA, vaccinationRepository.getVaccinations());
    }

    @Test
    void getVaccinationTest_success() {
        Vaccination expected = new Vaccination(10, 3, 12, 4, LocalDate.of(2021, 5, 23), false);
        Assertions.assertEquals(expected, vaccinationRepository.getVaccination(10));
        Assertions.assertEquals(expected.getVaccineId(), vaccinationRepository.getVaccination(10).getVaccineId());
        Assertions.assertEquals(expected.getPatientId(), vaccinationRepository.getVaccination(10).getPatientId());
        Assertions.assertEquals(expected.getShiftId(), vaccinationRepository.getVaccination(10).getShiftId());
        Assertions.assertEquals(expected.getDate(), vaccinationRepository.getVaccination(10).getDate());
        Assertions.assertEquals(expected.isDeleted(), vaccinationRepository.getVaccination(10).isDeleted());
    }

    @Test
    void getVaccinationTest_fail() {
        Assertions.assertNull(vaccinationRepository.getVaccination(876));
    }

    @Test
    void createVaccinationTest_success() {
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(3, 20, 8, LocalDate.of(2021, 5, 28));
        Vaccination expected = new Vaccination(24, vaccinationCreateData);
        Assertions.assertTrue(vaccinationRepository.createVaccination(vaccinationCreateData));
        Assertions.assertEquals(expected, vaccinationRepository.getVaccination(24));
        Assertions.assertEquals(expected.getVaccineId(), vaccinationRepository.getVaccination(24).getVaccineId());
        Assertions.assertEquals(expected.getPatientId(), vaccinationRepository.getVaccination(24).getPatientId());
        Assertions.assertEquals(expected.getShiftId(), vaccinationRepository.getVaccination(24).getShiftId());
        Assertions.assertEquals(expected.getDate(), vaccinationRepository.getVaccination(24).getDate());
        Assertions.assertEquals(expected.isDeleted(), vaccinationRepository.getVaccination(24).isDeleted());
    }

    @Test
    void createVaccinationTest_fail() {
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(123, 20, 8, LocalDate.of(2021, 5, 28));
        Assertions.assertFalse(vaccinationRepository.createVaccination(vaccinationCreateData));
    }

    @Test
    void updateVaccinationTest_success() {
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(2, 20, 7, LocalDate.of(2021, 5, 27));
        Vaccination expected = new Vaccination(3, vaccinationCreateData);
        Assertions.assertTrue(vaccinationRepository.updateVaccination(3, vaccinationCreateData));
        Assertions.assertEquals(expected, vaccinationRepository.getVaccination(3));
        Assertions.assertEquals(expected.getVaccineId(), vaccinationRepository.getVaccination(3).getVaccineId());
        Assertions.assertEquals(expected.getPatientId(), vaccinationRepository.getVaccination(3).getPatientId());
        Assertions.assertEquals(expected.getShiftId(), vaccinationRepository.getVaccination(3).getShiftId());
        Assertions.assertEquals(expected.getDate(), vaccinationRepository.getVaccination(3).getDate());
        Assertions.assertEquals(expected.isDeleted(), vaccinationRepository.getVaccination(3).isDeleted());
    }

    @Test
    void updateVaccinationTest_idFail() {
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(2, 20, 7, LocalDate.of(2021, 5, 27));
        Assertions.assertFalse(vaccinationRepository.updateVaccination(987, vaccinationCreateData));
    }

    @Test
    void updateVaccinationTest_vaccineFail() {
        VaccinationCreateData vaccinationCreateData = new VaccinationCreateData(102, 20, 7, LocalDate.of(2021, 5, 27));
        Assertions.assertFalse(vaccinationRepository.updateVaccination(3, vaccinationCreateData));
    }

    @Test
    void deleteVaccinationTest_success() {
        Assertions.assertTrue(vaccinationRepository.deleteVaccination(5));
        Assertions.assertNull(vaccinationRepository.getVaccination(5));
    }

    @Test
    void deleteVaccinationTest_fail() {
        Assertions.assertFalse(vaccinationRepository.deleteVaccination(987));
    }
}
