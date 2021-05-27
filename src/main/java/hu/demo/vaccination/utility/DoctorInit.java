package hu.demo.vaccination.utility;

public class DoctorInit {

   public static String DOCTOR_DROP_TABLE = "DROP TABLE IF EXISTS doctor";

   public static String DOCTOR_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS doctor (id int PRIMARY KEY AUTO_INCREMENT, " +
                "first_name varchar(20) NOT NULL, last_name varchar(20) NOT NULL, email varchar(60), address varchar(200), " +
                "telephone_number varchar(15), type varchar(10), date_of_birth datetime)";

   public static String DOCTOR_SAMPLE_DATA_INSERT = "INSERT INTO doctor (first_name, last_name, email, address, telephone_number, " +
                "type, date_of_birth) VALUES " +
                "('Gipsz',  'Jakab',  'jakab.gipsz@gmail.com',   '1111, Budapest Gipsz utca 1',  '+36 11 111-1111', 'prof.', '1960.10.10'), " +
                "('Kerek',  'Béla',   'bela.kerek@gmail.com',    '2222, Budapest Kerek utca 2',  '+36 22 222-2222', 'adj.',  '1970.10.10'), " +
                "('Karcsú', 'Virág',  'virag.karcsu@gmail.com',  '3333, Budapest Karcsú utca 3', '+36 33 333-3333', 'ápóló', '1980.10.10'), " +
                "('Gipsz',  'Andrea', 'andrea.gipsz@gmail.com',  '4444, Budapest Gipsz utca 4',  '+36 44 444-4444', 'ápóló', '1990.10.10')";
}
