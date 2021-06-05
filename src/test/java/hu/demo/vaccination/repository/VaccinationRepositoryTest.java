package hu.demo.vaccination.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

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
    void Testingdataload() {

    }
}
