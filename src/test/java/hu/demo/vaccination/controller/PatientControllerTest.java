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

import static hu.demo.vaccination.config.PatientTestHelper.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
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
    @DisplayName("GET /api/patient - findAll")
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
    }
}
