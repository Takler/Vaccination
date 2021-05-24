package hu.demo.vaccination.config;

import java.sql.Date;

public class PatientTestHelper {

    private PatientTestHelper() {
    }

    public static final int PATIENT_1_ID = 748237274;
    public static final String PATIENT_1_FIRST_NAME = "Frigyes";
    public static final String PATIENT_1_LAST_NAME = "Csonka";
    public static final String PATIENT_1_MOTHERS_NAME = "Prohászka Adél";
    public static final String PATIENT_1_GENDER = "male";
    public static final Date PATIENT_1_BIRTH_DATE = Date.valueOf("1980-01-22");
    public static final String PATIENT_1_EMAIL = "frigyes.csonka@email.com";
    public static final String PATIENT_1_CITY = "Ács";
    public static final String PATIENT_1_ZIP_CODE = "2941";
    public static final String PATIENT_1_ADDRESS = "Munkácsy Mihály út 14.";
    public static final String PATIENT_1_TELEPHONE_NUMBER = "0634388544";
    public static final boolean PATIENT_1_PREGNANT = false;
    public static final boolean PATIENT_1_UNDERLYING_MEDICAL_CONDITION = false;

    public static final int PATIENT_2_ID = 248248264;
    public static final String PATIENT_2_FIRST_NAME = "Virág";
    public static final String PATIENT_2_LAST_NAME = "Szakáts";
    public static final String PATIENT_2_MOTHERS_NAME = "Vörös Hermina";
    public static final String PATIENT_2_GENDER = "female";
    public static final Date PATIENT_2_BIRTH_DATE = Date.valueOf("1970-06-11");
    public static final String PATIENT_2_EMAIL = "virag70@email.com";
    public static final String PATIENT_2_CITY = "Budapest";
    public static final String PATIENT_2_ZIP_CODE = "1149";
    public static final String PATIENT_2_ADDRESS = "Árpád fejedelem útja 51.";
    public static final String PATIENT_2_TELEPHONE_NUMBER = "0618659140";
    public static final boolean PATIENT_2_PREGNANT = true;
    public static final boolean PATIENT_2_UNDERLYING_MEDICAL_CONDITION = true;
}
