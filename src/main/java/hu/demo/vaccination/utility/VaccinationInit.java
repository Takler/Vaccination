package hu.demo.vaccination.utility;

public class VaccinationInit {

    private VaccinationInit() {
    }

    public static final String VACCINATION_DROP_TABLE = "DROP TABLE IF EXISTS vaccination";

    public static final String VACCINATION_INIT_TABLE = "CREATE TABLE vaccination " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "vaccine_id INT NOT NULL, " +
            "patient_id INT NOT NULL, " +
            "service_id INT NOT NULL, " +
            "FOREIGN KEY (patient_id) REFERENCES vaccine(id), " +
            "FOREIGN KEY (patient_id) REFERENCES patient(id), " +
            "FOREIGN KEY (service_id) REFERENCES service(id))";
}
