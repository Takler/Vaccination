package hu.demo.vaccination.repository.mapper;

import hu.demo.vaccination.dto.doctor.DoctorCreateUpdateData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorCreateUpdateDataMapper implements RowMapper<DoctorCreateUpdateData> {
    @Override
    public DoctorCreateUpdateData mapRow(ResultSet resultSet, int i) throws SQLException {
        DoctorCreateUpdateData doctorCreateUpdateData = new DoctorCreateUpdateData();
        doctorCreateUpdateData.setFirstName(resultSet.getString("first_name"));
        doctorCreateUpdateData.setLastName(resultSet.getString("last_name"));
        doctorCreateUpdateData.setEmail(resultSet.getString("email"));
        doctorCreateUpdateData.setAddress(resultSet.getString("address"));
        doctorCreateUpdateData.setTelephoneNumber(resultSet.getString("telephone_number"));
        return doctorCreateUpdateData;
    }
}
