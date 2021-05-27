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

    DOCTOR_CREATE_TABLE("CREATE TABLE IF NOT EXISTS doctor " +
            "(id int PRIMARY KEY AUTO_INCREMENT, " +
            "first_name varchar(20) NOT NULL, " +
            "last_name varchar(20) NOT NULL, " +
            "email varchar(60), " +
            "address varchar(200), " +
            "telephone_number varchar(15), " +
            "date_of_birth datetime)"),
    PATIENT_CREATE_TABLE("CREATE TABLE patient " +
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

    DOCTOR_INSERT_SAMPLE_DATA("INSERT INTO doctor (first_name, last_name, email, address, telephone_number, " +
            "date_of_birth) VALUES " +
            "('Gipsz',  'Jakab',  'jakab.gipsz@gmail.com',   '1111, Budapest Gipsz utca 1',  '+36 11 111-1111', '1960.10.10'), " +
            "('Kerek',  'Béla',   'bela.kerek@gmail.com',    '2222, Budapest Kerek utca 2',  '+36 22 222-2222',  '1970.10.10'), " +
            "('Karcsú', 'Virág',  'virag.karcsu@gmail.com',  '3333, Budapest Karcsú utca 3', '+36 33 333-3333', '1980.10.10'), " +
            "('Gipsz',  'Andrea', 'andrea.gipsz@gmail.com',  '4444, Budapest Gipsz utca 4',  '+36 44 444-4444', '1990.10.10')"),
    VACCINE_INSERT_SAMPLE_DATA("INSERT INTO vaccine " +
            "(name, type, storage_temperature, age_limit_min, age_limit_max, shots_needed, next_shot_id, " +
            "days_until_next_shot, fully_vaccinated_time_period, applicable, applicable_for_pregnant, " +
            "applicable_for_chronic) " +
            "VALUES " +
            "('Pfizer', 'mRNA', -70, 16, 999, 2, 1, 28, 42, 1, 1, 1)," +
            "('Moderna', 'mRNA', -20, 18, 999, 2, 2, 28, 42, 1, 1, 1)," +
            "('AstraZeneca', 'adenovirus', 4, 18, 999, 2, 3, 84, 96, 1, 1, 1), " +
            "('Gamaleja 1st', 'adenovirus', 4, 18, 999, 2, 5, 21, 35, 1, 0, 0), " +
            "('Gamaleja 2nd', 'adenovirus', 4, 18, 999, 2, -1, 0, 14, 1, 0, 0), " +
            "('Sinopharm', 'unactivated virus', 4, 18, 999, 2, 6, 28, 42, 1, 0, 0), " +
            "('Janssen', 'adenovirus', 4, 18, 999, 1, -1, 0, 14, 1, 0, 1)"),
    PATIENT_INSERT_SAMPLE_DATA("INSERT INTO patient " +
            "(id, first_name, last_name, mothers_name, gender, date_of_birth, " +
            "email, city, zip_code, address, telephone_number, chronic, pregnant) " +
            "VALUES " +
            "(157648531,'Albert','Magyar','Bodnár Gyöngyi','male','1979-09-16'," +
            "'magyar.a@email.com','Monori erdő','2213','Síp utca 38.','20207128918',1,0)," +
            "(175684569,'Péter','Pálinkás','Lukács Franciska','male','1985-10-03'," +
            "'p.peti@email.com','Budapest','1133','Csabai kapu 1.','0617530147',0,0)," +
            "(248248264,'Virág','Szakáts','Vörös Hermina','female','1970-06-11'," +
            "'virag70@email.com','Budapest','1149','Árpád fejedelem útja 51.','0618659140',1,0)," +
            "(748237274,'Frigyes','Csonka','Prohászka Adél','male','1980-01-22'," +
            "'frigyes.csonka@email.com','Ács','2941','Munkácsy Mihály út 14.','0634388544',0,0)"),
    CENTER_INSERT_SAMPLE_DATA("INSERT INTO center VALUES " +
            "(12, 'Honvéd Kórház', 'Budapest', 'honved@honved.hu', '+3619877651', 1000, false)," +
            "(23, 'Szent Imre Kórház', 'Budapest', 'info@sztimre.hu', '+3612376593', 700, false)," +
            "(32, 'Szent György Kórház', 'Székesfehérvár', 'vakcina@gyorgykorhaz.hu', '+3622877651', 500, false)," +
            "(43, 'Szeged Kettes Kórház', 'Szeged', 'oltas@ketteskorhazszeged.hu', '+3662234876', 800, false)"),

    INVENTORY_CREATE_TABLE("CREATE TABLE inventory " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "center_id INT NOT NULL, " +
            "vaccine_id INT NOT NULL, " +
            "amount INTEGER, " +
            "deleted BOOLEAN NOT NULL DEFAULT FALSE, " +
            "FOREIGN KEY (center_id) REFERENCES center(id), " +
            "FOREIGN KEY (vaccine_id) REFERENCES vaccine(id))"),
    SHIFT_CREATE_TABLE("CREATE TABLE IF NOT EXISTS shift" +
            "(id int PRIMARY KEY AUTO_INCREMENT, " +
            "center_id int NOT NULL, " +
            "doctor_id int NOT NULL,"+
            "FOREIGN KEY (center_id) REFERENCES center(id),"+
            "FOREIGN KEY (doctor_id) REFERENCES doctor(id))"),
    VACCINATION_CREATE_TABLE("CREATE TABLE vaccination " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "vaccine_id INT NOT NULL, " +
            "patient_id INT NOT NULL, " +
            "shift_id INT NOT NULL, " +
            "FOREIGN KEY (vaccine_id) REFERENCES vaccine(id), " +
            "FOREIGN KEY (patient_id) REFERENCES patient(id), " +
            "FOREIGN KEY (shift_id) REFERENCES shift(id))"),
    RESERVATION_CREATE_TABLE("CREATE TABLE reservation " +
            "(id INT PRIMARY KEY AUTO_INCREMENT, " +
            "patient_id INT NOT NULL, " +
            "center_id INT NOT NULL, " +
            "vaccine_id INT NOT NULL, " +
            "registration DATE NOT NULL, " +
            "next_shot DATE NOT NULL, " +
            "deleted BOOLEAN NOT NULL DEFAULT false, " +
            "FOREIGN KEY (patient_id) REFERENCES patient(id), " +
            "FOREIGN KEY (center_id) REFERENCES center(id), " +
            "FOREIGN KEY (vaccine_id) REFERENCES vaccine(id))");

    private final String definition;

    DataDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }
}
