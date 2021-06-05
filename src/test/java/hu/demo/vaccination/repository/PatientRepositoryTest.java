package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import hu.demo.vaccination.utility.DataDefinition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static hu.demo.vaccination.config.PatientTestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
class PatientRepositoryTest {

    @Autowired
    private JdbcTemplate jdbc;

    private PatientRepository patientRepository;

    @BeforeEach
    void init() {
        patientRepository = new PatientRepository(jdbc);
        jdbc.execute(DataDefinition.PATIENT_DROP_TABLE.getDefinition());
        jdbc.execute(DataDefinition.PATIENT_CREATE_TABLE.getDefinition());
    }

    @AfterEach
    void destruct() {
        jdbc.execute(DataDefinition.PATIENT_DROP_TABLE.getDefinition());
    }

    @Test
    void test_getName_getCorrectName() {
        PatientCreateData data = getPatientOneCreateData();
        String expectedName = data.getFirstName() + " " + data.getLastName();

        patientRepository.save(data);

        assertEquals(expectedName, patientRepository.getName(data.getId()));
    }

    @Test
    void test_getLastName_getCorrectNames() {
        PatientCreateData data = getPatientOneCreateData();

        patientRepository.save(data);

        assertEquals(data.getLastName(), patientRepository.getLastName(data.getFirstName()).get(0));
    }

    @Test
    void test_findAll_noPatientsExists_returnsEmptyList() {
        assertEquals(0, patientRepository.findAll().size());
    }

    @Test
    void test_findAll_twoPatientsExists_returnsTwoElementList() {
        PatientCreateData firstData = getPatientOneCreateData();
        PatientCreateData secondData = getPatientTwoCreateData();

        patientRepository.save(firstData);
        patientRepository.save(secondData);

        assertEquals(2, patientRepository.findAll().size());
    }

    @Test
    void test_save_samePatientDataReceived() {
        PatientCreateData data = getPatientOneCreateData();
        Patient originalPatient = getPatientOne();

        patientRepository.save(data);

        Patient resultPatient = patientRepository.getById(PATIENT_1_ID);

        assertEquals(originalPatient, resultPatient);
    }

    @Test
    void test_save_twoPatientsCreated_samePatientsDataReceived() {
        PatientCreateData firstData = getPatientOneCreateData();
        PatientCreateData secondData = getPatientTwoCreateData();
        Patient originalFirstPatient = getPatientOne();
        Patient originalSecondPatient = getPatientTwo();


        patientRepository.save(firstData);
        patientRepository.save(secondData);

        List<Patient> patients = patientRepository.findAll();
        Patient firstPatient;
        Patient secondPatient;

        if (patients.get(0).getId() == PATIENT_1_ID) {
            firstPatient = patients.get(0);
            secondPatient = patients.get(1);
        } else {
            firstPatient = patients.get(1);
            secondPatient = patients.get(0);
        }

        assertEquals(2, patients.size());

        assertEquals(originalFirstPatient, firstPatient);
        assertEquals(originalSecondPatient, secondPatient);
    }

    @Test
    void test_update_modifiedDataReceived() {
        final int MODIFIED_ID = 111111111;
        final String MODIFIED_TEST_TEXT = "modified";
        final LocalDate MODIFIED_TEST_DATE = Date.valueOf("2000-01-01").toLocalDate();
        final boolean MODIFIED_TEST_BOOLEAN = false;

        PatientCreateData data = getPatientTwoCreateData();

        PatientCreateData modifiedData = new PatientCreateData(MODIFIED_ID, MODIFIED_TEST_TEXT,
                MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT, MODIFIED_TEST_DATE,
                MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT,
                MODIFIED_TEST_TEXT, MODIFIED_TEST_BOOLEAN, MODIFIED_TEST_BOOLEAN);

        Patient exceptedModifiedPatient = new Patient(MODIFIED_ID, MODIFIED_TEST_TEXT,
                MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT, MODIFIED_TEST_DATE,
                MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT, MODIFIED_TEST_TEXT,
                MODIFIED_TEST_TEXT, MODIFIED_TEST_BOOLEAN, MODIFIED_TEST_BOOLEAN, false);


        patientRepository.save(data);

        patientRepository.update(PATIENT_2_ID, modifiedData);

        Patient modifiedPatient = patientRepository.getById(MODIFIED_ID);

        assertNull(patientRepository.getById(PATIENT_2_ID));
        assertEquals(exceptedModifiedPatient, modifiedPatient);
    }

    @Test
    void test_delete_deleteFieldModified_noResultFromGets() {
        PatientCreateData data = getPatientOneCreateData();

        patientRepository.save(data);

        patientRepository.delete(PATIENT_1_ID);

        assertEquals(0, patientRepository.findAll().size());
    }

}
