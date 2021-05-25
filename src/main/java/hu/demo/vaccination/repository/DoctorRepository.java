package hu.demo.vaccination.repository;

import hu.demo.vaccination.dto.DoctorCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class DoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createDoctor(DoctorCreate doctorCreate) {
        String sqlInsert = "INSERT INTO vaccination (id, firstName, lastName, email, address, telephone_nember, type, date_of_birth)" +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            int result = jdbcTemplate.update(sqlInsert, doctorCreate.getId(), doctorCreate.getFirstName(), doctorCreate.getLastName(),
                    doctorCreate.getAddress(), doctorCreate.getTelephoneNumber(), doctorCreate.getType(),
                    doctorCreate.getDate_of_birth());
            return result;
        } catch (DataAccessException exception) {
            return -1;
        }
    }

}
