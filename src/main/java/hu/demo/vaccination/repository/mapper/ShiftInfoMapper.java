package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.dto.shift.ShiftInfoData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftInfoMapper implements RowMapper<ShiftInfoData> {

    @Override
    public ShiftInfoData mapRow(ResultSet resultSet, int i) throws SQLException {
        ShiftInfoData shiftData = new ShiftInfoData();
        shiftData.setId(resultSet.getInt("id"));


        return null;
    }
}
