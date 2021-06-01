package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.domain.Vaccination;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VaccinationMapper implements RowMapper<Vaccination> {

    @Override
    public Vaccination mapRow(ResultSet resultSet, int i) throws SQLException {
        Vaccination vaccination = new Vaccination();
        vaccination.setId(resultSet.getInt("id"));
        vaccination.setVaccineId(resultSet.getInt("vaccine_id"));
        vaccination.setPatientId(resultSet.getInt("patient_id"));
        vaccination.setShiftId(resultSet.getInt("shift_id"));
        vaccination.setDate(resultSet.getDate("date").toLocalDate());
        vaccination.setDeleted(resultSet.getBoolean("deleted"));
        return vaccination;
    }
}
