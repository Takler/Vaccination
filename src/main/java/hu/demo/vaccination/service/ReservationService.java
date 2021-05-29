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
public class ReservationService implements CrudOperation<Reservation, ReservationCreateData> {
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
        patientReservation.setVaccineName(vaccineService.getById(reservation.getVaccineId()).getName());

        return patientReservation;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.getReservations();
    }

    @Override
    public Reservation getById(int id) {
        return reservationRepository.getReservation(id);
    }

    @Override
    public boolean save(ReservationCreateData data) {
        return reservationRepository.createReservation(data);
    }

    @Override
    public boolean update(int id, ReservationCreateData data) {
        return reservationRepository.updateReservation(id, data);
    }

    @Override
    public boolean delete(int id) {
        return reservationRepository.deleteReservation(id);
    }
}
