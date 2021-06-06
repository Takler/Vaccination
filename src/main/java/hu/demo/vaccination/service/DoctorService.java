package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.dto.InputCreateData;
import hu.demo.vaccination.dto.doctor.DoctorCreateUpdateData;
import hu.demo.vaccination.repository.DoctorRepository;
import hu.demo.vaccination.service.interfaces.CrudOperation;
import hu.demo.vaccination.service.interfaces.FileHandler;
import hu.demo.vaccination.service.interfaces.LastNameable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Slf4j
@Transactional
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
        List<Doctor> doctorList = doctorRepository.findAll();
        try {
            for (Doctor item : doctorList) {
                Files.writeString(path, item.toString() + "\r\n", StandardOpenOption.APPEND);
            }
            return true;
        } catch (IOException ex) {
            log.error("fileSave exception: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean fileLoad(InputCreateData input) {
        boolean result = true;
        Path path = Paths.get(input.getInput());   //TODO hol vannak a lokális változók létrehozva, használat előtt vagy egységesen a metódus elején
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            DoctorCreateUpdateData doctorCreateUpdateData = new DoctorCreateUpdateData();
            String row = "";
            while (((row = bufferedReader.readLine()) != null) && result) {    //TODO külön metódus? ... van megszokott neve?
                String[] attribs = row.split(";");
                doctorCreateUpdateData.setFirstName(attribs[1]);
                doctorCreateUpdateData.setLastName(attribs[2]);
                doctorCreateUpdateData.setEmail(attribs[3]);
                doctorCreateUpdateData.setAddress(attribs[4]);
                doctorCreateUpdateData.setTelephoneNumber(attribs[5]);
                if (!doctorRepository.save(doctorCreateUpdateData)) {
                    result = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
