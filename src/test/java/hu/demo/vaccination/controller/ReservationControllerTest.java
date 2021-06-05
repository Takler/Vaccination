package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Reservation;
import hu.demo.vaccination.dto.reservation.PatientReservationData;
import hu.demo.vaccination.dto.reservation.ReservationInfoData;
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

import static hu.demo.vaccination.config.ReservationTestHelper.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @MockBean
    private ReservationService reservationServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/reservation/patientid/123123123 - getPatientReservation")
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
    }

    @Test
    @DisplayName("GET /api/reservation/info/1 - getInfo")
    void test_getInfo_receiveCorrectInfoData() {
        ReservationInfoData reservationInfoData = getReservationInfoDataOne();

        doReturn(reservationInfoData).when(reservationServiceMock).getInfo(RESERVATION_INFO_1_RESERVATION_ID);

        try {
            mockMvc.perform(get("/api/reservation/info/1"))
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
    }

    @Test
    @DisplayName("GET /api/reservation/nameinfo/1 - getNameInfo")
    void test_getNameInfo_receiveCorrectInfoData() {
       //TODO
    }

    @Test
    @DisplayName("GET /api/reservation - findall")
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
    }

    @Test
    @DisplayName("GET /api/reservation/1 -getById")
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
    }
}
