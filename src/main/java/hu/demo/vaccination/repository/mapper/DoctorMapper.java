package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.domain.Doctor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper implements RowMapper<Doctor> {
    @Override
    public Doctor mapRow(ResultSet resultSet, int i) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(resultSet.getInt("id"));
        doctor.setFirstName(resultSet.getString("first_name"));
        doctor.setLastName(resultSet.getString("last_name"));
        doctor.setEmail(resultSet.getString("email"));
        doctor.setAddress(resultSet.getString("address"));
        doctor.setTelephoneNumber(resultSet.getString("telephone_number"));
        doctor.setDeleted(resultSet.getBoolean("deleted"));
        return doctor;
    }
}
