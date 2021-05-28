package hu.demo.vaccination.repository;

import hu.demo.vaccination.dto.shift.ShiftInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiftRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ShiftInfoData getShiftInfo(int shiftId){
        ShiftInfoData shiftInfoData=new ShiftInfoData();
        String sqlSelect = "SELECT start, end FROM shift WHERE id=?";
        //jdbcTemplate.query(sqlSelect, );
        return shiftInfoData;
    }
}
