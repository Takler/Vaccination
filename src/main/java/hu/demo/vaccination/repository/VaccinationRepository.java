package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.dto.VaccinationCreateData;
import hu.demo.vaccination.repository.mapper.VaccinationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Repository
public class VaccinationRepository {

    private final JdbcTemplate jdbc;
    private final VaccinationMapper vaccinationMapper = new VaccinationMapper();

    @Autowired
    public VaccinationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Vaccination> getVaccinations() {
        String sql = "SELECT * FROM vaccination";
        try {
            return jdbc.query(sql, vaccinationMapper);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    public Vaccination getVaccination(int id) {
        String sql = "SELECT * FROM vaccination WHERE id = ?";
        try {
            return jdbc.queryForObject(sql, vaccinationMapper, id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public boolean createVaccination(VaccinationCreateData data) {
        String sql = "INSERT INTO vaccination (" +
                "vaccine_id, " +
                "patient_id, " +
                "shift_id, " +
                "date, " +
                "deleted) " +
                "VALUES (?, ?, ?, ?, ?);";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getVaccineId(),
                    data.getPatientId(),
                    data.getShiftId(),
                    Date.valueOf(data.getDate()),
                    data.isDeleted()
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean updateVaccination(int id, VaccinationCreateData data) {
        String sql = "UPDATE vaccination SET" +
                "vaccine_id = ?, " +
                "patient_id = ?, " +
                "shift_id = ?, " +
                "date = ?, " +
                "deleted = ? " +
                "WHERE id = ?;";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getVaccineId(),
                    data.getPatientId(),
                    data.getShiftId(),
                    Date.valueOf(data.getDate()),
                    data.isDeleted(),
                    id
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean deleteVaccination(int id) {
        String sql = "UPDATE vaccination " +
                "SET deleted = FALSE " +
                "WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

}
