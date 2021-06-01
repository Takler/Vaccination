package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.dto.shift.ShiftCreateUpdateData;
import hu.demo.vaccination.dto.shift.ShiftDateData;
import hu.demo.vaccination.repository.mapper.ShiftDateDataMapper;
import hu.demo.vaccination.repository.mapper.ShiftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class ShiftRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Shift getById(int shiftId) {
        Shift shift = new Shift();
        String sqlSelect = "SELECT * FROM shift WHERE id=? and deleted = false";
        try {
            shift = jdbcTemplate.queryForObject(sqlSelect, new ShiftMapper(), shiftId);   // numerikus id megy?
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return shift;
    }

    public List<Shift> findAll() {
        String sqlSelect = "SELECT * FROM shift WHERE deleted = false";
        try {
            return jdbcTemplate.query(sqlSelect, new ShiftMapper());          // A queryForList() a ? -nél kell?
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public boolean save(ShiftCreateUpdateData shiftCreateUpdateData) {      //INSERT
        String sqlInsert = "INSERT INTO shift (center_id, doctor_id, start, end) " +
                "VALUES (?, ?, ?, ?)";
        try {
            if (jdbcTemplate.update(sqlInsert, shiftCreateUpdateData.getCenter_id(), shiftCreateUpdateData.getDoctor_id(),
                    shiftCreateUpdateData.getStart(), shiftCreateUpdateData.getEnd()) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean update(int shiftId, ShiftCreateUpdateData shiftCreateUpdateData) {
        String sqlUpdate = "UPDATE shift SET center_id = ?, doctor_id = ?, start = ?, end = ?" +
                "WHERE id = ?";
        try {
            if (jdbcTemplate.update(sqlUpdate, shiftCreateUpdateData.getCenter_id(), shiftCreateUpdateData.getDoctor_id(),
                    shiftCreateUpdateData.getStart(), shiftCreateUpdateData.getEnd(), shiftId) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int shiftId) {
        String sqlUpdate = "UPDATE shift SET deleted = false WHERE id = ?";
        try {
            if (jdbcTemplate.update(sqlUpdate, shiftId) == 1) {
                return true;
            }
        } catch (DataAccessException ex) {
            ex.printStackTrace();
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
