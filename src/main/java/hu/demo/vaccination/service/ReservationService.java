package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;
import hu.demo.vaccination.dto.reservation.ReservationInfoData;
import hu.demo.vaccination.dto.reservation.ReservationNameInfoData;
import hu.demo.vaccination.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService implements CrudOperation<Reservation, ReservationCreateData>, InfoOperation<ReservationInfoData, ReservationNameInfoData> {
    private final ReservationRepository reservationRepository;
    private final CenterService centerService;
    private final VaccineService vaccineService;
    private final PatientService patientService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CenterService centerService, VaccineService vaccineService, PatientService patientService) {
        this.reservationRepository = reservationRepository;
        this.centerService = centerService;
        this.vaccineService = vaccineService;
        this.patientService = patientService;
    }

    public PatientReservationData getPatientReservation(int patientId) {
        PatientReservationData patientReservation = reservationRepository.getPatientReservation(patientId);
        Reservation reservation = reservationRepository.getReservation(patientReservation.getReservationId());

        patientReservation.setPatientId(patientId);
        patientReservation.setPatientName(patientService.getName(patientId));
        patientReservation.setCenterName(centerService.getName(reservation.getCenterId()));
        patientReservation.setVaccineName(vaccineService.getById(reservation.getVaccineId()).getName());

        return patientReservation;
    }

    @Override
    public ReservationInfoData getInfo(int id) {
        Reservation reservation = reservationRepository.getReservation(id);

        ReservationInfoData reservationInfoData = new ReservationInfoData();

        reservationInfoData.setId(id);
        reservationInfoData.setPatient(patientService.getById(reservation.getPatientId()));
        reservationInfoData.setCenter(centerService.getById(reservation.getCenterId()));
        reservationInfoData.setVaccine(vaccineService.getById(reservation.getVaccineId()));
        reservationInfoData.setRegistration(reservation.getRegistration());
        reservationInfoData.setNextShot(reservation.getNextShot());

        return reservationInfoData;
    }

    @Override
    public ReservationNameInfoData getNameInfo(int id) {
        Reservation reservation = reservationRepository.getReservation(id);

        ReservationNameInfoData reservationNameInfoData = new ReservationNameInfoData();

        reservationNameInfoData.setId(id);
        reservationNameInfoData.setPatientName(patientService.getName(reservation.getPatientId()));
        reservationNameInfoData.setCenterName(centerService.getName(reservation.getCenterId()));
        reservationNameInfoData.setVaccineName(vaccineService.getById(reservation.getVaccineId()).getName());
        reservationNameInfoData.setRegistration(reservation.getRegistration());
        reservationNameInfoData.setNextShot(reservation.getNextShot());

        return reservationNameInfoData;
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
