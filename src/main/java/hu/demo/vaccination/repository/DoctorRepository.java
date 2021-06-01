package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.repository.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorRepository {   // implements betétele

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save(Doctor doctor) {    //INSERT
        boolean result = false;
        String sqlInsert = "INSERT INTO doctor (id, first_name, last_name, email, address, telephone_number, deleted)" +
                " VALUES (?,?,?,?,?,?,?)";
        // vizsgálatok hiányoznak ...
        try {
            if (jdbcTemplate.update(sqlInsert, doctor.getId(), doctor.getFirstName(),
                    doctor.getLastName(), doctor.getEmail(), doctor.getAddress(),
                    doctor.getTelephoneNumber(), doctor.isDeleted()) == 1) {
                result = true;
            }
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public List<Map<String, Object>> findAll() {   // Selectbe tenni a logikai kiszűrést
        List<Map<String, Object>> doctors = new LinkedList<>();
        String sqlSelect = "SELECT * FROM doctor WHERE deleted = false";
        try {
            doctors = jdbcTemplate.queryForList(sqlSelect);     // SQL oszlopnevek vannak ??
        } catch (DataAccessException ex) {                      // ??
            ex.printStackTrace();
        }
        return doctors;
    }

    public Doctor getById(int doctorId) {   //null felfelé?
        Doctor doctor = new Doctor();
        DoctorMapper doctorMapper = new DoctorMapper();
        String sqlSelect = "SELECT * FROM doctor WHERE id=? and deleted = false";
        try {
            doctor = jdbcTemplate.queryForObject(sqlSelect, doctorMapper, doctorId);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return doctor;
    }

    public boolean update(Doctor doctor) {   // javítani a id paraméter betételét
        boolean result = false;
        String sqlUpdate = "UPDATE doctor SET first_name=?, last_name=?, email=?, address=?, telephone_number=?, " +
                "deleted=? WHERE id=?";
        // vizsgálatok hiányoznak ...
        try {
            if (jdbcTemplate.update(sqlUpdate, doctor.getFirstName(),
                    doctor.getLastName(), doctor.getEmail(), doctor.getAddress(),
                    doctor.getTelephoneNumber(), doctor.isDeleted(), doctor.getId()) == 1) {
                result = true;
            }
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {   // logikai törlést csinálni csak
        boolean result = false;
        String sqlDelete = "DELETE FROM doctor WHERE id=?";
        // vizsgálatok hiányoznak ...
        try {
            if (jdbcTemplate.update(sqlDelete, id) == 1) {
                result = true;
            }
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
