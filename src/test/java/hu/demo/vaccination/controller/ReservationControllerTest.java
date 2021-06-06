package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationInfoData;
import hu.demo.vaccination.dto.reservation.ReservationNameInfoData;
import hu.demo.vaccination.service.ReservationService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static hu.demo.vaccination.config.PatientTestHelper.*;
import static hu.demo.vaccination.config.ReservationTestHelper.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

    @MockBean
    private ReservationService reservationServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/reservation/patientid/123123123 - getPatientReservation - correct")
    void test_getPatientReservation_receiveCorrectPatientReservationData() {
        PatientReservationData patientReservationData = getPatientReservationDataOne();

        doReturn(patientReservationData).when(reservationServiceMock).getPatientReservation(PATIENT_RESERVATION_1_PATIENT_ID);

        try {
            mockMvc.perform(get("/api/reservation/patientid/123123123"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("reservationId", is(PATIENT_RESERVATION_1_RESERVATION_ID)))
                    .andExpect(jsonPath("patientId", is(PATIENT_RESERVATION_1_PATIENT_ID)))
                    .andExpect(jsonPath("patientName", is(PATIENT_RESERVATION_1_PATIENT_NAME)))
                    .andExpect(jsonPath("centerName", is(PATIENT_RESERVATION_1_CENTER_NAME)))
                    .andExpect(jsonPath("vaccineName", is(PATIENT_RESERVATION_1_VACCINE_NAME)))
                    .andExpect(jsonPath("registration", is(PATIENT_RESERVATION_1_REGISTRATION.toString())))
                    .andExpect(jsonPath("nextShot", is(PATIENT_RESERVATION_1_NEXT_SHOT.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).getPatientReservation(PATIENT_RESERVATION_1_PATIENT_ID);
    }

    @Test
    @DisplayName("GET /api/reservation/patientid/123123123 - getPatientReservation - no result")
    void test_getPatientReservation_noResult() {
        doReturn(null).when(reservationServiceMock).getPatientReservation(PATIENT_RESERVATION_1_PATIENT_ID);

        try {
            mockMvc.perform(get("/api/reservation/patientid/123123123"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).getPatientReservation(PATIENT_RESERVATION_1_PATIENT_ID);
    }

    @Test
    @DisplayName("GET /api/reservation/info/1 - getInfo - correct")
    void test_getInfo_receiveCorrectInfoData() {
        ReservationInfoData reservationInfoData = getReservationInfoDataOne();

        doReturn(reservationInfoData).when(reservationServiceMock).getInfo(RESERVATION_INFO_1_RESERVATION_ID);

        try {
            mockMvc.perform(get("/api/reservation/info/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("id", is(RESERVATION_NAME_INFO_1_ID)))
                    .andExpect(jsonPath("patient.id", is(PATIENT_1_ID)))
                    .andExpect(jsonPath("patient.firstName", is(PATIENT_1_FIRST_NAME)))
                    .andExpect(jsonPath("patient.lastName", is(PATIENT_1_LAST_NAME)))
                    .andExpect(jsonPath("patient.mothersName", is(PATIENT_1_MOTHERS_NAME)))
                    .andExpect(jsonPath("patient.gender", is(PATIENT_1_GENDER)))
                    .andExpect(jsonPath("patient.dateOfBirth", is(PATIENT_1_BIRTH_DATE.toString())))
                    .andExpect(jsonPath("patient.email", is(PATIENT_1_EMAIL)))
                    .andExpect(jsonPath("patient.city", is(PATIENT_1_CITY)))
                    .andExpect(jsonPath("patient.zipCode", is(PATIENT_1_ZIP_CODE)))
                    .andExpect(jsonPath("patient.address", is(PATIENT_1_ADDRESS)))
                    .andExpect(jsonPath("patient.telephoneNumber", is(PATIENT_1_TELEPHONE_NUMBER)))
                    .andExpect(jsonPath("patient.pregnant", is(PATIENT_1_PREGNANT)))
                    .andExpect(jsonPath("patient.underlyingMedicalCondition", is(PATIENT_1_CHRONIC)))

                    .andExpect(jsonPath("center.id", is(reservationInfoData.getCenter().getId())))
                    .andExpect(jsonPath("center.name", is(reservationInfoData.getCenter().getName())))
                    .andExpect(jsonPath("center.city", is(reservationInfoData.getCenter().getCity())))
                    .andExpect(jsonPath("center.email", is(reservationInfoData.getCenter().getEmail())))
                    .andExpect(jsonPath("center.telephoneNumber", is(reservationInfoData.getCenter().getTelephoneNumber())))
                    .andExpect(jsonPath("center.dailyCapacity", is(reservationInfoData.getCenter().getDailyCapacity())))

                    .andExpect(jsonPath("vaccine.id", is(reservationInfoData.getVaccine().getId())))
                    .andExpect(jsonPath("vaccine.name", is(reservationInfoData.getVaccine().getName())))
                    .andExpect(jsonPath("vaccine.type", is(reservationInfoData.getVaccine().getType())))
                    .andExpect(jsonPath("vaccine.storageTemperature", is(reservationInfoData.getVaccine().getStorageTemperature())))
                    .andExpect(jsonPath("vaccine.ageLimitMin", is(reservationInfoData.getVaccine().getAgeLimitMin())))
                    .andExpect(jsonPath("vaccine.ageLimitMax", is(reservationInfoData.getVaccine().getAgeLimitMax())))
                    .andExpect(jsonPath("vaccine.shotsNeeded", is(reservationInfoData.getVaccine().getShotsNeeded())))
                    .andExpect(jsonPath("vaccine.daysUntilNextShot", is(reservationInfoData.getVaccine().getDaysUntilNextShot())))
                    .andExpect(jsonPath("vaccine.nextShotId", is(reservationInfoData.getVaccine().getNextShotId())))
                    .andExpect(jsonPath("vaccine.fullyVaccinatedTimePeriod", is(reservationInfoData.getVaccine().getFullyVaccinatedTimePeriod())))
                    .andExpect(jsonPath("vaccine.applicable", is(reservationInfoData.getVaccine().isApplicable())))
                    .andExpect(jsonPath("vaccine.applicableForPregnant", is(reservationInfoData.getVaccine().isApplicableForPregnant())))
                    .andExpect(jsonPath("vaccine.applicableForChronic", is(reservationInfoData.getVaccine().isApplicableForChronic())))

                    .andExpect(jsonPath("registration", is(RESERVATION_INFO_1_REGISTRATION.toString())))
                    .andExpect(jsonPath("nextShot", is(RESERVATION_INFO_1_NEXT_SHOT.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).getInfo(RESERVATION_INFO_1_RESERVATION_ID);
    }

    @Test
    @DisplayName("GET /api/reservation/info/1 - getInfo - no result")
    void test_getInfo_noResult() {
        doReturn(null).when(reservationServiceMock).getInfo(RESERVATION_INFO_1_RESERVATION_ID);

        try {
            mockMvc.perform(get("/api/reservation/info/1"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).getInfo(RESERVATION_INFO_1_RESERVATION_ID);
    }

    @Test
    @DisplayName("GET /api/reservation/nameinfo/1 - getNameInfo - correct")
    void test_getNameInfo_receiveCorrectInfoData() {
        ReservationNameInfoData nameInfoData = getReservationNameInfoDataOne();

        doReturn(nameInfoData).when(reservationServiceMock).getNameInfo(RESERVATION_NAME_INFO_1_ID);

        try {
            mockMvc.perform(get("/api/reservation/nameinfo/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("id", is(RESERVATION_NAME_INFO_1_ID)))
                    .andExpect(jsonPath("patientName", is(RESERVATION_NAME_INFO_1_PATIENT_NAME)))
                    .andExpect(jsonPath("centerName", is(RESERVATION_NAME_INFO_1_CENTER_NAME)))
                    .andExpect(jsonPath("vaccineName", is(RESERVATION_NAME_INFO_1_VACCINE_NAME)))
                    .andExpect(jsonPath("registration", is(RESERVATION_NAME_INFO_1_REGISTRATION.toString())))
                    .andExpect(jsonPath("nextShot", is(RESERVATION_NAME_INFO_1_NEXT_SHOT.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).getNameInfo(RESERVATION_NAME_INFO_1_ID);
    }

    @Test
    @DisplayName("GET /api/reservation/nameinfo/1 - getNameInfo - no result")
    void test_getNameInfo_noResult() {
        doReturn(null).when(reservationServiceMock).getNameInfo(RESERVATION_NAME_INFO_1_ID);

        try {
            mockMvc.perform(get("/api/reservation/nameinfo/1"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).getNameInfo(RESERVATION_NAME_INFO_1_ID);
    }

    @Test
    @DisplayName("GET /api/reservation - findall - correct")
    void test_findAll_receiveCorrectReservationData() {
        Reservation reservationOne = getReservationOne();
        Reservation reservationTwo = getReservationTwo();

        doReturn(Lists.newArrayList(reservationOne, reservationTwo)).when(reservationServiceMock).findAll();

        try {
            mockMvc.perform(get("/api/reservation"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", is(RESERVATION_1_ID)))
                    .andExpect(jsonPath("$[0].patientId", is(RESERVATION_1_PATIENT_ID)))
                    .andExpect(jsonPath("$[0].centerId", is(RESERVATION_1_CENTER_ID)))
                    .andExpect(jsonPath("$[0].vaccineId", is(RESERVATION_1_VACCINE_ID)))
                    .andExpect(jsonPath("$[0].registration", is(RESERVATION_1_REGISTRATION.toString())))
                    .andExpect(jsonPath("$[0].nextShot", is(RESERVATION_1_NEXT_SHOT.toString())))

                    .andExpect(jsonPath("$[1].id", is(RESERVATION_2_ID)))
                    .andExpect(jsonPath("$[1].patientId", is(RESERVATION_2_PATIENT_ID)))
                    .andExpect(jsonPath("$[1].centerId", is(RESERVATION_2_CENTER_ID)))
                    .andExpect(jsonPath("$[1].vaccineId", is(RESERVATION_2_VACCINE_ID)))
                    .andExpect(jsonPath("$[1].registration", is(RESERVATION_2_REGISTRATION.toString())))
                    .andExpect(jsonPath("$[1].nextShot", is(RESERVATION_2_NEXT_SHOT.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).findAll();
    }

    @Test
    @DisplayName("GET /api/reservation - findall - no result")
    void test_findAll_noResult() {
        doReturn(Collections.emptyList()).when(reservationServiceMock).findAll();

        try {
            mockMvc.perform(get("/api/reservation"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).findAll();
    }

    @Test
    @DisplayName("GET /api/reservation/1 - getById - correct")
    void test_getById_receiveCorrectReservationData() {
        Reservation reservation = getReservationOne();

        doReturn(reservation).when(reservationServiceMock).getById(RESERVATION_1_ID);

        try {
            mockMvc.perform(get("/api/reservation/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("id", is(RESERVATION_1_ID)))
                    .andExpect(jsonPath("patientId", is(RESERVATION_1_PATIENT_ID)))
                    .andExpect(jsonPath("centerId", is(RESERVATION_1_CENTER_ID)))
                    .andExpect(jsonPath("vaccineId", is(RESERVATION_1_VACCINE_ID)))
                    .andExpect(jsonPath("registration", is(RESERVATION_1_REGISTRATION.toString())))
                    .andExpect(jsonPath("nextShot", is(RESERVATION_1_NEXT_SHOT.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).getById(RESERVATION_1_ID);
    }

    @Test
    @DisplayName("GET /api/reservation/1 - getById - no result")
    void test_getById_noResult() {
        doReturn(null).when(reservationServiceMock).getById(RESERVATION_1_ID);

        try {
            mockMvc.perform(get("/api/reservation/1"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(reservationServiceMock).getById(RESERVATION_1_ID);
    }
}
