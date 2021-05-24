package hu.demo.vaccination.repository;

public class VaccineInit {

    private VaccineInit() {
    }

    public static final String VACCINE_DROP_TABLE = "DROP TABLE IF EXISTS vaccines";

    public static final String VACCINE_INIT_TABLE = "CREATE TABLE vaccines " +
            "(id INT PRIMARY KEY AUTO INCREMENT, " +
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

    public static final String VACCINE_SAMPLE_DATA_INSERT = "INSERT INTO vaccines " +
            "(name, type, storage_temperature, age_limit_min, age_limit_max, shots_needed, next_shot_id, " +
            "days_until_next_shot, fully_vaccinated_time_period, is_applicable, is_applicable_for_pregnant, " +
            "is_applicable_for_chronic) " +
            "VALUES " +
            "();";

}
