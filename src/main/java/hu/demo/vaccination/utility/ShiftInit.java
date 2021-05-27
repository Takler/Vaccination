package hu.demo.vaccination.utility;

public class ShiftInit {

    public static String DROP_TABLE_SHIFT = "DROP TABLE IF EXISTS shift";

    public static String CREATE_TABLE_SHIFT = "CREATE TABLE IF NOT EXISTS service(id int PRIMARY KEY AUTO_INCREMENT, " +
            "center_id int NOT NULL, doctor_id int NOT NULL)";
}
