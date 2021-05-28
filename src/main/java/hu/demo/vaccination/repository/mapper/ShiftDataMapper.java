package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.dto.shift.ShiftData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftDataMapper implements RowMapper<ShiftData> {
    @Override
    public ShiftData mapRow(ResultSet resultSet, int i) throws SQLException {
        ShiftData shiftData = new ShiftData();
        shiftData.setId(resultSet.getInt("id"));
        shiftData.setCenter_id(resultSet.getInt("center_id"));
        shiftData.setDoctor_id(resultSet.getInt("doctor_id"));
        shiftData.setStart(resultSet.getTimestamp("start"));
        shiftData.setEnd(resultSet.getTimestamp("end"));
        shiftData.setDeleted(resultSet.getBoolean("deleted"));
        return shiftData;
    }
}
