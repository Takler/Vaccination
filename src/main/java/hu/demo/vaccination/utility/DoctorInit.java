package hu.demo.vaccination.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorInit {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS doctor (id int PRIMARY KEY AUTO_INCREMENT, " +
               "firstName varchar(20) NOT NULL, lastName varchar(20) NOT NULL, email varchar(60), address varchar(200), " +
              "telephone_nember varchar(15), type varchar(10), date_of_birth datetime)";
        jdbcTemplate.execute(sqlCreate);
    }
}
