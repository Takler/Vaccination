package hu.demo.vaccination.repository;

import hu.demo.vaccination.utility.DataDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InitializationRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public InitializationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void initialization() {
        for (DataDefinition definition : DataDefinition.values()) {
            jdbc.execute(String.valueOf(definition));
        }
    }
}
