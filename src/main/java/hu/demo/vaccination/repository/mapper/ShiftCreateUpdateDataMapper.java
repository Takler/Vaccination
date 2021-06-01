package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.dto.shift.ShiftCreateUpdateData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftCreateUpdateDataMapper implements RowMapper<ShiftCreateUpdateData> {
    @Override
    public ShiftCreateUpdateData mapRow(ResultSet resultSet, int i) throws SQLException {
        ShiftCreateUpdateData shiftCreateUpdateData = new ShiftCreateUpdateData();
        shiftCreateUpdateData.setCenterId(resultSet.getInt("center_id"));
        shiftCreateUpdateData.setDoctorId(resultSet.getInt("doctor_id"));
        shiftCreateUpdateData.setStart(resultSet.getTimestamp("start"));
        shiftCreateUpdateData.setEnd(resultSet.getTimestamp("end"));
        return shiftCreateUpdateData;
    }
}
