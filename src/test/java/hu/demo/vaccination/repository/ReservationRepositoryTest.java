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

import static hu.demo.vaccination.config.ReservationTestHelper.getSecondSampleReservationCreateData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        ReservationCreateData originalData = getSecondSampleReservationCreateData();

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
    void test_findall_noReservationsExists_returnsEmptyList() {
        assertEquals(0, reservationRepository.findAll().size());
    }

    @Test
    void test_getById_sameReservationDataReceived() {
        ReservationCreateData originalData = getSecondSampleReservationCreateData();

        reservationRepository.save(originalData);
        Reservation result = reservationRepository.getById(1);

        assertEquals(originalData.getPatientId(), result.getPatientId());
        assertEquals(originalData.getCenterId(), result.getCenterId());
        assertEquals(originalData.getVaccineId(), result.getVaccineId());
        assertEquals(originalData.getRegistration(), result.getRegistration());
        assertEquals(originalData.getNextShot(), result.getNextShot());
    }

    @Test
    void test_update_modifiedDataReceived() {
        ReservationCreateData originalData = getSecondSampleReservationCreateData();

        ReservationCreateData updatedData = new ReservationCreateData();
        updatedData.setPatientId(248248264);
        updatedData.setCenterId(23);
        updatedData.setVaccineId(2);
        LocalDate updatedRegistration = originalData.getRegistration().plusDays(20);
        LocalDate updatedNextShot = originalData.getNextShot().plusDays(20);
        updatedData.setRegistration(updatedRegistration);
        updatedData.setNextShot(updatedNextShot);

        reservationRepository.save(originalData);
        reservationRepository.update(1, updatedData);
        Reservation result = reservationRepository.getById(1);

        assertEquals(updatedData.getPatientId(), result.getPatientId());
        assertEquals(updatedData.getCenterId(), result.getCenterId());
        assertEquals(updatedData.getVaccineId(), result.getVaccineId());
        assertEquals(updatedData.getRegistration(), result.getRegistration());
        assertEquals(updatedData.getNextShot(), result.getNextShot());
    }

    @Test
    void test_delete_DeleteFieldModified_noResultFromGets() {
        ReservationCreateData originalData = getSecondSampleReservationCreateData();

        reservationRepository.save(originalData);
        reservationRepository.delete(1);

        assertEquals(0, reservationRepository.findAll().size());
    }


}
