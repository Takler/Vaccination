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
    void test_getLastName_receiveLastName() {
        String lastName = "Test";
        String firstName = "Elek";
        List<String> lastNames = new ArrayList<>();
        lastNames.add(lastName);

        when(patientRepositoryMock.getLastName(firstName)).thenReturn(lastNames);

        List<String> receivedLastNames = patientService.getLastName(firstName);
        assertEquals(lastName, receivedLastNames.get(0));
        verify(patientRepositoryMock, times(1)).getLastName(firstName);
    }

    @Test
    void test_getName_receiveName() {
        String name = "Test Elek";
        int id = 1;

        when(patientRepositoryMock.getName(id)).thenReturn(name);

        assertEquals(name, patientService.getName(id));
        verify(patientRepositoryMock, times(1)).getName(id);

    }

    @Test
    void test_findAll_receiveAllPatients() {
        List<Patient> patients = new ArrayList<>();
        Patient patientOne = getPatientOne();
        Patient patientTwo = getPatientTwo();

        patients.add(patientOne);
        patients.add(patientTwo);

        when(patientRepositoryMock.findAll()).thenReturn(patients);

        List<Patient> receivedPatients = patientService.findAll();
        assertEquals(2, receivedPatients.size());
        verify(patientRepositoryMock, times(1)).findAll();
    }

    @Test
    void test_getById_receiveCorrectPatient() {
        Patient patient = getPatientOne();

        when(patientRepositoryMock.getById(patient.getId())).thenReturn(patient);

        Patient receivedPatient = patientService.getById(patient.getId());
        assertEquals(patient, receivedPatient);
        verify(patientRepositoryMock, times(1)).getById(patient.getId());
    }

    @Test
    void test_save_onceCalled() {
        PatientCreateData patientCreateData = getPatientOneCreateData();

        patientService.save(patientCreateData);

        verify(patientRepositoryMock, times(1)).save(patientCreateData);
    }

    @Test
    void test_delete_onceCalled() {
        int id = getPatientOne().getId();

        patientService.delete(id);

        verify(patientRepositoryMock, times(1)).delete(id);
    }

    @Test
    void test_update_onceCalled() {
        PatientCreateData patientCreateData = getPatientOneCreateData();

        patientService.update(patientCreateData.getId(), patientCreateData);

        verify(patientRepositoryMock, times(1)).update(patientCreateData.getId(), patientCreateData);
    }
}
