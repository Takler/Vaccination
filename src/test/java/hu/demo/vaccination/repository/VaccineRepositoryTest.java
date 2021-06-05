package hu.demo.vaccination.repository;

import hu.demo.vaccination.config.VaccineTestHelper;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.VaccineCreateData;
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
    void createVaccine_success() {
        VaccineCreateData vaccineCreateData = new VaccineCreateData("BrandNewVaccine", "mRNA", 0,
                18, 65, 1, 35, -1, 49,
                true, false, false);
        Assertions.assertTrue(vaccineRepository.createVaccine(vaccineCreateData));
        Vaccine expectedVaccine = new Vaccine(8, "BrandNewVaccine", "mRNA", 0, 18, 65, 1, 35, -1, 49, true, false, false);
        Assertions.assertEquals(expectedVaccine, vaccineRepository.getVaccine(8));
        Assertions.assertEquals(expectedVaccine.getName(), vaccineRepository.getVaccine(8).getName());
        Assertions.assertEquals(expectedVaccine.getType(), vaccineRepository.getVaccine(8).getType());
        Assertions.assertEquals(expectedVaccine.getStorageTemperature(), vaccineRepository.getVaccine(8).getStorageTemperature());
        Assertions.assertEquals(expectedVaccine.getAgeLimitMin(), vaccineRepository.getVaccine(8).getAgeLimitMin());
        Assertions.assertEquals(expectedVaccine.getAgeLimitMax(), vaccineRepository.getVaccine(8).getAgeLimitMax());
        Assertions.assertEquals(expectedVaccine.getShotsNeeded(), vaccineRepository.getVaccine(8).getShotsNeeded());
        Assertions.assertEquals(expectedVaccine.getDaysUntilNextShot(), vaccineRepository.getVaccine(8).getDaysUntilNextShot());
        Assertions.assertEquals(expectedVaccine.getFullyVaccinatedTimePeriod(), vaccineRepository.getVaccine(8).getFullyVaccinatedTimePeriod());
        Assertions.assertEquals(expectedVaccine.isApplicable(), vaccineRepository.getVaccine(8).isApplicable());
        Assertions.assertEquals(expectedVaccine.isApplicableForPregnant(), vaccineRepository.getVaccine(8).isApplicableForPregnant());
        Assertions.assertEquals(expectedVaccine.isApplicableForChronic(), vaccineRepository.getVaccine(8).isApplicableForChronic());

    }

    @Test
    void updateVaccine_success() {
        VaccineCreateData vaccineCreateData = new VaccineCreateData("BrandNewVaccine", "mRNA", 0,
                18, 65, 1, 35, -1, 49,
                true, false, false);
        Vaccine updatedVaccine = new Vaccine(3, vaccineCreateData);
        Assertions.assertTrue(vaccineRepository.updateVaccine(3, vaccineCreateData));
        Assertions.assertEquals(updatedVaccine, vaccineRepository.getVaccine(3));
        Assertions.assertEquals(updatedVaccine.getName(), vaccineRepository.getVaccine(3).getName());
        Assertions.assertEquals(updatedVaccine.getType(), vaccineRepository.getVaccine(3).getType());
        Assertions.assertEquals(updatedVaccine.getStorageTemperature(), vaccineRepository.getVaccine(3).getStorageTemperature());
        Assertions.assertEquals(updatedVaccine.getAgeLimitMin(), vaccineRepository.getVaccine(3).getAgeLimitMin());
        Assertions.assertEquals(updatedVaccine.getAgeLimitMax(), vaccineRepository.getVaccine(3).getAgeLimitMax());
        Assertions.assertEquals(updatedVaccine.getShotsNeeded(), vaccineRepository.getVaccine(3).getShotsNeeded());
        Assertions.assertEquals(updatedVaccine.getDaysUntilNextShot(), vaccineRepository.getVaccine(3).getDaysUntilNextShot());
        Assertions.assertEquals(updatedVaccine.getFullyVaccinatedTimePeriod(), vaccineRepository.getVaccine(3).getFullyVaccinatedTimePeriod());
        Assertions.assertEquals(updatedVaccine.isApplicable(), vaccineRepository.getVaccine(3).isApplicable());
        Assertions.assertEquals(updatedVaccine.isApplicableForPregnant(), vaccineRepository.getVaccine(3).isApplicableForPregnant());
        Assertions.assertEquals(updatedVaccine.isApplicableForChronic(), vaccineRepository.getVaccine(3).isApplicableForChronic());
    }

    @Test
    void updateVaccine_fail() {
        VaccineCreateData vaccineCreateData = new VaccineCreateData("BrandNewVaccine", "mRNA", 0,
                18, 65, 1, 35, -1, 49,
                true, false, false);
        Assertions.assertFalse(vaccineRepository.updateVaccine(350, vaccineCreateData));
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
