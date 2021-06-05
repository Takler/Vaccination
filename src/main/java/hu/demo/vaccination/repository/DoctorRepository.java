package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.dto.doctor.DoctorCreateUpdateData;
import hu.demo.vaccination.repository.mapper.DoctorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class DoctorRepository {   // implements betétele

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Doctor> findAll() {
        String sqlSelect = "SELECT * FROM doctor WHERE deleted = false";
        try {
            return jdbcTemplate.query(sqlSelect, new DoctorMapper());
        } catch (DataAccessException ex) {
            log.error("findAll exception " + ex.getMessage());
            return Collections.emptyList();
        }
    }

    public Doctor getById(int doctorId) {
        String sqlSelect = "SELECT * FROM doctor WHERE id=? and deleted = false";
        try {
            return jdbcTemplate.queryForObject(sqlSelect, new DoctorMapper(), doctorId);
        } catch (DataAccessException ex) {
            log.error("getById exception: " + ex.getMessage());
            return null;
        }
    }

    public boolean save(DoctorCreateUpdateData doctorCreateUpdateData) {
        String sqlInsert = "INSERT INTO doctor (first_name, last_name, email, address, telephone_number) VALUES (?,?,?,?,?)";
        try {
            if (jdbcTemplate.update(sqlInsert, doctorCreateUpdateData.getFirstName(),
                    doctorCreateUpdateData.getLastName(), doctorCreateUpdateData.getEmail(),
                    doctorCreateUpdateData.getAddress(), doctorCreateUpdateData.getTelephoneNumber()) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            log.error("save exception " + ex.getMessage());
            // TODO ide rakva a return miért nem működik?
        }
        return false;
    }

    public boolean update(int doctorId, DoctorCreateUpdateData doctorCreateUpdateData) {
        String sqlUpdate = "UPDATE doctor SET first_name = ?, last_name = ?, email = ?, address = ?, " +
                "telephone_number = ?, WHERE id = ?";
        try {
            if (jdbcTemplate.update(sqlUpdate, doctorCreateUpdateData.getFirstName(),
                    doctorCreateUpdateData.getLastName(), doctorCreateUpdateData.getEmail(),
                    doctorCreateUpdateData.getAddress(), doctorCreateUpdateData.getTelephoneNumber(),
                    doctorId) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            log.error("update exception " + ex.getMessage());
            // TODO ide rakva a return miért nem működik?
        }
        return false;
    }

    public boolean delete(int doctorId) {
        String sqlUpdate = "UPDATE doctor SET deleted = true WHERE id = ?";
        try {
            if (jdbcTemplate.update(sqlUpdate, doctorId) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            log.error("delete exception " + ex.getMessage());
        }
        return false;
    }
}
