package hu.demo.vaccination.service;

import hu.demo.vaccination.config.PatientTestHelper;
import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.domain.Vaccine;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationCreateData;
import hu.demo.vaccination.dto.reservation.ReservationInfoData;
import hu.demo.vaccination.dto.reservation.ReservationNameInfoData;
import hu.demo.vaccination.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static hu.demo.vaccination.config.ReservationTestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    private static final String CENTER_NAME = "Honvéd Kórház";
    private static final String VACCINE_NAME = "Pfizer";
    private static final String PATIENT_NAME = "Test Elek";

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
        Reservation originalReservation = getSampleReservation();

        PatientReservationData originalPatientReservationData = new PatientReservationData();
        originalPatientReservationData.setReservationId(RESERVATION_ID);
        originalPatientReservationData.setRegistration(originalReservation.getRegistration());
        originalPatientReservationData.setNextShot(originalPatientReservationData.getNextShot());

        when(reservationRepositoryMock.getById(RESERVATION_ID)).thenReturn(originalReservation);
        when(reservationRepositoryMock.getPatientReservation(PATIENT_ID)).thenReturn(originalPatientReservationData);
        when(centerServiceMock.getName(CENTER_ID)).thenReturn(CENTER_NAME);
        when(vaccineServiceMock.getName(VACCINE_ID)).thenReturn(VACCINE_NAME);
        when(patientServiceMock.getName(PATIENT_ID)).thenReturn(PATIENT_NAME);

        PatientReservationData resultData = reservationService.getPatientReservation(PATIENT_ID);

        assertEquals(CENTER_NAME, resultData.getCenterName());
        assertEquals(VACCINE_NAME, resultData.getVaccineName());
        assertEquals(PATIENT_NAME, resultData.getPatientName());
        verify(reservationRepositoryMock, times(1)).getById(RESERVATION_ID);
        verify(centerServiceMock, times(1)).getName(CENTER_ID);
        verify(vaccineServiceMock, times(1)).getName(VACCINE_ID);
        verify(patientServiceMock, times(1)).getName(PATIENT_ID);

    }

    @Test
    void test_getInfo_receiveCorrectData() {
        Reservation sampleReservation = getSampleReservation();
        Patient samplePatient = PatientTestHelper.getPatientOne();
        samplePatient.setId(PATIENT_ID);

        Center sampleCenter = new Center();
        sampleCenter.setId(CENTER_ID);
        sampleCenter.setName(CENTER_NAME);
        sampleCenter.setCity("Budapest");
        sampleCenter.setEmail("honved@honved.hu");
        sampleCenter.setTelephoneNumber("+3619877651");
        sampleCenter.setDailyCapacity(1000);

        Vaccine sampleVaccine = new Vaccine(VACCINE_ID, VACCINE_NAME, "mRNA", -70, 16, 999,
                2, 1, 28, 42, true, true, true);

        when(reservationRepositoryMock.getById(sampleReservation.getId())).thenReturn(sampleReservation);
        when(patientServiceMock.getById(samplePatient.getId())).thenReturn(samplePatient);
        when(centerServiceMock.getById(sampleCenter.getId())).thenReturn(sampleCenter);
        when(vaccineServiceMock.getById(sampleVaccine.getId())).thenReturn(sampleVaccine);

        ReservationInfoData resultData = reservationService.getInfo(RESERVATION_ID);
        assertEquals(samplePatient, resultData.getPatient());
        assertEquals(sampleCenter, resultData.getCenter());
        assertEquals(sampleVaccine, resultData.getVaccine());
        verify(reservationRepositoryMock, times(1)).getById(RESERVATION_ID);
        verify(patientServiceMock, times(1)).getById(samplePatient.getId());
        verify(centerServiceMock, times(1)).getById(sampleCenter.getId());
        verify(vaccineServiceMock, times(1)).getById(sampleVaccine.getId());
    }

    @Test
    void test_getNameInfo_receiveCorrectData() {
        Reservation sampleReservation = getSampleReservation();

        when(reservationRepositoryMock.getById(RESERVATION_ID)).thenReturn(sampleReservation);
        when(patientServiceMock.getName(PATIENT_ID)).thenReturn(PATIENT_NAME);
        when(centerServiceMock.getName(CENTER_ID)).thenReturn(CENTER_NAME);
        when(vaccineServiceMock.getName(VACCINE_ID)).thenReturn(VACCINE_NAME);

        ReservationNameInfoData resultData = reservationService.getNameInfo(RESERVATION_ID);

        assertEquals(CENTER_NAME, resultData.getCenterName());
        assertEquals(VACCINE_NAME, resultData.getVaccineName());
        assertEquals(PATIENT_NAME, resultData.getPatientName());
        assertEquals(sampleReservation.getRegistration(), resultData.getRegistration());
        assertEquals(sampleReservation.getNextShot(), resultData.getNextShot());
        verify(reservationRepositoryMock, times(1)).getById(RESERVATION_ID);
        verify(centerServiceMock, times(1)).getName(CENTER_ID);
        verify(vaccineServiceMock, times(1)).getName(VACCINE_ID);
        verify(patientServiceMock, times(1)).getName(PATIENT_ID);
    }

    @Test
    void test_findAll_receiveAllReservations() {
        Reservation testReservation = getSampleReservation();

        when(reservationRepositoryMock.findAll()).thenReturn(List.of(testReservation));

        List<Reservation> resultReservations = reservationService.findAll();

        assertEquals(1, resultReservations.size());
        assertEquals(testReservation, resultReservations.get(0));
        verify(reservationRepositoryMock, times(1)).findAll();

    }

    @Test
    void test_getById_receiveOneReservation() {
        Reservation testReservation = getSampleReservation();
        int id = testReservation.getId();

        when(reservationRepositoryMock.getById(id)).thenReturn(testReservation);

        Reservation resultReservation = reservationService.getById(id);

        assertEquals(testReservation, resultReservation);
        verify(reservationRepositoryMock, times(1)).getById(id);

    }

    @Test
    void test_save_onceCalled() {
        ReservationCreateData testData = getSampleReservationCreateData();

        when(reservationRepositoryMock.save(testData)).thenReturn(true);

        assertTrue(reservationService.save(testData));
        verify(reservationRepositoryMock, times(1)).save(testData);
    }

    @Test
    void test_update_onceCalled() {
        ReservationCreateData testData = getSampleReservationCreateData();

        when(reservationRepositoryMock.update(OTHER_ID, testData)).thenReturn(true);

        assertTrue(reservationService.update(OTHER_ID, testData));
        verify(reservationRepositoryMock, times(1)).update(OTHER_ID, testData);
    }

    @Test
    void test_delete_onceCalled() {
        when(reservationRepositoryMock.delete(OTHER_ID)).thenReturn(true);

        assertTrue(reservationService.delete(OTHER_ID));
        verify(reservationRepositoryMock, times(1)).delete(OTHER_ID);
    }

}
