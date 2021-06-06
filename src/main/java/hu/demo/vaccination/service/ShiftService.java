package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.dto.InputCreateData;
import hu.demo.vaccination.dto.shift.ShiftCreateUpdateData;
import hu.demo.vaccination.dto.shift.ShiftInfoData;
import hu.demo.vaccination.dto.shift.ShiftNameInfoData;
import hu.demo.vaccination.repository.ShiftRepository;
import hu.demo.vaccination.service.interfaces.FileHandler;
import hu.demo.vaccination.service.interfaces.InfoOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class ShiftService implements InfoOperation<Shift, ShiftCreateUpdateData, ShiftInfoData, ShiftNameInfoData>, FileHandler { //TODO: Requestable??? Object is a placeholder for ShiftNameData

    //TODO   dátumkezelés beállítása, számít implenets<> -ben a típussorrend?

    private ShiftRepository shiftRepository;
    private DoctorService doctorService;
    private CenterService centerService;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository, DoctorService doctorService, CenterService centerService) {
        this.shiftRepository = shiftRepository;
        this.doctorService = doctorService;
        this.centerService = centerService;
    }

    // implement CrudOperation methods

    @Override
    public List<Shift> findAll() {      // TODO Interface<Shift> ,miért "eszi" meg?
        return shiftRepository.findAll();
    }

    @Override
    public Shift getById(int shiftId) {
        return shiftRepository.getById(shiftId);
    }

    @Override
    public boolean save(ShiftCreateUpdateData shiftCreateUpdateData) {
        return shiftRepository.save(shiftCreateUpdateData);
    }

    @Override
    public boolean update(int shiftId, ShiftCreateUpdateData shiftCreateUpdateData) {
        return shiftRepository.update(shiftId, shiftCreateUpdateData);
    }

    @Override
    public boolean delete(int shiftId) {
        return shiftRepository.delete(shiftId);
    }

    // implement InfoOperation methods

    @Override
    public ShiftInfoData getInfo(int shiftId) {    //TODO Nagy ellenőrzés
        ShiftInfoData shiftInfoData = new ShiftInfoData();
        Shift shift = shiftRepository.getShift(shiftId);
        Doctor doctor = doctorService.getById(shift.getDoctorId());
        Center center = centerService.getById(shift.getCenterId());

        shiftInfoData.setId(shiftId);
        shiftInfoData.setStart(shift.getStart());
        shiftInfoData.setEnd(shift.getEnd());
        shiftInfoData.setDeleted(shift.isDeleted());
        shiftInfoData.setDoctor(doctor);
        shiftInfoData.setCenter(center);
        return shiftInfoData;
    }

    @Override
    public ShiftNameInfoData getNameInfo(int shiftId) {     //TODO Nagy ellenőrzés
        ShiftNameInfoData shiftNameInfoData = new ShiftNameInfoData();
        Shift shift = shiftRepository.getShift(shiftId);
        Doctor doctor = doctorService.getById(shift.getDoctorId());  //egész rekord kényszer a rossz getNameByID miatt...

        shiftNameInfoData.setId(shiftId);
        shiftNameInfoData.setStart(shift.getStart());
        shiftNameInfoData.setEnd(shift.getEnd());
        shiftNameInfoData.setDeleted(shift.isDeleted());
        shiftNameInfoData.setDoctorFirstName(doctor.getFirstName());
        shiftNameInfoData.setDoctorLastName(doctor.getLastName());
        shiftNameInfoData.setCenterName(centerService.getName(shift.getCenterId()));  //getByIdOnlyName -nak kéne lennie?
        return shiftNameInfoData;
    }

    @Override
    public boolean fileSave(InputCreateData input) {
        Path path = Paths.get(input.getInput());
        List<Shift> shiftList = shiftRepository.findAll();
        try {
            for (Shift item : shiftList) {
                Files.writeString(path, item.toString() + "\r\n", StandardOpenOption.APPEND);  //TODO létrehozás nem ok.
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
        Path path = Paths.get(input.getInput());
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            ShiftCreateUpdateData shiftCreateUpdateData = new ShiftCreateUpdateData();
            String row = "";
            while (((row = bufferedReader.readLine()) != null) && result) {
                String[] attribs = row.split(";");
                shiftCreateUpdateData.setCenterId(Integer.parseInt(attribs[1]));
                shiftCreateUpdateData.setDoctorId(Integer.parseInt(attribs[2]));
                shiftCreateUpdateData.setStart(Timestamp.valueOf(attribs[3]));
                shiftCreateUpdateData.setEnd(Timestamp.valueOf(attribs[4]));
                if (!shiftRepository.save(shiftCreateUpdateData)) {
                    result = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
