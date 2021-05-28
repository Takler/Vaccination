package hu.demo.vaccination.service;

import hu.demo.vaccination.dto.DoctorCreate;
import hu.demo.vaccination.dto.shift.ShiftData;
import hu.demo.vaccination.dto.shift.ShiftDateData;
import hu.demo.vaccination.dto.shift.ShiftInfoData;
import hu.demo.vaccination.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftService implements Requestable {

    private ShiftRepository shiftRepository;
    private DoctorService doctorService;
    private CenterService centerService;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository, DoctorService doctorService, CenterService centerService) {
        this.shiftRepository = shiftRepository;
        this.doctorService = doctorService;
        this.centerService = centerService;
    }


    public ShiftInfoData getShiftInfo(int shiftId) {
        ShiftInfoData shiftInfoData = new ShiftInfoData();

        ShiftData shiftData = shiftRepository.getShiftDate(shiftId);
        shiftInfoData.setStart(shiftData.getStart());
        shiftInfoData.setEnd(shiftData.getEnd());

        DoctorCreate doctorCreate = doctorService.getDoctor(shiftData.getDoctor_id());
        shiftInfoData.setDoctorFirstName(doctorCreate.getFirstName());
        shiftInfoData.setDoctorLastName(doctorCreate.getLastName());


        return shiftInfoData;
    }

    @Override
    public String getName(int id) {
        return null;
    }


}
