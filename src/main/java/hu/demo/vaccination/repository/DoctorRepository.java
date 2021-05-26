package hu.demo.vaccination.repository;

import hu.demo.vaccination.dto.DoctorCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorRepository {

    private final JdbcTemplate jdbcTemplate;
    List<Map<String, Object>> doctors;

    @Autowired
    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createDoctor(DoctorCreate doctorCreate) {
        String sqlInsert = "INSERT INTO doctor (id, firstName, lastName, email, address, telephone_nember, " +
                "type, date_of_birth) VALUES (?,?,?,?,?,?,?,?)";
        // vizsgálatok hiányoznak ...
        try {
            int result = jdbcTemplate.update(sqlInsert, doctorCreate.getId(), doctorCreate.getFirstName(),
                    doctorCreate.getLastName(), doctorCreate.getEmail(), doctorCreate.getAddress(),
                    doctorCreate.getTelephoneNumber(), doctorCreate.getType(), doctorCreate.getDateOfBirth());
            return result;
        } catch (DataAccessException exception) {
            return -1;
        }
    }

    public List<Map<String, Object>> getDoctorsList() {
        String sqlSelect = "SELECT * FROM doctor";
        try {
            doctors = jdbcTemplate.queryForList(sqlSelect);
        } catch (DataAccessException ex) {                      // ??
            ex.printStackTrace();
        }
        return doctors;
    }
}
