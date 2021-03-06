package hu.demo.vaccination.utility;

public enum DataDefinition {
    VACCINATION_DROP_TABLE("DROP TABLE IF EXISTS vaccination"),
    SHIFT_DROP_TABLE("DROP TABLE IF EXISTS shift"),
    INVENTORY_DROP_TABLE("DROP TABLE IF EXISTS inventory"),
    RESERVATION_DROP_TABLE("DROP TABLE IF EXISTS reservation"),

    DOCTOR_DROP_TABLE("DROP TABLE IF EXISTS doctor"),
    PATIENT_DROP_TABLE("DROP TABLE IF EXISTS patient"),
    VACCINE_DROP_TABLE("DROP TABLE IF EXISTS vaccine"),
    CENTER_DROP_TABLE("DROP TABLE IF EXISTS center"),

    DOCTOR_CREATE_TABLE("CREATE TABLE IF NOT EXISTS doctor (" +
            "id INT PRIMARY KEY AUTO_INCREMENT, " +
            "first_name VARCHAR(20) NOT NULL, " +
            "last_name VARCHAR(20) NOT NULL, " +
            "email VARCHAR(60), " +
            "address VARCHAR(200), " +
            "telephone_number VARCHAR(15), " +
            "deleted BOOLEAN NOT NULL DEFAULT FALSE)"),
    PATIENT_CREATE_TABLE("CREATE TABLE patient (" +
            "id INT PRIMARY KEY, " +
            "first_name VARCHAR(250) NOT NULL, " +
            "last_name VARCHAR(250) NOT NULL, " +
            "mothers_name VARCHAR(250) NOT NULL, " +
            "gender VARCHAR(50) NOT NULL, " +
            "date_of_birth DATE NOT NULL, " +
            "email VARCHAR(250) NOT NULL, " +
            "city VARCHAR(250) NOT NULL, " +
            "zip_code VARCHAR(20) NOT NULL, " +
            "address VARCHAR(250) NOT NULL, " +
            "telephone_number VARCHAR(50) NOT NULL, " +
            "chronic BOOLEAN NOT NULL, " +
            "pregnant BOOLEAN NOT NULL, " +
            "deleted BOOLEAN NOT NULL DEFAULT false)"),
    VACCINE_CREATE_TABLE("CREATE TABLE vaccine " +
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
            "applicable BOOLEAN NOT NULL, " +
            "applicable_for_pregnant BOOLEAN NOT NULL, " +
            "applicable_for_chronic BOOLEAN NOT NULL)"),
    CENTER_CREATE_TABLE("CREATE TABLE center " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(250) NOT NULL, " +
            "city VARCHAR(250) NOT NULL, " +
            "email VARCHAR(250) NOT NULL, " +
            "telephone_number VARCHAR(50) NOT NULL, " +
            "daily_capacity INT NOT NULL, " +
            "deleted BOOLEAN NOT NULL DEFAULT FALSE)"),

    DOCTOR_INSERT_SAMPLE_DATA("INSERT INTO doctor (first_name, last_name, email, address, telephone_number) VALUES " +
            "('Jakab',  'Gipsz',  'jakab.gipsz@gmail.com',   '1111, Budapest Gipsz utca 1',  '+36 11 111-1111'), " +
            "('B??la',   'Kerek',  'bela.kerek@gmail.com',    '2222, Budapest Kerek utca 2',  '+36 22 222-2222'), " +
            "('Vir??g',  'Karcs??', 'virag.karcsu@gmail.com',  '3333, Budapest Karcs?? utca 3', '+36 33 333-3333'), " +
            "('Andrea', 'Gipsz',  'andrea.gipsz@gmail.com',  '4444, Budapest Gipsz utca 4',  '+36 44 444-4444')"),
    VACCINE_INSERT_SAMPLE_DATA("INSERT INTO vaccine " +
            "(name, type, storage_temperature, age_limit_min, age_limit_max, shots_needed, next_shot_id, " +
            "days_until_next_shot, fully_vaccinated_time_period, applicable, applicable_for_pregnant, " +
            "applicable_for_chronic) " +
            "VALUES " +
            "('Pfizer', 'mRNA', -70, 16, 999, 2, 1, 28, 42, 1, 1, 1)," +
            "('Moderna', 'mRNA', -20, 18, 999, 2, 2, 28, 42, 1, 1, 1)," +
            "('AstraZeneca', 'adenovirus', 4, 18, 999, 2, 3, 84, 96, 1, 1, 1), " +
            "('Gamaleja', 'adenovirus', 4, 18, 999, 2, 5, 21, 35, 1, 0, 0), " +
            "('Gamaleja 2nd', 'adenovirus', 4, 18, 999, 2, -1, 0, 14, 1, 0, 0), " +
            "('Sinopharm', 'inactivated virus', 4, 18, 999, 2, 6, 28, 42, 1, 0, 0), " +
            "('Janssen', 'adenovirus', 4, 18, 999, 1, -1, 0, 14, 1, 0, 1)"),
    PATIENT_INSERT_SAMPLE_DATA("INSERT INTO patient " +
            "(id, first_name, last_name, mothers_name, gender, date_of_birth, " +
            "email, city, zip_code, address, telephone_number, chronic, pregnant) " +
            "VALUES " +
            "(157648531,'Albert','Magyar','Bodn??r Gy??ngyi','male','1979-09-16'," +
            "'magyar.a@email.com','Monori erd??','2213','S??p utca 38.','20207128918',1,0)," +
            "(175684569,'P??ter','P??link??s','Luk??cs Franciska','male','1985-10-03'," +
            "'p.peti@email.com','Budapest','1133','Csabai kapu 1.','0617530147',0,0)," +
            "(248248264,'Vir??g','Szak??ts','V??r??s Hermina','female','1970-06-11'," +
            "'virag70@email.com','Budapest','1149','??rp??d fejedelem ??tja 51.','0618659140',1,0)," +
            "(748237274,'Frigyes','Csonka','Proh??szka Ad??l','male','1980-01-22'," +
            "'frigyes.csonka@email.com','??cs','2941','Munk??csy Mih??ly ??t 14.','0634388544',0,0)"),
    CENTER_INSERT_SAMPLE_DATA("INSERT INTO center VALUES " +
            "(12, 'Honv??d K??rh??z', 'Budapest', 'honved@honved.hu', '+3619877651', 1000, false)," +
            "(23, 'Szent Imre K??rh??z', 'Budapest', 'info@sztimre.hu', '+3612376593', 700, false)," +
            "(32, 'Szent Gy??rgy K??rh??z', 'Sz??kesfeh??rv??r', 'vakcina@gyorgykorhaz.hu', '+3622877651', 500, false)," +
            "(43, 'Szeged Kettes K??rh??z', 'Szeged', 'oltas@ketteskorhazszeged.hu', '+3662234876', 800, false)"),

