package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.domain.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftMapper implements RowMapper<Shift> {
    @Override
    public Shift mapRow(ResultSet resultSet, int i) throws SQLException {
        Shift shift = new Shift();
        shift.setId(resultSet.getInt("id"));
        shift.setCenter_id(resultSet.getInt("center_id"));
        shift.setDoctor_id(resultSet.getInt("doctor_id"));
        shift.setStart(resultSet.getTimestamp("start"));
        shift.setEnd(resultSet.getTimestamp("end"));
        shift.setDeleted(resultSet.getBoolean("deleted"));
        return shift;
    }
}
