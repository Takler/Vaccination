package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.InputCreateData;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import hu.demo.vaccination.repository.PatientRepository;
import hu.demo.vaccination.utility.FilePathDefinition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static hu.demo.vaccination.config.PatientTestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    private static final String SAVE_FILE = "TestPatientsSave.csv";
    private static final String LOAD_FILE = "TestPatientsLoad.csv";

    private PatientService patientService;

    @Mock
    private PatientRepository patientRepositoryMock;

    @BeforeEach
    void init() {
        patientService = new PatientService(patientRepositoryMock);
        initTestFiles();
    }

    @AfterEach
    void destruct() {
        removeTestFiles();
    }

    @Test
    void test_fileSave_successfulSave() {
        InputCreateData input = new InputCreateData();
        input.setInput(SAVE_FILE);

        List<Patient> patients = new ArrayList<>();
        Patient patientOne = getPatientOne();
        Patient patientTwo = getPatientTwo();

        patients.add(patientOne);
        patients.add(patientTwo);

        when(patientRepositoryMock.findAll()).thenReturn(patients);

        assertTrue(patientService.fileSave(input));

        List<Patient> resultPatients = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(Paths.get(FilePathDefinition.SAVE_PATH.getDefinition(), SAVE_FILE)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Patient readPatient = new Patient();
                String[] values = line.split(";");
                readPatient.setId(Integer.parseInt(values[0]));
                readPatient.setFirstName(values[1]);
                readPatient.setLastName(values[2]);
                readPatient.setMothersName(values[3]);
                readPatient.setGender(values[4]);
                readPatient.setDateOfBirth(LocalDate.parse(values[5]));
                readPatient.setEmail(values[6]);
                readPatient.setCity(values[7]);
                readPatient.setZipCode(values[8]);
                readPatient.setAddress(values[9]);
                readPatient.setTelephoneNumber(values[10]);
                readPatient.setPregnant(Boolean.parseBoolean(values[11]));
                readPatient.setUnderlyingMedicalCondition(Boolean.parseBoolean(values[12]));
                resultPatients.add(readPatient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(2, resultPatients.size());

        Patient firstResultPatient;
        Patient secondResultPatient;

        if (patients.get(0).getId() == PATIENT_1_ID) {
            firstResultPatient = patients.get(0);
            secondResultPatient = patients.get(1);
        } else {
            firstResultPatient = patients.get(1);
            secondResultPatient = patients.get(0);
        }

        assertEquals(patientOne, firstResultPatient);
        assertEquals(patientTwo, secondResultPatient);
        verify(patientRepositoryMock).findAll();
    }

    @Test
    void test_fileLoad_successfulLoad() {
        InputCreateData input = new InputCreateData();
        input.setInput(LOAD_FILE);

        PatientCreateData patientOne = getPatientOneCreateData();
        PatientCreateData patientTwo = getPatientTwoCreateData();

        when(patientRepositoryMock.save(patientOne)).thenReturn(true);
        when(patientRepositoryMock.save(patientTwo)).thenReturn(true);

        assertTrue(patientService.fileLoad(input));

        verify(patientRepositoryMock).save(patientOne);
        verify(patientRepositoryMock).save(patientTwo);
    }

    @Test
    void test_getLastName_receiveLastName() {
        String lastName = "Test";
        String firstName = "Elek";
        List<String> lastNames = new ArrayList<>();
        lastNames.add(lastName);

        when(patientRepositoryMock.getLastName(firstName)).thenReturn(lastNames);

        List<String> receivedLastNames = patientService.getLastNames(firstName);
        assertEquals(lastName, receivedLastNames.get(0));
        verify(patientRepositoryMock).getLastName(firstName);
    }

    @Test
    void test_getName_receiveName() {
        String name = "Test Elek";
        int id = 1;

        when(patientRepositoryMock.getName(id)).thenReturn(name);

        assertEquals(name, patientService.getName(id));
        verify(patientRepositoryMock).getName(id);

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
        verify(patientRepositoryMock).findAll();
    }

    @Test
    void test_getById_receiveCorrectPatient() {
        Patient patient = getPatientOne();

        when(patientRepositoryMock.getById(patient.getId())).thenReturn(patient);

        Patient receivedPatient = patientService.getById(patient.getId());
        assertEquals(patient, receivedPatient);
        verify(patientRepositoryMock).getById(patient.getId());
    }

    @Test
    void test_save_onceCalled() {
        PatientCreateData patientCreateData = getPatientOneCreateData();

        when(patientRepositoryMock.save(patientCreateData)).thenReturn(true);

        assertTrue(patientService.save(patientCreateData));
        verify(patientRepositoryMock).save(patientCreateData);
    }

    @Test
    void test_delete_onceCalled() {
        int id = getPatientOne().getId();

        when(patientRepositoryMock.delete(id)).thenReturn(true);

        assertTrue(patientService.delete(id));
        verify(patientRepositoryMock).delete(id);
    }

    @Test
    void test_update_onceCalled() {
        PatientCreateData patientCreateData = getPatientOneCreateData();
        int id = patientCreateData.getId();

        when(patientRepositoryMock.update(id, patientCreateData)).thenReturn(true);

        assertTrue(patientService.update(id, patientCreateData));
        verify(patientRepositoryMock).update(patientCreateData.getId(), patientCreateData);
    }

    private void initTestFiles() {
        removeTestFiles();

        try (BufferedWriter bufferedWriter = new BufferedWriter(Files.newBufferedWriter(Paths.get(FilePathDefinition.SAVE_PATH.getDefinition(), LOAD_FILE)))) {
            bufferedWriter.write("748237274;Frigyes;Csonka;Prohászka Adél;male;1980-01-22;frigyes.csonka@email.com;Ács;2941;Munkácsy Mihály út 14.;0634388544;false;false");
            bufferedWriter.newLine();
            bufferedWriter.write("248248264;Virág;Szakáts;Vörös Hermina;female;1970-06-11;virag70@email.com;Budapest;1149;Árpád fejedelem útja 51.;0618659140;true;true");
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeTestFiles() {
        try {
            Files.deleteIfExists(Path.of(FilePathDefinition.SAVE_PATH.getDefinition(), SAVE_FILE));
            Files.deleteIfExists(Path.of(FilePathDefinition.SAVE_PATH.getDefinition(), LOAD_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
