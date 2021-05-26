package hu.demo.vaccination.repository;

import hu.demo.vaccination.dto.DoctorCreate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper implements RowMapper<DoctorCreate> {
    @Override
    public DoctorCreate mapRow(ResultSet resultSet, int i) throws SQLException {
        DoctorCreate doctorCreate = new DoctorCreate();
        doctorCreate.setId(resultSet.getInt("id"));
        doctorCreate.setFirstName(resultSet.getString("first_name"));
        doctorCreate.setLastName(resultSet.getString("last_name"));
        doctorCreate.setEmail(resultSet.getString("email"));
        doctorCreate.setAddress(resultSet.getString("address"));
        doctorCreate.setFirstName(resultSet.getString("telephone_number"));
        doctorCreate.setType(resultSet.getString("type"));
        doctorCreate.setDateOfBirth(resultSet.getString("date_of_birth"));
        return doctorCreate;
    }
}
