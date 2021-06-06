package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.dto.InputCreateData;
import hu.demo.vaccination.dto.doctor.DoctorCreateUpdateData;
import hu.demo.vaccination.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
public class DoctorService implements CrudOperation<Doctor, DoctorCreateUpdateData>, LastNameable, FileHandler {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getById(int doctorId) {
        return doctorRepository.getById(doctorId);
    }

    @Override
    public boolean save(DoctorCreateUpdateData doctorCreateUpdateData) {
        return doctorRepository.save(doctorCreateUpdateData);
    }

    @Override   // TODO Miért adjuk meg külön az id -t?
    public boolean update(int doctorId, DoctorCreateUpdateData doctorCreateUpdateData) {
        return doctorRepository.update(doctorId, doctorCreateUpdateData);
    }

    @Override
    public boolean delete(int doctorId) {
        return doctorRepository.delete(doctorId);
    }

    @Override
    public String getName(int doctorId) {
        return doctorRepository.getName(doctorId);
    }

    @Override
    public List<String> getLastNames(String doctorFirstName) {
        return doctorRepository.getLastName(doctorFirstName);
    }

    @Override
    public boolean fileSave(InputCreateData input) {
        Path path = Paths.get(input.getInput());
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            while (doctorRepository.fileSave() != null) {
            }

        } catch (IOException ex) {
            log.error("fileSave exception: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean fileLoad(InputCreateData input) {
        return false;
    }
}
