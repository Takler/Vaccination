package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.ReservationCreateData;
import hu.demo.vaccination.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.getReservations();
    }

    public Reservation getReservation(int id) {
        return reservationRepository.getReservation();
    }

    public boolean createReservation(ReservationCreateData data) {
        return reservationRepository.createReservation(data);
    }

    public boolean updateReservation(int id, ReservationCreateData data) {
        return reservationRepository.updateReservation(id, data);
    }

    public boolean deleteReservation(int id) {
        return reservationRepository.deleteReservation(id);
    }
}
