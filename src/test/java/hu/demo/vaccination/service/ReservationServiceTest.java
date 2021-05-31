package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepositoryMock;
    @Mock
    private CenterService centerServiceMock;
    @Mock
    private VaccineService vaccineServiceMock;
    @Mock
    private PatientService patientServiceMock;

    @BeforeEach
    void init() {
        reservationService = new ReservationService(reservationRepositoryMock, centerServiceMock, vaccineServiceMock, patientServiceMock);
    }

    @Test
    void test_getPatientReservation_receiveCorrectData() {
        int patientId = 123123123;
        int otherId = 1;
        String centerName = "Honvéd Kórház";
        String vaccineName = "Pzizer";
        String patientName = "Test Elek";

        Reservation originalReservation = new Reservation();
        originalReservation.setId(otherId);
        originalReservation.setCenterId(otherId);
        originalReservation.setVaccineId(otherId);
        originalReservation.setPatientId(patientId);
        originalReservation.setRegistration(LocalDate.now());
        originalReservation.setNextShot(LocalDate.now().plusDays(14));

        PatientReservationData originalPatientReservationData = new PatientReservationData();
        originalPatientReservationData.setReservationId(otherId);
        originalPatientReservationData.setRegistration(originalReservation.getRegistration());
        originalPatientReservationData.setNextShot(originalPatientReservationData.getNextShot());

        when(reservationRepositoryMock.getById(otherId)).thenReturn(originalReservation);
        when(reservationRepositoryMock.getPatientReservation(patientId)).thenReturn(originalPatientReservationData);
        when(centerServiceMock.getName(otherId)).thenReturn(centerName);
        when(vaccineServiceMock.getName(otherId)).thenReturn(vaccineName);
        when(patientServiceMock.getName(patientId)).thenReturn(patientName);

        PatientReservationData resultData = reservationService.getPatientReservation(patientId);

        assertEquals(centerName, resultData.getCenterName());
        assertEquals(vaccineName, resultData.getVaccineName());
        assertEquals(patientName, resultData.getPatientName());
        verify(reservationRepositoryMock, times(1)).getById(otherId);
        verify(centerServiceMock, times(1)).getName(otherId);
        verify(vaccineServiceMock, times(1)).getName(otherId);
        verify(patientServiceMock, times(1)).getName(patientId);

    }


}
