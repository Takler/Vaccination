package hu.demo.vaccination.repository;

import hu.demo.vaccination.config.VaccineTestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@JdbcTest
public class VaccineRepositoryTest {

    VaccineRepository vaccineRepository;
    DataSource dataSource;

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:vaccine/vaccine-schema.sql")
                .addScript("classpath:vaccine/vaccine-test-data.sql")
                .build();

        vaccineRepository = new VaccineRepository(dataSource);
    }

    @AfterEach
    void destruct() {
        dataSource = null;
    }

    @Test
    void getVaccinesTest() {
        Assertions.assertEquals(VaccineTestHelper.VACCINE_DATA, vaccineRepository.getVaccines());
    }

    @Test
    void getVaccineById_success() {
        Assertions.assertEquals(VaccineTestHelper.VACCINE_DATA.get(0), vaccineRepository.getVaccine(1));
    }

    @Test
    void getVaccineById_fail() {
        Assertions.assertNull(vaccineRepository.getVaccine(564));
    }

    @Test
    void deleteVaccine_success() {
        Assertions.assertTrue(vaccineRepository.deleteVaccine(3));
        Assertions.assertFalse(vaccineRepository.getVaccine(3).isApplicable());
    }

    @Test
    void deleteVaccine_fail() {
        Assertions.assertFalse(vaccineRepository.deleteVaccine(897));
    }
}
