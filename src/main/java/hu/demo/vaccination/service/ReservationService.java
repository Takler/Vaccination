package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;
import hu.demo.vaccination.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CenterService centerService;
    private final VaccineService vaccineService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CenterService centerService, VaccineService vaccineService) {
        this.reservationRepository = reservationRepository;
        this.centerService = centerService;
        this.vaccineService = vaccineService;
    }

    public PatientReservationData getPatientReservation(int patientId) {
        PatientReservationData patientReservation = reservationRepository.getPatientReservation(patientId);
        Reservation reservation = reservationRepository.getReservation(patientReservation.getReservationId());

        patientReservation.setCenterName(centerService.getCenter(reservation.getCenterId()).getName());
        patientReservation.setVaccineName(vaccineService.getVaccine(reservation.getVaccineId()).getName());

        return patientReservation;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.getReservations();
    }

    public Reservation getReservation(int id) {
        return reservationRepository.getReservation(id);
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
