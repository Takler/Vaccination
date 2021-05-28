package hu.demo.vaccination.service;

import hu.demo.vaccination.dto.DoctorCreate;
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




    public ShiftInfoData getShift(int shiftId) {
        ShiftInfoData shiftData = new ShiftInfoData();
        ShiftDateData shiftDateData = shiftRepository.getShiftDateData(shiftId);
        shiftData.setStart(shiftDateData.getStart());
        shiftData.setEnd(shiftDateData.getEnd());
        DoctorCreate doctorCreate = doctorService.getDoctor()


        return shiftData;
    }

    @Override
    public String getName(int id) {
        return null;
    }


}
