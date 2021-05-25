package hu.demo.vaccination.utility;

public class CenterInit {
    public CenterInit() {
    }

    public static String CENTER_DROP_TABLE = "DROP TABLE IF EXISTS center";

    public static String CENTER_INIT_TABLE = "CREATE TABLE center " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(250) NOT NULL, " +
            "city VARCHAR(250) NOT NULL, " +
            "email VARCHAR(250) NOT NULL, " +
            "telephone_number VARCHAR(50) NOT NULL, " +
            "daily_capacity INT NOT NULL)";

    public static String CENTER_SAMPLE_DATA_INSERT = "INSERT INTO center VALUES " +
            "(12, 'Honvéd Kórház', 'Budapest', 'honved@honved.hu', '+3619877651', 1000)," +
            "(23, 'Szent Imre Kórház', 'Budapest', 'info@sztimre.hu', '+3612376593', 700)," +
            "(32, 'Szent György Kórház', 'Székesfehérvár', 'vakcina@gyorgykorhaz.hu', '+3622877651', 500)," +
            "(43, 'Szeged Kettes Kórház', 'Szeged', 'oltas@ketteskorhazszeged.hu', '+3662234876', 800)";
}
