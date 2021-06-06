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
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Repository
public class DoctorRepository {

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
        String sqlSelect = "SELECT * FROM doctor WHERE id=? AND deleted = false";
        try {
            return jdbcTemplate.queryForObject(sqlSelect, new DoctorMapper(), doctorId);
        } catch (DataAccessException ex) {
            log.error("getById exception: " + ex.getMessage());
            return null;
        }
    }

    public boolean save(DoctorCreateUpdateData doctorCreateUpdateData) {  //TODO miaz a @NotNull kint a balszegélyen?
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
                "telephone_number = ? WHERE id = ?";
        try {
            if (jdbcTemplate.update(sqlUpdate, doctorCreateUpdateData.getFirstName(),
                    doctorCreateUpdateData.getLastName(), doctorCreateUpdateData.getEmail(),
                    doctorCreateUpdateData.getAddress(), doctorCreateUpdateData.getTelephoneNumber(),
                    doctorId) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            log.error("update exception " + ex.getMessage());
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

    public String getName(int doctorId) {
        Map<String, Object> doctorFirstLastname = new TreeMap<>();
        String name = "";
        String sqlSelect = "SELECT first_name, last_name FROM doctor WHERE id = ? AND deleted = false";
        try {
            doctorFirstLastname = jdbcTemplate.queryForMap(sqlSelect, doctorId);
            for (Map.Entry<String, Object> item : doctorFirstLastname.entrySet()) {
                name += String.valueOf(item.getValue() + " ");   //TODO Object.toString...
            }
            return name;
        } catch (DataAccessException ex) {
            log.error("delete exception " + ex.getMessage());
        }
        return "";
    }

    public List<String> getLastName(String doctorFirstName) {
        String sqlSelect = "SELECT last_name FROM doctor WHERE first_name = ? AND deleted = false ORDER BY last_name";
        try {
            return jdbcTemplate.queryForList(sqlSelect, String.class, doctorFirstName);
        } catch (DataAccessException ex) {
            log.error("delete exception " + ex.getMessage());
        }
        return null;
    }
}
