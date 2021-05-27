package hu.demo.vaccination.utility;

public class InventoryInit {

    private InventoryInit() {
    }

    public static String INVENTORY_DROP_TABLE = "DROP TABLE IF EXISTS inventory";

    public static String INVENTORY_INIT_TABLE = "CREATE TABLE inventory " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "amount INTEGER, " +
            "deleted BOOLEAN NOT NULL DEFAULT FALSE, " +
            "FOREIGN KEY (center_id) REFERENCES center(id), " +
            "FOREIGN KEY (vaccine_id) REFERENCES vaccine(id))";
}
