package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.ReservationCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ReservationRepository { //TODO: implement the logic for ReservationRepository

    private final JdbcTemplate jdbc;

    @Autowired
    public ReservationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Reservation> getReservations() {
        return Collections.emptyList();
    }

    public Reservation getReservation(int id) {
        return new Reservation();
    }

    public boolean createReservation(ReservationCreateData data) {
        return false;
    }

    public boolean updateReservation(int id, ReservationCreateData data) {
        return false;
    }

    public boolean deleteReservation(int id) {
        return false;
    }
}
