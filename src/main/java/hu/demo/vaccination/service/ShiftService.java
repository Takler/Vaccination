package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.domain.Doctor;
import hu.demo.vaccination.domain.Shift;
import hu.demo.vaccination.dto.shift.ShiftCreateData;
import hu.demo.vaccination.dto.shift.ShiftInfoData;
import hu.demo.vaccination.dto.shift.ShiftNameInfoData;
import hu.demo.vaccination.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService implements CrudOperation<Shift, ShiftCreateData>, Requestable, InfoOperation {

    private ShiftRepository shiftRepository;
    private DoctorService doctorService;
    private CenterService centerService;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository, DoctorService doctorService, CenterService centerService) {
        this.shiftRepository = shiftRepository;
        this.doctorService = doctorService;
        this.centerService = centerService;
    }

    //implement CrudOperation

    @Override  //OK , no test
    public Shift getById(int shiftId) {
        return shiftRepository.getById(shiftId);
    }

    @Override
    public List<Shift> findAll() {  // OK, no test    // Interface<Shift> ,miért "eszi" meg?
        return shiftRepository.findAll();
    }

    @Override
    public boolean save(ShiftCreateData shiftCreateData) {    // Ok, no test    //INSERT
        return shiftRepository.save(shiftCreateData);
    }

    @Override
    public boolean update(int shiftId, ShiftCreateData shiftCreateData) {
        return shiftRepository.update(shiftId, shiftCreateData);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    //implement InfoOperation

    @Override
    public ShiftInfoData getInfo(int shiftId) {
        ShiftInfoData shiftInfoData = new ShiftInfoData();
        Shift shift = shiftRepository.getShift(shiftId);
        Doctor doctor = doctorService.getById(shift.getDoctor_id());
        Center center = centerService.getById(shift.getCenter_id());

        shiftInfoData.setId(shiftId);
        shiftInfoData.setStart(shift.getStart());
        shiftInfoData.setEnd(shift.getEnd());
        shiftInfoData.setDeleted(shift.isDeleted());
        shiftInfoData.setDoctor(doctor);
        shiftInfoData.setCenter(center);
        return shiftInfoData;
    }

    @Override
    public ShiftNameInfoData getNameInfo(int shiftId) {
        ShiftNameInfoData shiftNameInfoData = new ShiftNameInfoData();
        Shift shift = shiftRepository.getShift(shiftId);
        Doctor doctor = doctorService.getById(shift.getDoctor_id());  //egész rekord kényszer a rossz getNameByID miatt...

        shiftNameInfoData.setId(shiftId);
        shiftNameInfoData.setStart(shift.getStart());
        shiftNameInfoData.setEnd(shift.getEnd());
        shiftNameInfoData.setDeleted(shift.isDeleted());
        shiftNameInfoData.setDoctorFirstName(doctor.getFirstName());
        shiftNameInfoData.setDoctorLastName(doctor.getLastName());
        shiftNameInfoData.setCenterName(centerService.getName(shift.getCenter_id()));  //getByIdOnlyName -nak kéne lennie?
        return shiftNameInfoData;
    }
//        Field[] fields = shift.getClass().getFields();
//        for (int i = 0; i < fields.length; i++) {
//            shiftNameInfoData.
//        }


//    public ShiftNameInfoData getShiftNameInfo(int shiftId) {
//
//
//        Shift shift = shiftRepository.getShift(shiftId);
//        shiftInfoData.setStart(shift.getStart());
//        shiftInfoData.setEnd(shift.getEnd());
//
//        DoctorCreate doctorCreate = doctorService.getDoctor(shift.getDoctor_id());
//        shiftInfoData.setDoctorFirstName(doctorCreate.getFirstName());
//        shiftInfoData.setDoctorLastName(doctorCreate.getLastName());
//
//
//        return shiftInfoData;
//    }

    @Override
    public String getName(int id) {
        return null;
    }


}
