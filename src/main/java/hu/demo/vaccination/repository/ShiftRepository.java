package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.dto.shift.ShiftDateData;
import hu.demo.vaccination.repository.mapper.ShiftDateDataMapper;
import hu.demo.vaccination.repository.mapper.ShiftMapper;
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

    public Shift getShift(int shiftId) {
        Shift shift = new Shift();
        ShiftMapper shiftMapper = new ShiftMapper();
        List<Shift> shiftList = new LinkedList<>();
        String id = Integer.toString(shiftId);
        String sqlSelect = "SELECT * FROM shift WHERE id=?";
        shiftList = jdbcTemplate.query(sqlSelect, shiftMapper, id);
        shift = shiftList.get(0);
        return shift;
    }
}
