package hu.demo.vaccination.utility;

public class PatientInit {

    private PatientInit() {
    }

    public static final String PATIENT_DROP_TABLE = "DROP TABLE IF EXISTS patient";

    public static final String PATIENT_INIT_TABLE = "CREATE TABLE patient " +
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
            "underlying_medical_condition BOOLEAN NOT NULL, " +
            "pregnant BOOLEAN NOT NULL, " +
            "deleted BOOLEAN NOT NULL DEFAULT false);";

    public static final String PATIENT_SAMPLE_DATA_INSERT = "INSERT INTO patient VALUES " +
            "(157648531,'Albert','Magyar','Bodnár Gyöngyi','male','1979-09-16'," +
            "'magyar.a@email.com','Monori erdő','2213','Síp utca 38.','20207128918',1,0,0)," +
            "(175684569,'Péter','Pálinkás','Lukács Franciska','male','1985-10-03'," +
            "'p.peti@email.com','Budapest','1133','Csabai kapu 1.','0617530147',0,0,0)," +
            "(248248264,'Virág','Szakáts','Vörös Hermina','female','1970-06-11'," +
            "'virag70@email.com','Budapest','1149','Árpád fejedelem útja 51.','0618659140',1,0,0)," +
            "(748237274,'Frigyes','Csonka','Prohászka Adél','male','1980-01-22'," +
            "'frigyes.csonka@email.com','Ács','2941','Munkácsy Mihály út 14.','0634388544',0,0,0);";
}
