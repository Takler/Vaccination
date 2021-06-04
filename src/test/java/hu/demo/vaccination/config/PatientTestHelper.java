package hu.demo.vaccination.config;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.patient.PatientCreateData;

import java.sql.Date;
import java.time.LocalDate;

public class PatientTestHelper {

    private PatientTestHelper() {
    }

    public static final int PATIENT_1_ID = 748237274;
    public static final String PATIENT_1_FIRST_NAME = "Frigyes";
    public static final String PATIENT_1_LAST_NAME = "Csonka";
    public static final String PATIENT_1_MOTHERS_NAME = "Prohászka Adél";
    public static final String PATIENT_1_GENDER = "male";
    public static final LocalDate PATIENT_1_BIRTH_DATE = Date.valueOf("1980-01-22").toLocalDate();
    public static final String PATIENT_1_EMAIL = "frigyes.csonka@email.com";
    public static final String PATIENT_1_CITY = "Ács";
    public static final String PATIENT_1_ZIP_CODE = "2941";
    public static final String PATIENT_1_ADDRESS = "Munkácsy Mihály út 14.";
    public static final String PATIENT_1_TELEPHONE_NUMBER = "0634388544";
    public static final boolean PATIENT_1_PREGNANT = false;
    public static final boolean PATIENT_1_CHRONIC = false;
    public static final boolean PATIENT_1_DELETED = false;

    public static final int PATIENT_2_ID = 248248264;
    public static final String PATIENT_2_FIRST_NAME = "Virág";
    public static final String PATIENT_2_LAST_NAME = "Szakáts";
    public static final String PATIENT_2_MOTHERS_NAME = "Vörös Hermina";
    public static final String PATIENT_2_GENDER = "female";
    public static final LocalDate PATIENT_2_BIRTH_DATE = Date.valueOf("1970-06-11").toLocalDate();
    public static final String PATIENT_2_EMAIL = "virag70@email.com";
    public static final String PATIENT_2_CITY = "Budapest";
    public static final String PATIENT_2_ZIP_CODE = "1149";
    public static final String PATIENT_2_ADDRESS = "Árpád fejedelem útja 51.";
    public static final String PATIENT_2_TELEPHONE_NUMBER = "0618659140";
    public static final boolean PATIENT_2_PREGNANT = true;
    public static final boolean PATIENT_2_CHRONIC = true;
    public static final boolean PATIENT_2_DELETED = false;

    public static Patient getPatientOne() {
        return new Patient(PATIENT_1_ID, PATIENT_1_FIRST_NAME, PATIENT_1_LAST_NAME,
                PATIENT_1_MOTHERS_NAME, PATIENT_1_GENDER, PATIENT_1_BIRTH_DATE,
                PATIENT_1_EMAIL, PATIENT_1_CITY, PATIENT_1_ZIP_CODE, PATIENT_1_ADDRESS,
                PATIENT_1_TELEPHONE_NUMBER, PATIENT_1_PREGNANT, PATIENT_1_CHRONIC, PATIENT_1_DELETED);
    }

    public static Patient getPatientTwo() {
        return new Patient(PATIENT_2_ID, PATIENT_2_FIRST_NAME, PATIENT_2_LAST_NAME,
                PATIENT_2_MOTHERS_NAME, PATIENT_2_GENDER, PATIENT_2_BIRTH_DATE,
                PATIENT_2_EMAIL, PATIENT_2_CITY, PATIENT_2_ZIP_CODE, PATIENT_2_ADDRESS,
                PATIENT_2_TELEPHONE_NUMBER, PATIENT_2_PREGNANT, PATIENT_2_CHRONIC, PATIENT_2_DELETED);
    }

    public static PatientCreateData getPatientOneCreateData() {
        return new PatientCreateData(PATIENT_1_ID, PATIENT_1_FIRST_NAME, PATIENT_1_LAST_NAME,
                PATIENT_1_MOTHERS_NAME, PATIENT_1_GENDER, PATIENT_1_BIRTH_DATE, PATIENT_1_EMAIL,
                PATIENT_1_CITY, PATIENT_1_ZIP_CODE, PATIENT_1_ADDRESS, PATIENT_1_TELEPHONE_NUMBER,
                PATIENT_1_PREGNANT, PATIENT_1_CHRONIC);
    }

    public static PatientCreateData getPatientTwoCreateData() {
        return new PatientCreateData(PATIENT_2_ID, PATIENT_2_FIRST_NAME, PATIENT_2_LAST_NAME,
                PATIENT_2_MOTHERS_NAME, PATIENT_2_GENDER, PATIENT_2_BIRTH_DATE, PATIENT_2_EMAIL,
                PATIENT_2_CITY, PATIENT_2_ZIP_CODE, PATIENT_2_ADDRESS, PATIENT_2_TELEPHONE_NUMBER,
                PATIENT_2_PREGNANT, PATIENT_2_CHRONIC);
    }
}
