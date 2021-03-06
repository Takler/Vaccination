package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class ReservationRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ReservationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public PatientReservationData getPatientReservation(int patientId) {
        String sql = "SELECT id, center_id, vaccine_id, registration, next_shot " +
                "FROM reservation " +
                "WHERE patient_id = ? AND deleted = false";
        try {
            return jdbc.queryForObject(sql, (resultSet, i) -> {
                PatientReservationData patientReservation = new PatientReservationData();
                patientReservation.setReservationId(resultSet.getInt("id"));
                patientReservation.setRegistration(resultSet.getDate("registration").toLocalDate());
                patientReservation.setNextShot(resultSet.getDate("next_shot").toLocalDate());
                return patientReservation;
            }, patientId);
        } catch (DataAccessException e) {
            log.error("getPatientReservation exception: " + e.getMessage());
            return null;
        }
    }

    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservation WHERE deleted = false";
        try {
            return jdbc.query(sql, new ReservationMapper());
        } catch (DataAccessException e) {
            log.error("findAll exception: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Reservation getById(int id) {
        String sql = "SELECT * FROM reservation WHERE id = ? AND deleted = false";
        try {
            return jdbc.queryForObject(sql, new ReservationMapper(), id);
        } catch (DataAccessException e) {
            log.error("getById exception: " + e.getMessage());
            return null;
        }
    }

    public boolean save(ReservationCreateData data) {
        String sql = "INSERT INTO reservation (" +
                "patient_id, " +
                "center_id, " +
                "vaccine_id, " +
                "registration, " +
                "next_shot) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getPatientId(),
                    data.getCenterId(),
                    data.getVaccineId(),
                    data.getRegistration(),
                    data.getNextShot()
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("save exception: " + e.getMessage());
            return false;
        }

    }

    public boolean update(int id, ReservationCreateData data) {
        String sql = "UPDATE reservation SET " +
                "patient_id = ?, " +
                "center_id = ?, " +
                "vaccine_id = ?, " +
                "registration = ?, " +
                "next_shot = ? " +
                "WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql,
                    data.getPatientId(),
                    data.getCenterId(),
                    data.getVaccineId(),
                    data.getRegistration(),
                    data.getNextShot(),
                    id
            );
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("update exception: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "UPDATE reservation SET deleted = ? WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql, true, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("delete exception: " + e.getMessage());
            return false;
        }
    }

    private static class ReservationMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
            Reservation reservation = new Reservation();
            reservation.setId(resultSet.getInt("id"));
            reservation.setPatientId(resultSet.getInt("patient_id"));
            reservation.setCenterId(resultSet.getInt("center_id"));
            reservation.setVaccineId(resultSet.getInt("vaccine_id"));
            reservation.setRegistration(resultSet.getDate("registration").toLocalDate());
            reservation.setNextShot(resultSet.getDate("next_shot").toLocalDate());
            reservation.setDeleted(resultSet.getBoolean("deleted"));
            return reservation;
        }
    }
}
