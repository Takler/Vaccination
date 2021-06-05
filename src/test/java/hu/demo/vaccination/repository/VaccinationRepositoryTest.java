package hu.demo.vaccination.repository;

import hu.demo.vaccination.config.VaccinationTestHelper;
import hu.demo.vaccination.domain.Vaccination;
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
    void deleteVaccinationTest_success() {
        Assertions.assertTrue(vaccinationRepository.deleteVaccination(5));
        Assertions.assertNull(vaccinationRepository.getVaccination(5));
    }

    @Test
    void deleteVaccinationTest_fail() {
        Assertions.assertFalse(vaccinationRepository.deleteVaccination(987));
    }
}
