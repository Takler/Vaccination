package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.dto.shift.ShiftDateData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftDateDataMapper implements RowMapper<ShiftDateData> {

    @Override
    public ShiftDateData mapRow(ResultSet resultSet, int i) throws SQLException {
        ShiftDateData shiftDateData = new ShiftDateData();
        shiftDateData.setStart(resultSet.getTimestamp("start"));
        shiftDateData.setEnd(resultSet.getTimestamp("end"));  //nincs csak getDate vagy getTimeStamp
        return shiftDateData;
    }
}
