package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.dto.shift.ShiftNameInfoData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftInfoMapper implements RowMapper<ShiftNameInfoData> {

    @Override
    public ShiftNameInfoData mapRow(ResultSet resultSet, int i) throws SQLException {
        ShiftNameInfoData shiftData = new ShiftNameInfoData();
        shiftData.setId(resultSet.getInt("id"));


        return null;
    }
}
