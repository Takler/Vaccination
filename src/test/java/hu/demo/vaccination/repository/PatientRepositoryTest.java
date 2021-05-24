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
        jdbc.execute("DROP TABLE IF EXISTS patient");
        jdbc.execute("CREATE TABLE patient " +
                "(id INT PRIMARY KEY, " +
                "first_name VARCHAR(250) NOT NULL, " +
                "last_name VARCHAR(250) NOT NULL, " +
                "mothers_name VARCHAR(250) NOT NULL, " +
                "gender VARCHAR(50) NOT NULL, " +
                "date_of_birth DATE NOT NULL, " +
                "email VARCHAR(250) NOT NULL, " +
                "city VARCHAR(250) NOT NULL, " +
                "zip_code VARCHAR(20) NOT NULL, " +
                "address VARCHAR(250) NOT NULL, " +
                "telephone_number VARCHAR(250) NOT NULL, " +
                "pregnant BOOLEAN NOT NULL, " +
                "underlying_medical_condition BOOLEAN NOT NULL, " +
                "deleted BOOLEAN NOT NULL DEFAULT false);"
        );
    }

    @AfterEach
    void destruct() {
        jdbc.execute("DROP TABLE IF EXISTS patient");
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

        patientRepository.createPatient(data);

        Patient resultPatient = patientRepository.getPatient(PATIENT_1_ID);

        assertEquals(PATIENT_1_ID, resultPatient.getId());
        assertEquals(PATIENT_1_FIRST_NAME, resultPatient.getFirstName());
        assertEquals(PATIENT_1_LAST_NAME, resultPatient.getLastName());
        assertEquals(PATIENT_1_MOTHERS_NAME, resultPatient.getMothersName());
        assertEquals(PATIENT_1_GENDER, resultPatient.getGender());
        assertEquals(PATIENT_1_BIRTH_DATE, resultPatient.getDateOfBirth());
        assertEquals(PATIENT_1_EMAIL, resultPatient.getEmail());
        assertEquals(PATIENT_1_CITY, resultPatient.getCity());
        assertEquals(PATIENT_1_ZIP_CODE, resultPatient.getZipCode());
        assertEquals(PATIENT_1_ADDRESS, resultPatient.getAddress());
        assertEquals(PATIENT_1_TELEPHONE_NUMBER, resultPatient.getTelephoneNumber());
        assertEquals(PATIENT_1_PREGNANT, resultPatient.isPregnant());
        assertEquals(PATIENT_1_UNDERLYING_MEDICAL_CONDITION, resultPatient.isUnderlyingMedicalCondition());
    }

    @Test
    void test_createPatient_twoPatientsCreated_samePatientsDataReceived() {
        PatientCreateData firstData = getPatientOneCreateData();
        PatientCreateData secondData = getPatientTwoCreateData();

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

        assertEquals(PATIENT_1_ID, firstPatient.getId());
        assertEquals(PATIENT_1_FIRST_NAME, firstPatient.getFirstName());
        assertEquals(PATIENT_1_LAST_NAME, firstPatient.getLastName());
        assertEquals(PATIENT_1_MOTHERS_NAME, firstPatient.getMothersName());
        assertEquals(PATIENT_1_GENDER, firstPatient.getGender());
        assertEquals(PATIENT_1_BIRTH_DATE, firstPatient.getDateOfBirth());
        assertEquals(PATIENT_1_EMAIL, firstPatient.getEmail());
        assertEquals(PATIENT_1_CITY, firstPatient.getCity());
        assertEquals(PATIENT_1_ZIP_CODE, firstPatient.getZipCode());
        assertEquals(PATIENT_1_ADDRESS, firstPatient.getAddress());
        assertEquals(PATIENT_1_TELEPHONE_NUMBER, firstPatient.getTelephoneNumber());
        assertEquals(PATIENT_1_PREGNANT, firstPatient.isPregnant());
        assertEquals(PATIENT_1_UNDERLYING_MEDICAL_CONDITION, firstPatient.isUnderlyingMedicalCondition());

        assertEquals(PATIENT_2_ID, secondPatient.getId());
        assertEquals(PATIENT_2_FIRST_NAME, secondPatient.getFirstName());
        assertEquals(PATIENT_2_LAST_NAME, secondPatient.getLastName());
        assertEquals(PATIENT_2_MOTHERS_NAME, secondPatient.getMothersName());
        assertEquals(PATIENT_2_GENDER, secondPatient.getGender());
        assertEquals(PATIENT_2_BIRTH_DATE, secondPatient.getDateOfBirth());
        assertEquals(PATIENT_2_EMAIL, secondPatient.getEmail());
        assertEquals(PATIENT_2_CITY, secondPatient.getCity());
        assertEquals(PATIENT_2_ZIP_CODE, secondPatient.getZipCode());
        assertEquals(PATIENT_2_ADDRESS, secondPatient.getAddress());
        assertEquals(PATIENT_2_TELEPHONE_NUMBER, secondPatient.getTelephoneNumber());
        assertEquals(PATIENT_2_PREGNANT, secondPatient.isPregnant());
        assertEquals(PATIENT_2_UNDERLYING_MEDICAL_CONDITION, secondPatient.isUnderlyingMedicalCondition());
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
        PatientCreateData secondData = new PatientCreateData();
        secondData.setId(PATIENT_2_ID);
        secondData.setFirstName(PATIENT_2_FIRST_NAME);
        secondData.setLastName(PATIENT_2_LAST_NAME);
        secondData.setMothersName(PATIENT_2_MOTHERS_NAME);
        secondData.setGender(PATIENT_2_GENDER);
        secondData.setDateOfBirth(PATIENT_2_BIRTH_DATE);
        secondData.setEmail(PATIENT_2_EMAIL);
        secondData.setCity(PATIENT_2_CITY);
        secondData.setZipCode(PATIENT_2_ZIP_CODE);
        secondData.setAddress(PATIENT_2_ADDRESS);
        secondData.setTelephoneNumber(PATIENT_2_TELEPHONE_NUMBER);
        secondData.setPregnant(PATIENT_2_PREGNANT);
        secondData.setUnderlyingMedicalCondition(PATIENT_2_UNDERLYING_MEDICAL_CONDITION);
        return secondData;
    }
}
