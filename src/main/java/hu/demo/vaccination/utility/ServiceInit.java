package hu.demo.vaccination.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceInit {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ServiceInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS service(id int PRIMARY KEY AUTO_INCREMENT, " +
                "center_id int NOT NULL, doctor_id int NOT NULL)";
        try {
            jdbcTemplate.execute(sqlCreate);
        } catch (DataAccessException ex) {
           ex.printStackTrace();
        }
    }
}
