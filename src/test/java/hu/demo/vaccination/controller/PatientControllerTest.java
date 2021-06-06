package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.service.PatientService;
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
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @MockBean
    private PatientService patientServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/patient/lastnames/albert - getLastNames - correct")
    void test_getLastNames_getCorrectLastNames() {
        String firstName = "albert";
        doReturn(Lists.newArrayList(PATIENT_1_LAST_NAME, PATIENT_2_LAST_NAME))
                .when(patientServiceMock).getLastNames(firstName);

        try {
            mockMvc.perform(get("/api/patient/lastnames/albert"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0]", is(PATIENT_1_LAST_NAME)))
                    .andExpect(jsonPath("$[1]", is(PATIENT_2_LAST_NAME)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(patientServiceMock).getLastNames(firstName);
    }

    @Test
    @DisplayName("GET /api/patient/lastnames/albert - getLastNames - no result")
    void test_getLastNames_noResult() {
        String firstName = "albert";
        doReturn(Lists.emptyList()).when(patientServiceMock).getLastNames(firstName);

        try {
            mockMvc.perform(get("/api/patient/lastnames/albert"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(patientServiceMock).getLastNames(firstName);
    }

    @Test
    @DisplayName("GET /api/patient/name/748237274 - getName - correct")
    void test_getName_getCorrectName() {
        String name = PATIENT_1_FIRST_NAME + " " + PATIENT_1_LAST_NAME;

        doReturn(name).when(patientServiceMock).getName(PATIENT_1_ID);

        try {
            mockMvc.perform(get("/api/patient/name/748237274"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString(name)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(patientServiceMock).getName(PATIENT_1_ID);
    }

    @Test
    @DisplayName("GET /api/patient/name/748237274 - getName - no result")
    void test_getName_noResult() {
        doReturn("").when(patientServiceMock).getName(PATIENT_1_ID);

        try {
            mockMvc.perform(get("/api/patient/name/748237274"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(patientServiceMock).getName(PATIENT_1_ID);
    }

    @Test
    @DisplayName("GET /api/patient - findAll - correct")
    void test_findAll_receiveCorrectPatientData() {
        Patient patientOne = getPatientOne();
        Patient patientTwo = getPatientTwo();

        doReturn(Lists.newArrayList(patientOne, patientTwo)).when(patientServiceMock).findAll();

        try {
            mockMvc.perform(get("/api/patient"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", is(PATIENT_1_ID)))
                    .andExpect(jsonPath("$[0].firstName", is(PATIENT_1_FIRST_NAME)))
                    .andExpect(jsonPath("$[0].lastName", is(PATIENT_1_LAST_NAME)))
                    .andExpect(jsonPath("$[0].mothersName", is(PATIENT_1_MOTHERS_NAME)))
                    .andExpect(jsonPath("$[0].gender", is(PATIENT_1_GENDER)))
                    .andExpect(jsonPath("$[0].dateOfBirth", is(PATIENT_1_BIRTH_DATE.toString())))
                    .andExpect(jsonPath("$[0].email", is(PATIENT_1_EMAIL)))
                    .andExpect(jsonPath("$[0].city", is(PATIENT_1_CITY)))
                    .andExpect(jsonPath("$[0].zipCode", is(PATIENT_1_ZIP_CODE)))
                    .andExpect(jsonPath("$[0].address", is(PATIENT_1_ADDRESS)))
                    .andExpect(jsonPath("$[0].telephoneNumber", is(PATIENT_1_TELEPHONE_NUMBER)))
                    .andExpect(jsonPath("$[0].pregnant", is(PATIENT_1_PREGNANT)))
                    .andExpect(jsonPath("$[0].underlyingMedicalCondition", is(PATIENT_1_CHRONIC)))

                    .andExpect(jsonPath("$[1].id", is(PATIENT_2_ID)))
                    .andExpect(jsonPath("$[1].firstName", is(PATIENT_2_FIRST_NAME)))
                    .andExpect(jsonPath("$[1].lastName", is(PATIENT_2_LAST_NAME)))
                    .andExpect(jsonPath("$[1].mothersName", is(PATIENT_2_MOTHERS_NAME)))
                    .andExpect(jsonPath("$[1].gender", is(PATIENT_2_GENDER)))
                    .andExpect(jsonPath("$[1].dateOfBirth", is(PATIENT_2_BIRTH_DATE.toString())))
                    .andExpect(jsonPath("$[1].email", is(PATIENT_2_EMAIL)))
                    .andExpect(jsonPath("$[1].city", is(PATIENT_2_CITY)))
                    .andExpect(jsonPath("$[1].zipCode", is(PATIENT_2_ZIP_CODE)))
                    .andExpect(jsonPath("$[1].address", is(PATIENT_2_ADDRESS)))
                    .andExpect(jsonPath("$[1].telephoneNumber", is(PATIENT_2_TELEPHONE_NUMBER)))
                    .andExpect(jsonPath("$[1].pregnant", is(PATIENT_2_PREGNANT)))
                    .andExpect(jsonPath("$[1].underlyingMedicalCondition", is(PATIENT_2_CHRONIC)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(patientServiceMock).findAll();
    }

    @Test
    @DisplayName("GET /api/patient - findAll - no result")
    void test_findAll_noResult() {
        doReturn(Collections.emptyList()).when(patientServiceMock).findAll();

        try {
            mockMvc.perform(get("/api/patient"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(patientServiceMock).findAll();
    }

    @Test
    @DisplayName("GET /api/patient/748237274 - getById - correct")
    void test_getById_receiveCorrectPatientData() {
        Patient patientOne = getPatientOne();

        doReturn(patientOne).when(patientServiceMock).getById(PATIENT_1_ID);

        try {
            mockMvc.perform(get("/api/patient/748237274"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("id", is(PATIENT_1_ID)))
                    .andExpect(jsonPath("firstName", is(PATIENT_1_FIRST_NAME)))
                    .andExpect(jsonPath("lastName", is(PATIENT_1_LAST_NAME)))
                    .andExpect(jsonPath("mothersName", is(PATIENT_1_MOTHERS_NAME)))
                    .andExpect(jsonPath("gender", is(PATIENT_1_GENDER)))
                    .andExpect(jsonPath("dateOfBirth", is(PATIENT_1_BIRTH_DATE.toString())))
                    .andExpect(jsonPath("email", is(PATIENT_1_EMAIL)))
                    .andExpect(jsonPath("city", is(PATIENT_1_CITY)))
                    .andExpect(jsonPath("zipCode", is(PATIENT_1_ZIP_CODE)))
                    .andExpect(jsonPath("address", is(PATIENT_1_ADDRESS)))
                    .andExpect(jsonPath("telephoneNumber", is(PATIENT_1_TELEPHONE_NUMBER)))
                    .andExpect(jsonPath("pregnant", is(PATIENT_1_PREGNANT)))
                    .andExpect(jsonPath("underlyingMedicalCondition", is(PATIENT_1_CHRONIC)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(patientServiceMock).getById(PATIENT_1_ID);
    }

    @Test
    @DisplayName("GET /api/patient/748237274 - getById - no result")
    void test_getById_noResult() {
        doReturn(null).when(patientServiceMock).getById(PATIENT_1_ID);

        try {
            mockMvc.perform(get("/api/patient/748237274"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(patientServiceMock).getById(PATIENT_1_ID);
    }
}
