package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Vaccination;
import hu.demo.vaccination.dto.vaccination.VaccinationCreateData;
import hu.demo.vaccination.repository.mapper.VaccinationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class VaccinationRepository {

    private final JdbcTemplate jdbc;
    private final VaccinationMapper vaccinationMapper = new VaccinationMapper();

    @Autowired
    public VaccinationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public VaccinationRepository(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    public List<Vaccination> getVaccinations() {
        String sql = "SELECT * FROM vaccination WHERE deleted = FALSE";
        try {
            return jdbc.query(sql, vaccinationMapper);
        } catch (DataAccessException e) {
            log.error("getVaccinations exception: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Vaccination getVaccination(int id) {
        String sql = "SELECT * FROM vaccination WHERE id = ? AND deleted = FALSE";
        try {
            return jdbc.queryForObject(sql, vaccinationMapper, id);
        } catch (DataAccessException e) {
            log.error("getVaccination exception: " + e.getMessage());
            return null;
        }
    }

    public boolean createVaccination(VaccinationCreateData data) {
        String sql = "INSERT INTO vaccination (" +
                "vaccine_id, " +
                "patient_id, " +
                "shift_id, " +
                "date) " +
                "VALUES (?, ?, ?, ?)";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getVaccineId(),
                    data.getPatientId(),
                    data.getShiftId(),
                    Date.valueOf(data.getDate())
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("createVaccination exception: " + e.getMessage());
            return false;
        }
    }

    public boolean updateVaccination(int id, VaccinationCreateData data) {
        String sql = "UPDATE vaccination SET " +
                "vaccine_id = ?, " +
                "patient_id = ?, " +
                "shift_id = ?, " +
                "date = ? " +
                "WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getVaccineId(),
                    data.getPatientId(),
                    data.getShiftId(),
                    Date.valueOf(data.getDate()),
                    id
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("updateVaccination exception: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteVaccination(int id) {
        String sql = "UPDATE vaccination " +
                "SET deleted = TRUE " +
                "WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("deleteVaccination exception: " + e.getMessage());
            return false;
        }
    }

}
