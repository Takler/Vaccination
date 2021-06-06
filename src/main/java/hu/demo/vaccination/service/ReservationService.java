package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;
import hu.demo.vaccination.dto.reservation.ReservationInfoData;
import hu.demo.vaccination.dto.reservation.ReservationNameInfoData;
import hu.demo.vaccination.repository.ReservationRepository;
import hu.demo.vaccination.service.interfaces.InfoOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService implements InfoOperation<Reservation, ReservationCreateData, ReservationInfoData, ReservationNameInfoData> {
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
        if (patientReservation == null) {
            return null;
        }

        Reservation reservation = reservationRepository.getById(patientReservation.getReservationId());
        if (reservation == null) {
            return null;
        }

        patientReservation.setPatientId(patientId);
        patientReservation.setPatientName(patientService.getName(patientId));
        patientReservation.setCenterName(centerService.getName(reservation.getCenterId()));
        patientReservation.setVaccineName(vaccineService.getName(reservation.getVaccineId()));
        return patientReservation;
    }

    @Override
    public ReservationInfoData getInfo(int id) {
        Reservation reservation = reservationRepository.getById(id);
        if (reservation == null) {
            return null;
        }

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
        Reservation reservation = reservationRepository.getById(id);
        if (reservation == null) {
            return null;
        }

        ReservationNameInfoData reservationNameInfoData = new ReservationNameInfoData();

        reservationNameInfoData.setId(id);
        reservationNameInfoData.setPatientName(patientService.getName(reservation.getPatientId()));
        reservationNameInfoData.setCenterName(centerService.getName(reservation.getCenterId()));
        reservationNameInfoData.setVaccineName(vaccineService.getName(reservation.getVaccineId()));
        reservationNameInfoData.setRegistration(reservation.getRegistration());
        reservationNameInfoData.setNextShot(reservation.getNextShot());

        return reservationNameInfoData;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getById(int id) {
        return reservationRepository.getById(id);
    }

    @Override
    public boolean save(ReservationCreateData data) {
        return reservationRepository.save(data);
    }

    @Override
    public boolean update(int id, ReservationCreateData data) {
        return reservationRepository.update(id, data);
    }

    @Override
    public boolean delete(int id) {
        return reservationRepository.delete(id);
    }
}
