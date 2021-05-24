package hu.demo.vaccination.repository;

public class VaccineInit {

    private VaccineInit() {
    }

    public static final String VACCINE_DROP_TABLE = "DROP TABLE IF EXISTS vaccines";

    public static final String VACCINE_INIT_TABLE = "CREATE TABLE vaccines " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(250) NOT NULL, " +
            "type VARCHAR(250) NOT NULL, " +
            "storage_temperature INTEGER NOT NULL, " +
            "age_limit_min SMALLINT NOT NULL, " +
            "age_limit_max SMALLINT NOT NULL, " +
            "shots_needed TINYINT NOT NULL, " +
            "next_shot_id TINYINT NOT NULL, " +
            "days_until_next_shot SMALLINT NOT NULL, " +
            "fully_vaccinated_time_period SMALLINT NOT NULL, " +
            "is_applicable BOOLEAN NOT NULL, " +
            "is_applicable_for_pregnant BOOLEAN NOT NULL, " +
            "is_applicable_for_chronic BOOLEAN NOT NULL);";

    public static final String VACCINE_DATA_INSERT = "INSERT INTO vaccines " +
            "(name, type, storage_temperature, age_limit_min, age_limit_max, shots_needed, next_shot_id, " +
            "days_until_next_shot, fully_vaccinated_time_period, is_applicable, is_applicable_for_pregnant, " +
            "is_applicable_for_chronic) " +
            "VALUES " +
            "('Pfizer', 'mRNA', -70, 16, 999, 2, 1, 28, 42, 1, 1, 1)," +
            "('Moderna', 'mRNA', -20, 18, 999, 2, 2, 28, 42, 1, 1, 1)," +
            "('AstraZeneca', 'adenovirus', 4, 18, 999, 2, 3, 84, 96, 1, 1, 1), " +
            "('Gamaleja 1st', 'adenovirus', 4, 18, 999, 2, 5, 21, 35, 1, 0, 0), " +
            "('Gamaleja 2nd', 'adenovirus', 4, 18, 999, 2, -1, 0, 14, 1, 0, 0), " +
            "('Sinopharm', 'unactivated virus', 4, 18, 999, 2, 6, 28, 42, 1, 0, 0), " +
            "('Janssen', 'adenovirus', 4, 18, 999, 1, -1, 0, 14, 1, 0, 1);";

}
