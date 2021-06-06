package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;
import hu.demo.vaccination.utility.DataDefinition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static hu.demo.vaccination.config.ReservationTestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class ReservationRepositoryTest {

    @Autowired
    private JdbcTemplate jdbc;

    private ReservationRepository reservationRepository;

    @BeforeEach
    void init() {
        reservationRepository = new ReservationRepository(jdbc);
        jdbc.execute(DataDefinition.RESERVATION_DROP_TABLE.getDefinition());
        jdbc.execute(DataDefinition.PATIENT_DROP_TABLE.getDefinition());
        jdbc.execute(DataDefinition.CENTER_DROP_TABLE.getDefinition());
        jdbc.execute(DataDefinition.VACCINE_DROP_TABLE.getDefinition());

        jdbc.execute(DataDefinition.PATIENT_CREATE_TABLE.getDefinition());
        jdbc.execute(DataDefinition.CENTER_CREATE_TABLE.getDefinition());
        jdbc.execute(DataDefinition.VACCINE_CREATE_TABLE.getDefinition());

        jdbc.execute(DataDefinition.CENTER_INSERT_SAMPLE_DATA.getDefinition());
        jdbc.execute(DataDefinition.VACCINE_INSERT_SAMPLE_DATA.getDefinition());
        jdbc.execute(DataDefinition.PATIENT_INSERT_SAMPLE_DATA.getDefinition());

        jdbc.execute(DataDefinition.RESERVATION_CREATE_TABLE.getDefinition());
    }

    @AfterEach
    void destruct() {
        jdbc.execute(DataDefinition.RESERVATION_DROP_TABLE.getDefinition());
    }

    @Test
    void test_getPatientReservation_correctDataReceived() {
        ReservationCreateData originalData = getReservationTwoCreateData();

        reservationRepository.save(originalData);
        PatientReservationData result = reservationRepository.getPatientReservation(originalData.getPatientId());

        assertEquals(1, result.getReservationId());
        assertEquals(originalData.getRegistration(), result.getRegistration());
        assertEquals(originalData.getNextShot(), result.getNextShot());
        assertNull(result.getPatientName());
        assertNull(result.getCenterName());
        assertNull(result.getVaccineName());
    }

    @Test
    void test_getPatientReservation_noResult() {
        assertNull(reservationRepository.getPatientReservation(PATIENT_RESERVATION_1_PATIENT_ID));
    }

    @Test
    void test_findAll_returnsCorrectData() {
        Reservation expectedReservation = getReservationTwo();
        expectedReservation.setId(1);

        reservationRepository.save(getReservationTwoCreateData());

        List<Reservation> reservations = reservationRepository.findAll();
        assertEquals(1, reservations.size());
        assertEquals(expectedReservation, reservations.get(0));
    }

    @Test
    void test_findAll_noReservationsExists_returnsEmptyList() {
        assertEquals(0, reservationRepository.findAll().size());
    }

    @Test
    void test_getById_sameReservationDataReceived() {
        ReservationCreateData originalData = getReservationTwoCreateData();

        reservationRepository.save(originalData);
        Reservation result = reservationRepository.getById(1);

        assertEquals(originalData.getPatientId(), result.getPatientId());
        assertEquals(originalData.getCenterId(), result.getCenterId());
        assertEquals(originalData.getVaccineId(), result.getVaccineId());
        assertEquals(originalData.getRegistration(), result.getRegistration());
        assertEquals(originalData.getNextShot(), result.getNextShot());
    }

    @Test
    void test_getById_noResult() {
        assertNull(reservationRepository.getById(1));
    }

    @Test
    void test_update_modifiedDataReceived() {
        ReservationCreateData originalData = getReservationTwoCreateData();

        LocalDate updatedRegistration = originalData.getRegistration().plusDays(20);
        LocalDate updatedNextShot = originalData.getNextShot().plusDays(20);
        ReservationCreateData updatedData = new ReservationCreateData(248248264, 23, 2,
                updatedRegistration, updatedNextShot);

        reservationRepository.save(originalData);
        assertTrue(reservationRepository.update(1, updatedData));
        Reservation result = reservationRepository.getById(1);

        assertEquals(updatedData.getPatientId(), result.getPatientId());
        assertEquals(updatedData.getCenterId(), result.getCenterId());
        assertEquals(updatedData.getVaccineId(), result.getVaccineId());
        assertEquals(updatedData.getRegistration(), result.getRegistration());
        assertEquals(updatedData.getNextShot(), result.getNextShot());
    }

    @Test
    void test_update_noResult() {
        assertFalse(reservationRepository.update(1, getReservationTwoCreateData()));
    }

    @Test
    void test_delete_DeleteFieldModified_noResultFromGets() {
        ReservationCreateData originalData = getReservationTwoCreateData();

        reservationRepository.save(originalData);
        assertTrue(reservationRepository.delete(1));

        assertEquals(0, reservationRepository.findAll().size());
    }

    @Test
    void test_delete_noResult() {
        assertFalse(reservationRepository.delete(1));
    }

}
