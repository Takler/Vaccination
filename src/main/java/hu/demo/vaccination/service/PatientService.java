package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Patient;
import hu.demo.vaccination.dto.InputCreateData;
import hu.demo.vaccination.dto.patient.PatientCreateData;
import hu.demo.vaccination.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PatientService implements CrudOperation<Patient, PatientCreateData>, LastNameable, FileHandler<PatientCreateData, InputCreateData> {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public boolean otherFileSave(InputCreateData input) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(Files.newBufferedWriter(Paths.get(input.getInput())))) {
            List<Patient> patients = patientRepository.findAll();
            if (!patients.isEmpty()) {
                for (Patient patient : patients) {
                    bufferedWriter.write(patient.toCsvFormat());
                    bufferedWriter.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean otherFileLoad(InputCreateData input) {
        try (BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(Paths.get(input.getInput())))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                PatientCreateData data = new PatientCreateData();
                String[] values = line.split(";");
                data.setId(Integer.parseInt(values[0]));
                data.setFirstName(values[1]);
                data.setLastName(values[2]);
                data.setMothersName(values[3]);
                data.setGender(values[4]);
                data.setDateOfBirth(LocalDate.parse(values[5]));
                data.setEmail(values[6]);
                data.setCity(values[7]);
                data.setZipCode(values[8]);
                data.setAddress(values[9]);
                data.setTelephoneNumber(values[10]);
                data.setPregnant(Boolean.parseBoolean(values[11]));
                data.setUnderlyingMedicalCondition(Boolean.parseBoolean(values[12]));
                if (!patientRepository.save(data)) {
                    return false;
                }
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean fileSave(PatientCreateData createData) {
        //TODO refactor interface for otherFileSave() method
        return false;
    }

    @Override
    public PatientCreateData fileLoad(InputCreateData input) {
        //TODO refactor interface for otherFileLoad() method
        return null;
    }

    @Override
    public List<String> getLastName(String firstName) {
        return patientRepository.getLastName(firstName);
    }

    @Override
    public String getName(int id) {
        return patientRepository.getName(id);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getById(int id) {
        return patientRepository.getById(id);
    }

    @Override
    public boolean save(PatientCreateData data) {
        return patientRepository.save(data);
    }

    @Override
    public boolean update(int id, PatientCreateData data) {
        return patientRepository.update(id, data);
    }

    @Override
    public boolean delete(int id) {
        return patientRepository.delete(id);
    }
}
