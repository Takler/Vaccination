package hu.demo.vaccination.repository;

import hu.demo.vaccination.dto.DoctorCreate;
import hu.demo.vaccination.repository.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createDoctor(DoctorCreate doctorCreate) {
        int result = -1;   // clean ??
        String sqlInsert = "INSERT INTO doctor (id, first_name, last_name, email, address, telephone_number, " +
                "type, date_of_birth) VALUES (?,?,?,?,?,?,?,?)";
        // vizsgálatok hiányoznak ...
        try {
            result = jdbcTemplate.update(sqlInsert, doctorCreate.getId(), doctorCreate.getFirstName(),
                    doctorCreate.getLastName(), doctorCreate.getEmail(), doctorCreate.getAddress(),
                    doctorCreate.getTelephoneNumber(), doctorCreate.getType(), doctorCreate.getDateOfBirth());
        } catch (DataAccessException exception) {
            return result;
        }
        return result;
    }

    public List<Map<String, Object>> getDoctorsList() {
        List<Map<String, Object>> doctors = new LinkedList<>();
        String sqlSelect = "SELECT * FROM doctor";
        try {
            doctors = jdbcTemplate.queryForList(sqlSelect);     // SQL oszlopnevek vannak ??
        } catch (DataAccessException ex) {                      // ??
            ex.printStackTrace();
        }
        return doctors;
    }

    public List<DoctorCreate> getDoctor(int id) {     // List ??
        List<DoctorCreate> doctor = null;
        DoctorMapper doctorMapper = new DoctorMapper();
        String sqlSelect = "SELECT * FROM doctor WHERE id=?";
        String[] sqlValues = new String[1];
        sqlValues[0] = Integer.toString(id);
        try {
            doctor = jdbcTemplate.query(sqlSelect, sqlValues, doctorMapper);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return doctor;
    }

    public int updateDoctor(DoctorCreate doctorCreate) {
        int result = -1;   // clean ??
        String sqlUpdate = "UPDATE doctor SET id=?, first_name=?, last_name=?, email=?, address=?, telephone_number=?, " +
                "type=?, date_of_birth=? WHERE id=?";
        // vizsgálatok hiányoznak ...
        try {
            result = jdbcTemplate.update(sqlUpdate, doctorCreate.getId(), doctorCreate.getFirstName(),
                    doctorCreate.getLastName(), doctorCreate.getEmail(), doctorCreate.getAddress(),
                    doctorCreate.getTelephoneNumber(), doctorCreate.getType(), doctorCreate.getDateOfBirth(), doctorCreate.getId());
        } catch (DataAccessException exception) {
            return result;
        }
        return result;
    }

    public int deleteDoctor(int id) {
        int result = -1;   // clean ??
        String sqlDelete = "DELETE FROM doctor WHERE id=?";
        // vizsgálatok hiányoznak ...
        try {
            result = jdbcTemplate.update(sqlDelete, id);
        } catch (DataAccessException exception) {
            return result;
        }
        return result;
    }
}
