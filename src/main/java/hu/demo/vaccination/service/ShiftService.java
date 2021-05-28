package hu.demo.vaccination.service;

import hu.demo.vaccination.dto.shift.ShiftInfoData;
import hu.demo.vaccination.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftService implements Requestable{

    private ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public ShiftInfoData getShiftInfo(int shiftId){
        return shiftRepository.getShiftInfo(shiftId);
    }

    @Override
    public String getName(int id) {
        return null;
    }


}
