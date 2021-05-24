package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.VaccineCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class VaccineRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<Vaccine> vaccineRowMapper = ((resultSet, i) -> {
        Vaccine vaccine = new Vaccine();
        vaccine.setId(resultSet.getInt("id"));
        vaccine.setName(resultSet.getString("name"));
        vaccine.setType(resultSet.getString("type"));
        vaccine.setStorageTemperature(resultSet.getInt("storage_temperature"));
        vaccine.setAgeLimitMin(resultSet.getInt("age_limit_min"));
        vaccine.setAgeLimitMax(resultSet.getInt("age_limit_max"));
        vaccine.setShotsNeeded(resultSet.getInt("shots_needed"));
        vaccine.setDaysUntilNextShot(resultSet.getInt("days_until_next_shot"));
        vaccine.setNextShotId(resultSet.getInt("next_shot_id"));
        vaccine.setFullyVaccinatedTimePeriod(resultSet.getInt("fully_vaccinated_time_period"));
        vaccine.setApplicable(resultSet.getBoolean("is_applicable"));
        vaccine.setApplicableForPregnant(resultSet.getBoolean("is_applicable_for_pregnant"));
        vaccine.setApplicableForChronic(resultSet.getBoolean("is_applicable_for_chronic"));
        return vaccine;
    });

    @Autowired
    public VaccineRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        init();
    }

    public List<Vaccine> getVaccines() {
        String sql = "SELECT * FROM vaccines;";
        try {
            return jdbc.query(sql, vaccineRowMapper);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    public Vaccine getVaccine(int id) {
        String sql = "SELECT * FROM vaccines WHERE id = ?;";
        try {
            return jdbc.queryForObject(sql, vaccineRowMapper, id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public boolean createVaccine(VaccineCreateData data) {
        String sql = "INSERT INTO vaccines (" +
                "name, " +
                "type, " +
                "storage_temperature, " +
                "age_limit_min, " +
                "age_limit_max, " +
                "shots_needed, " +
                "days_until_next_shot, " +
                "next_shot_id, " +
                "fully_vaccinated_time_period, " +
                "is_applicable, " +
                "is_applicable_for_pregnant, " +
                "is_applicable_for_chronic) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getName(),
                    data.getType(),
                    data.getStorageTemperature(),
                    data.getAgeLimitMin(),
                    data.getAgeLimitMax(),
                    data.getShotsNeeded(),
                    data.getDaysUntilNextShot(),
                    data.getNextShotId(),
                    data.getFullyVaccinatedTimePeriod(),
                    data.isApplicable(),
                    data.isApplicableForPregnant(),
                    data.isApplicableForChronic()
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean updateVaccine(int id, VaccineCreateData data) {
        String sql = "UPDATE vaccines SET" +
                "name = ?, " +
                "type = ?, " +
                "storage_temperature = ?, " +
                "age_limit_min = ?, " +
                "age_limit_max = ?, " +
                "shots_needed = ?, " +
                "days_until_next_shot = ?, " +
                "next_shot_id = ?, " +
                "fully_vaccinated_time_period = ?, " +
                "is_applicable = ?, " +
                "is_applicable_for_pregnant = ?, " +
                "is_applicable_for_chronic) = ? " +
                "WHERE id = ?;";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getName(),
                    data.getType(),
                    data.getStorageTemperature(),
                    data.getAgeLimitMin(),
                    data.getAgeLimitMax(),
                    data.getShotsNeeded(),
                    data.getDaysUntilNextShot(),
                    data.getNextShotId(),
                    data.getFullyVaccinatedTimePeriod(),
                    data.isApplicable(),
                    data.isApplicableForPregnant(),
                    data.isApplicableForChronic(),
                    id
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean deleteVaccine(int id) {
        String sql = "UPDATE vaccines " +
                "SET is_available = FALSE " +
                "WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }

    private void init() {
        jdbc.execute(VaccineInit.VACCINE_DROP_TABLE);
        jdbc.execute(VaccineInit.VACCINE_INIT_TABLE);
        jdbc.execute(VaccineInit.VACCINE_DATA_INSERT);
    }
}