    INVENTORY_CREATE_TABLE("CREATE TABLE inventory " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "center_id INT NOT NULL, " +
            "vaccine_id INT NOT NULL, " +
            "amount INTEGER, " +
            "deleted BOOLEAN NOT NULL DEFAULT FALSE, " +
            "FOREIGN KEY (center_id) REFERENCES center(id), " +
            "FOREIGN KEY (vaccine_id) REFERENCES vaccine(id))"),
    SHIFT_CREATE_TABLE("CREATE TABLE IF NOT EXISTS shift (" +
            "id INT PRIMARY KEY AUTO_INCREMENT, " +
            "center_id INT NOT NULL, " +
            "doctor_id INT NOT NULL, " +
            "start TIMESTAMP NOT NULL, " +
            "end TIMESTAMP NOT NULL, " +
            "deleted BOOLEAN NOT NULL DEFAULT FALSE, " +
            "FOREIGN KEY (center_id) REFERENCES center(id), " +
            "FOREIGN KEY (doctor_id) REFERENCES doctor(id))"),
    VACCINATION_CREATE_TABLE("CREATE TABLE vaccination " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "vaccine_id INT NOT NULL, " +
            "patient_id INT NOT NULL, " +
            "shift_id INT NOT NULL, " +
            "date DATE NOT NULL, " +
            "deleted BOOLEAN NOT NULL DEFAULT false, " +
            "FOREIGN KEY (vaccine_id) REFERENCES vaccine(id), " +
            "FOREIGN KEY (patient_id) REFERENCES patient(id), " +
            "FOREIGN KEY (shift_id) REFERENCES shift(id))"),
    RESERVATION_CREATE_TABLE("CREATE TABLE reservation (" +
            "id INT PRIMARY KEY AUTO_INCREMENT, " +
            "patient_id INT NOT NULL, " +
            "center_id INT NOT NULL, " +
            "vaccine_id INT NOT NULL, " +
            "registration DATE NOT NULL, " +
            "next_shot DATE NOT NULL, " +
            "deleted BOOLEAN NOT NULL DEFAULT false, " +
            "FOREIGN KEY (patient_id) REFERENCES patient(id), " +
            "FOREIGN KEY (center_id) REFERENCES center(id), " +
            "FOREIGN KEY (vaccine_id) REFERENCES vaccine(id))"),

    INVENTORY_INSERT_SAMPLE_DATA("INSERT INTO inventory " +
            "(center_id, vaccine_id, amount) " +
            "VALUES " +
            "(12, 1, 1200), " +
            "(12, 2, 331), " +
            "(12, 3, 0), " +
            "(12, 4, 100), " +
            "(23, 2, 300), " +
            "(32, 3, 2000), " +
            "(43, 4, 500)"),
    RESERVATION_INSERT_SAMPLE_DATA("INSERT INTO reservation " +
            "(patient_id, center_id, vaccine_id, registration, next_shot) " +
            "VALUES " +
            "(157648531,12,1,'2021-05-27','2021-06-05')"),
    SHIFT_INSERT_SAMPLE_DATA("INSERT INTO shift (center_id, doctor_id, start, end) " +
            "VALUES (12, 1, '2021-05-28 08:01:12', '2021-05-28 14:02:01')"),
    VACCINATION_INSERT_SAMPLE_DATA("INSERT INTO vaccination (vaccine_id, patient_id, shift_id, date, deleted) " +
            "VALUES (1, 157648531, 1, '2021-05-28', false), " +
            "(2, 175684569, 1, '2021-05-28', false), " +
            "(1, 248248264, 1, '2021-05-28', false)");

    private final String definition;

    DataDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }
}
