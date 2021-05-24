package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import hu.demo.vaccination.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static hu.demo.vaccination.config.PatientTestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    private PatientService patientService;

    @Mock
    private PatientRepository patientRepositoryMock;

    @BeforeEach
    void init() {
        patientService = new PatientService(patientRepositoryMock);
    }

    @Test
    void test_getAllPatients_receiveAllPatients() {
        List<Patient> patients = new ArrayList<>();
        Patient patientOne = getPatientOne();
        Patient patientTwo = getPatientTwo();

        patients.add(patientOne);
        patients.add(patientTwo);

        when(patientRepositoryMock.getPatients()).thenReturn(patients);

        List<Patient> receivedPatients = patientService.getPatients();
        assertEquals(2, receivedPatients.size());
        verify(patientRepositoryMock, times(1)).getPatients();
    }

    @Test
    void test_getPatientById_receiveCorrectPatient() {
        Patient patient = getPatientOne();

        when(patientRepositoryMock.getPatient(patient.getId())).thenReturn(patient);

        Patient receivedPatient = patientService.getPatient(patient.getId());
        assertEquals(patient, receivedPatient);
        verify(patientRepositoryMock, times(1)).getPatient(patient.getId());
    }

    @Test
    void test_addPatient_onceCalled() {
        PatientCreateData patientCreateData = getPatientOneCreateData();

        patientService.createPatient(patientCreateData);

        verify(patientRepositoryMock, times(1)).createPatient(patientCreateData);
    }

    @Test
    void test_deletePatient_onceCalled() {
        int id = getPatientOne().getId();

        patientService.deletePatient(id);

        verify(patientRepositoryMock, times(1)).deletePatient(id);
    }

    @Test
    void test_updatePatient_onceCalled() {
        PatientCreateData patientCreateData = getPatientOneCreateData();

        patientService.updatePatient(patientCreateData.getId(), patientCreateData);

        verify(patientRepositoryMock, times(1)).updatePatient(patientCreateData.getId(), patientCreateData);
    }
}
