package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import hu.demo.vaccination.utility.PatientInit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
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
        jdbc.execute(PatientInit.PATIENT_DROP_TABLE);
        jdbc.execute(PatientInit.PATIENT_INIT_TABLE);
    }

    @AfterEach
    void destruct() {
        jdbc.execute(PatientInit.PATIENT_DROP_TABLE);
    }

    @Test
    void test_getPatients_noPatientsExists_returnsEmptyList() {
        assertEquals(0, patientRepository.getPatients().size());
    }

    @Test
    void test_getPatients_twoPatientsExists_returnsTwoElementList() {
        PatientCreateData firstData = getPatientOneCreateData();
        PatientCreateData secondData = getPatientTwoCreateData();

        patientRepository.createPatient(firstData);
        patientRepository.createPatient(secondData);

        assertEquals(2, patientRepository.getPatients().size());
    }

    @Test
    void test_createPatient_samePatientDataReceived() {
        PatientCreateData data = getPatientOneCreateData();
        Patient originalPatient = getPatientOne();

        patientRepository.createPatient(data);

        Patient resultPatient = patientRepository.getPatient(PATIENT_1_ID);

        assertEquals(originalPatient, resultPatient);
    }

    @Test
    void test_createPatient_twoPatientsCreated_samePatientsDataReceived() {
        PatientCreateData firstData = getPatientOneCreateData();
        PatientCreateData secondData = getPatientTwoCreateData();
        Patient originalFirstPatient = getPatientOne();
        Patient originalSecondPatient = getPatientTwo();


        patientRepository.createPatient(firstData);
        patientRepository.createPatient(secondData);

        List<Patient> patients = patientRepository.getPatients();
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
    void test_updatePatient_modifiedDataReceived() {
        final int MODIFIED_ID = 111111111;
        final String MODIFIED_TEST_TEXT = "modified";
        final Date MODIFIED_TEST_DATE = Date.valueOf("2000-01-01");
        final boolean MODIFIED_TEST_BOOLEAN = false;

        PatientCreateData data = getPatientTwoCreateData();

        PatientCreateData modifiedData = new PatientCreateData();
        data.setId(MODIFIED_ID);
        data.setFirstName(MODIFIED_TEST_TEXT);
        data.setLastName(MODIFIED_TEST_TEXT);
        data.setMothersName(MODIFIED_TEST_TEXT);
        data.setGender(MODIFIED_TEST_TEXT);
        data.setDateOfBirth(MODIFIED_TEST_DATE);
        data.setEmail(MODIFIED_TEST_TEXT);
        data.setCity(MODIFIED_TEST_TEXT);
        data.setZipCode(MODIFIED_TEST_TEXT);
        data.setAddress(MODIFIED_TEST_TEXT);
        data.setTelephoneNumber(MODIFIED_TEST_TEXT);
        data.setPregnant(MODIFIED_TEST_BOOLEAN);
        data.setUnderlyingMedicalCondition(MODIFIED_TEST_BOOLEAN);

        patientRepository.createPatient(data);

        patientRepository.updatePatient(PATIENT_2_ID, modifiedData);

        Patient modifiedPatient = patientRepository.getPatient(MODIFIED_ID);

        assertNull(patientRepository.getPatient(PATIENT_2_ID));
        assertEquals(MODIFIED_ID, modifiedPatient.getId());

        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getFirstName());
        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getLastName());
        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getMothersName());
        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getGender());
        assertEquals(MODIFIED_TEST_DATE, modifiedPatient.getDateOfBirth());
        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getEmail());
        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getCity());
        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getZipCode());
        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getAddress());
        assertEquals(MODIFIED_TEST_TEXT, modifiedPatient.getTelephoneNumber());
        assertEquals(MODIFIED_TEST_BOOLEAN, modifiedPatient.isPregnant());
        assertEquals(MODIFIED_TEST_BOOLEAN, modifiedPatient.isUnderlyingMedicalCondition());
    }

    @Test
    void test_deletePatient_deleteFieldModified_noResultFromGets() {
        PatientCreateData data = getPatientOneCreateData();

        patientRepository.createPatient(data);

        patientRepository.deletePatient(PATIENT_1_ID);

        assertEquals(0, patientRepository.getPatients().size());
    }

}
