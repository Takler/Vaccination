package hu.demo.vaccination.utility;

public class ReservationInit {

    private ReservationInit() {
    }

    public static final String RESERVATION_DROP_TABLE = "DROP TABLE IF EXISTS reservation";

    public static final String RESERVATION_INIT_TABLE = "CREATE TABLE reservation " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "patient_id INT NOT NULL, " +
            "center_id INT NOT NULL, " +
            "vaccine_id INT NOT NULL, " +
            "FOREIGN KEY (patient_id) REFERENCES patient(id), " +
            "FOREIGN KEY (center_id) REFERENCES center(id), " +
            "FOREIGN KEY (vaccine_id) REFERENCES vaccine(id))";
}