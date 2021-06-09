package hu.demo.vaccination.repository;

import hu.demo.vaccination.dto.doctor.DoctorCreateUpdateData;
import hu.demo.vaccination.utility.DataDefinition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
public class DoctorRepositoryTest {


    @Autowired
    private JdbcTemplate jdbc;

    private DoctorRepository doctorRepository;

    @BeforeEach
    void init() {
        doctorRepository = new DoctorRepository(jdbc);
        jdbc.execute(DataDefinition.DOCTOR_DROP_TABLE.getDefinition());
        jdbc.execute(DataDefinition.DOCTOR_CREATE_TABLE.getDefinition());
    }

    @AfterEach
    void destruct() {
        jdbc.execute(DataDefinition.DOCTOR_DROP_TABLE.getDefinition());
    }

    @Test
    void test_getName_getCorrectName() {
        DoctorCreateUpdateData data = new DoctorCreateUpdateData();
        data.setFirstName("Jakab");
        data.setLastName("Test");
        data.setEmail("jakab.test@gmail.com");
        data.setAddress("1111, Budapest Test utca 1.");
        data.setTelephoneNumber("111 1111");

        String expectedName = data.getFirstName() + " " + data.getLastName() + " ";

        doctorRepository.save(data);

        assertEquals(expectedName, doctorRepository.getName(1));
    }
}
