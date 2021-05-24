package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
class PatientRepositoryTest {

    @Autowired
    private JdbcTemplate jdbc;

    private PatientRepository patientRepository;

    private final int PATIENT_1_ID = 748237274;
    private final String PATIENT_1_FIRST_NAME = "Frigyes";
    private final String PATIENT_1_LAST_NAME = "Csonka";
    private final String PATIENT_1_MOTHERS_NAME = "Prohászka Adél";
    private final String PATIENT_1_GENDER = "male";
    private final Date PATIENT_1_BIRTH_DATE = Date.valueOf("1980-01-22");
    private final String PATIENT_1_EMAIL = "frigyes.csonka@email.com";
    private final String PATIENT_1_CITY = "Ács";
    private final String PATIENT_1_ZIP_CODE = "2941";
    private final String PATIENT_1_ADDRESS = "Munkácsy Mihály út 14.";
    private final String PATIENT_1_TELEPHONE_NUMBER = "0634388544";
    private final boolean PATIENT_1_PREGNANT = false;
    private final boolean PATIENT_1_UNDERLYING_MEDICAL_CONDITION = false;

    private final int PATIENT_2_ID = 248248264;
    private final String PATIENT_2_FIRST_NAME = "Virág";
    private final String PATIENT_2_LAST_NAME = "Szakáts";
    private final String PATIENT_2_MOTHERS_NAME = "Vörös Hermina";
    private final String PATIENT_2_GENDER = "female";
    private final Date PATIENT_2_BIRTH_DATE = Date.valueOf("1970-06-11");
    private final String PATIENT_2_EMAIL = "virag70@email.com";
    private final String PATIENT_2_CITY = "Budapest";
    private final String PATIENT_2_ZIP_CODE = "1149";
    private final String PATIENT_2_ADDRESS = "Árpád fejedelem útja 51.";
    private final String PATIENT_2_TELEPHONE_NUMBER = "0618659140";
    private final boolean PATIENT_2_PREGNANT = true;
    private final boolean PATIENT_2_UNDERLYING_MEDICAL_CONDITION = true;

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

    private PatientCreateData getPatientOneCreateData() {
        PatientCreateData data = new PatientCreateData();
        data.setId(PATIENT_1_ID);
        data.setFirstName(PATIENT_1_FIRST_NAME);
        data.setLastName(PATIENT_1_LAST_NAME);
        data.setMothersName(PATIENT_1_MOTHERS_NAME);
        data.setGender(PATIENT_1_GENDER);
        data.setDateOfBirth(PATIENT_1_BIRTH_DATE);
        data.setEmail(PATIENT_1_EMAIL);
        data.setCity(PATIENT_1_CITY);
        data.setZipCode(PATIENT_1_ZIP_CODE);
        data.setAddress(PATIENT_1_ADDRESS);
        data.setTelephoneNumber(PATIENT_1_TELEPHONE_NUMBER);
        data.setPregnant(PATIENT_1_PREGNANT);
        data.setUnderlyingMedicalCondition(PATIENT_1_UNDERLYING_MEDICAL_CONDITION);
        return data;
    }

    private PatientCreateData getPatientTwoCreateData() {
        PatientCreateData data = new PatientCreateData();
        data.setId(PATIENT_2_ID);
        data.setFirstName(PATIENT_2_FIRST_NAME);
        data.setLastName(PATIENT_2_LAST_NAME);
        data.setMothersName(PATIENT_2_MOTHERS_NAME);
        data.setGender(PATIENT_2_GENDER);
        data.setDateOfBirth(PATIENT_2_BIRTH_DATE);
        data.setEmail(PATIENT_2_EMAIL);
        data.setCity(PATIENT_2_CITY);
        data.setZipCode(PATIENT_2_ZIP_CODE);
        data.setAddress(PATIENT_2_ADDRESS);
        data.setTelephoneNumber(PATIENT_2_TELEPHONE_NUMBER);
        data.setPregnant(PATIENT_2_PREGNANT);
        data.setUnderlyingMedicalCondition(PATIENT_2_UNDERLYING_MEDICAL_CONDITION);
        return data;
    }

    private Patient getPatientOne() {
        Patient patient = new Patient();
        patient.setId(PATIENT_1_ID);
        patient.setFirstName(PATIENT_1_FIRST_NAME);
        patient.setLastName(PATIENT_1_LAST_NAME);
        patient.setMothersName(PATIENT_1_MOTHERS_NAME);
        patient.setGender(PATIENT_1_GENDER);
        patient.setDateOfBirth(PATIENT_1_BIRTH_DATE);
        patient.setEmail(PATIENT_1_EMAIL);
        patient.setCity(PATIENT_1_CITY);
        patient.setZipCode(PATIENT_1_ZIP_CODE);
        patient.setAddress(PATIENT_1_ADDRESS);
        patient.setTelephoneNumber(PATIENT_1_TELEPHONE_NUMBER);
        patient.setPregnant(PATIENT_1_PREGNANT);
        patient.setUnderlyingMedicalCondition(PATIENT_1_UNDERLYING_MEDICAL_CONDITION);
        return patient;
    }

    private Patient getPatientTwo() {
        Patient patient = new Patient();
        patient.setId(PATIENT_2_ID);
        patient.setFirstName(PATIENT_2_FIRST_NAME);
        patient.setLastName(PATIENT_2_LAST_NAME);
        patient.setMothersName(PATIENT_2_MOTHERS_NAME);
        patient.setGender(PATIENT_2_GENDER);
        patient.setDateOfBirth(PATIENT_2_BIRTH_DATE);
        patient.setEmail(PATIENT_2_EMAIL);
        patient.setCity(PATIENT_2_CITY);
        patient.setZipCode(PATIENT_2_ZIP_CODE);
        patient.setAddress(PATIENT_2_ADDRESS);
        patient.setTelephoneNumber(PATIENT_2_TELEPHONE_NUMBER);
        patient.setPregnant(PATIENT_2_PREGNANT);
        patient.setUnderlyingMedicalCondition(PATIENT_2_UNDERLYING_MEDICAL_CONDITION);
        return patient;
    }
}
