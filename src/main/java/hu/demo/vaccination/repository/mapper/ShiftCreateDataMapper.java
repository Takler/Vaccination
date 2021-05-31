package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.dto.shift.ShiftCreateData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftCreateDataMapper implements RowMapper<ShiftCreateData> {
    @Override
    public ShiftCreateData mapRow(ResultSet resultSet, int i) throws SQLException {
        ShiftCreateData shiftCreateData = new ShiftCreateData();
        shiftCreateData.setCenter_id(resultSet.getInt("center_id"));
        shiftCreateData.setDoctor_id(resultSet.getInt("doctor_id"));
        shiftCreateData.setStart(resultSet.getTimestamp("start"));
        shiftCreateData.setEnd(resultSet.getTimestamp("end"));
        return shiftCreateData;
    }
}
