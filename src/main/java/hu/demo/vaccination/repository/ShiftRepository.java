package hu.demo.vaccination.repository;

import hu.demo.vaccination.dto.shift.ShiftData;
import hu.demo.vaccination.dto.shift.ShiftInfoData;
import hu.demo.vaccination.dto.shift.ShiftDateData;
import hu.demo.vaccination.repository.mapper.ShiftDataMapper;
import hu.demo.vaccination.repository.mapper.ShiftDateDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ShiftDateData getShiftDateData(int shiftId) {
        ShiftDateData shiftDateData = new ShiftDateData();
        List<ShiftDateData> shiftDateDataList = new LinkedList<>();
        ShiftDateDataMapper shiftDateDataMapper = new ShiftDateDataMapper();
        String id = Integer.toString(shiftId);
        String sqlSelect = "SELECT start, end FROM shift WHERE id=?";
        shiftDateDataList = jdbcTemplate.query(sqlSelect, shiftDateDataMapper, id);
        shiftDateData = shiftDateDataList.get(0);
        return shiftDateData;
    }

    public ShiftData getShift(int shiftId) {
        ShiftData shiftData = new ShiftData();
        List<ShiftData> shiftDataList = new LinkedList<>();
        ShiftDataMapper shiftDataMapper = new ShiftDataMapper();
        String id = Integer.toString(shiftId);
        String sqlSelect = "SELECT * FROM shift WHERE id=?";
        shiftDataList = jdbcTemplate.query(sqlSelect, shiftDataMapper, id);
        shiftData = shiftDataList.get(0);
        return shiftData;
    }
}
