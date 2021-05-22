package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class PatientRepository {

    private final JdbcTemplate jdbc;

    private final RowMapper<Patient> patientRowMapper = ((resultSet, i) -> {
        Patient patient = new Patient();
        patient.setId(resultSet.getInt("id"));
        patient.setFirstName(resultSet.getString("first_name"));
        patient.setLastName(resultSet.getString("last_name"));
        patient.setMothersName(resultSet.getString("mothers_name"));
        patient.setGender(resultSet.getString("gender"));
        patient.setDateOfBirth(resultSet.getDate("date_of_birth"));
        patient.setEmail(resultSet.getString("email"));
        patient.setCity(resultSet.getString("city"));
        patient.setZipCode(resultSet.getString("zip_code"));
        patient.setAddress(resultSet.getString("address"));
        patient.setTelephoneNumber(resultSet.getString("telephone_number"));
        patient.setUnderlyingMedicalCondition(resultSet.getBoolean("underlying_medical_condition"));
        return patient;
    });

    @Autowired
    public PatientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Patient> getPatients() {
        String sql = "SELECT id, first_name, last_name, mothers_name, gender, date_of_birth, " +
                "email, city, zip_code, address, telephone_number, underlying_medical_condition " +
                "FROM patient " +
                "WHERE deleted = false";
        try {
            return jdbc.query(sql, patientRowMapper);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    public Patient getPatient(int id) {
        String sqlQuery = "SELECT id, first_name, last_name, mothers_name, gender, date_of_birth, " +
                "email, city, zip_code, address, telephone_number, underlying_medical_condition " +
                "FROM patient " +
                "WHERE id = ? AND deleted = false";
        try {
            return jdbc.queryForObject(sqlQuery, patientRowMapper, id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public boolean createPatient(PatientCreateData data) {
        String sql = "INSERT INTO patient (" +
                "id, " +
                "first_name, " +
                "last_name, " +
                "mothers_name, " +
                "gender, " +
                "date_of_birth, " +
                "email, " +
                "city, " +
                "zip_code, " +
                "address, " +
                "telephone_number, " +
                "underlying_medical_condition, " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getId(),
                    data.getFirstName(),
                    data.getLastName(),
                    data.getMothersName(),
                    data.getGender(),
                    data.getDateOfBirth(),
                    data.getEmail(),
                    data.getCity(),
                    data.getZipCode(),
                    data.getAddress(),
                    data.getTelephoneNumber(),
                    data.isUnderlyingMedicalCondition()
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean updatePatient(int id, PatientCreateData data) {
        String sql = "UPDATE patient SET " +
                "id = ?, " +
                "first_name = ?, " +
                "last_name = ?, " +
                "mothers_name = ?, " +
                "gender = ?, " +
                "date_of_birth = ?, " +
                "email = ?, " +
                "city = ?, " +
                "zip_code = ?, " +
                "address = ?, " +
                "telephone_number = ?, " +
                "underlying_medical_condition = ?, " +
                "WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getId(),
                    data.getFirstName(),
                    data.getLastName(),
                    data.getMothersName(),
                    data.getGender(),
                    data.getDateOfBirth(),
                    data.getEmail(),
                    data.getCity(),
                    data.getZipCode(),
                    data.getAddress(),
                    data.getTelephoneNumber(),
                    data.isUnderlyingMedicalCondition(),
                    id
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean deletePatient(int id) {
        String sql = "UPDATE patient SET deleted = ? WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql, true, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
