package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.dto.shift.ShiftCreateUpdateData;
import hu.demo.vaccination.dto.shift.ShiftDateData;
import hu.demo.vaccination.repository.mapper.ShiftDateDataMapper;
import hu.demo.vaccination.repository.mapper.ShiftMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
public class ShiftRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Shift> findAll() {
        String sqlSelect = "SELECT * FROM shift WHERE deleted = false";
        try {
            return jdbcTemplate.query(sqlSelect, new ShiftMapper());  //TODO ha nincs eredmény ez is exceptiont dob?
        } catch (DataAccessException ex) {
            log.error("findAll exception: " + ex.getMessage());
            return Collections.emptyList();
        }
    }

    public Shift getById(int shiftId) {
        String sqlSelect = "SELECT * FROM shift WHERE id=? AND deleted = false";
        try {
            return jdbcTemplate.queryForObject(sqlSelect, new ShiftMapper(), shiftId);
        } catch (DataAccessException ex) {
            log.error("getById exception: " + ex.getMessage());
            return null;
        }
    }

    public boolean save(ShiftCreateUpdateData shiftCreateUpdateData) {
        String sqlInsert = "INSERT INTO shift (center_id, doctor_id, start, end) VALUES (?,?,?,?)";
        try {
            if (jdbcTemplate.update(sqlInsert, shiftCreateUpdateData.getCenterId(), shiftCreateUpdateData.getDoctorId(),
                    shiftCreateUpdateData.getStart(), shiftCreateUpdateData.getEnd()) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            log.error("save exception " + ex.getMessage());
            // TODO ide rakva a return miért nem működik?
        }
        return false;
    }


    public boolean update(int shiftId, ShiftCreateUpdateData shiftCreateUpdateData) {
        String sqlUpdate = "UPDATE shift SET center_id = ?, doctor_id = ?, start = ?, end = ? WHERE id = ?";
        try {
            if (jdbcTemplate.update(sqlUpdate, shiftCreateUpdateData.getCenterId(), shiftCreateUpdateData.getDoctorId(),
                    shiftCreateUpdateData.getStart(), shiftCreateUpdateData.getEnd(), shiftId) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            log.error("update exception " + ex.getMessage());
            // TODO ide rakva a return miért nem működik?
        }
        return false;
    }

    public boolean delete(int shiftId) {
        String sqlUpdate = "UPDATE shift SET deleted = true WHERE id = ?";
        try {
            if (jdbcTemplate.update(sqlUpdate, shiftId) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            log.error("delete exception " + ex.getMessage());
        }
        return false;
    }

    public Shift getShift(int shiftId) {
        Shift shift = new Shift();
        ShiftMapper shiftMapper = new ShiftMapper();
        List<Shift> shiftList = new LinkedList<>();
        String sqlSelect = "SELECT * FROM shift WHERE id=?";
        shiftList = jdbcTemplate.query(sqlSelect, shiftMapper, Integer.toString(shiftId));
        shift = shiftList.get(0);
        return shift;
    }

    public ShiftDateData getShiftDateData(int shiftId) {
        ShiftDateData shiftDateData = new ShiftDateData();
        List<ShiftDateData> shiftDateDataList = new LinkedList<>();
        ShiftDateDataMapper shiftDateDataMapper = new ShiftDateDataMapper();
        String sqlSelect = "SELECT start, end FROM shift WHERE id=?";
        shiftDateDataList = jdbcTemplate.query(sqlSelect, shiftDateDataMapper, Integer.toString(shiftId));
        shiftDateData = shiftDateDataList.get(0);
        return shiftDateData;
    }
}
