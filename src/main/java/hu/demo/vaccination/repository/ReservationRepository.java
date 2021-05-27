package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.ReservationCreateData;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ReservationRepository {

    private final JdbcTemplate jdbc;

    private final RowMapper<Reservation> reservationRowMapper = ((resultSet, i) -> {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getInt("id"));
        reservation.setPatientId(resultSet.getInt("patient_id"));
        reservation.setCenterId(resultSet.getInt("center_id"));
        reservation.setVaccineId(resultSet.getInt("vaccine_id"));
        reservation.setRegistration(resultSet.getDate("registration").toLocalDate());
        reservation.setNextShot(resultSet.getDate("next_shot").toLocalDate());
        return reservation;
    });

    @Autowired
    public ReservationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public PatientReservationData getPatientReservation(int patientId) {
        // TODO implement this!
        return new PatientReservationData();
    }

    public List<Reservation> getReservations() {
        String sql = "SELECT id, patient_id, center_id, vaccine_id, registration, next_shot " +
                "FROM reservation " +
                "WHERE deleted = false";
        try {
            return jdbc.query(sql, reservationRowMapper);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    public Reservation getReservation(int id) {
        String sql = "SELECT id, patient_id, center_id, vaccine_id, registration, next_shot " +
                "FROM reservation " +
                "WHERE id = ? AND deleted = false";
        try {
            return jdbc.queryForObject(sql, reservationRowMapper, id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public boolean createReservation(ReservationCreateData data) {
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
            return false;
        }

    }

    public boolean updateReservation(int id, ReservationCreateData data) {
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
            return false;
        }
    }

    public boolean deleteReservation(int id) {
        String sql = "UPDATE reservation SET deleted = ? WHERE id = ?";
        try {
            int rowsAffected = jdbc.update(sql, true, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
