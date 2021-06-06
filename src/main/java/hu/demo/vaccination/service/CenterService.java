package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.dto.InputCreateData;
import hu.demo.vaccination.dto.center.CenterCreateData;
import hu.demo.vaccination.repository.CenterRepository;
import hu.demo.vaccination.service.interfaces.CrudOperation;
import hu.demo.vaccination.service.interfaces.FileHandler;
import hu.demo.vaccination.service.interfaces.Nameable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CenterService implements CrudOperation<Center, CenterCreateData>, Nameable, FileHandler {
    private final CenterRepository centerRepository;

    @Autowired
    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public String getName(int id) {
        return centerRepository.getName(id);
    }

    @Override
    public List<Center> findAll() {
        return centerRepository.getCenters();
    }

    @Override
    public Center getById(int id) {
        return centerRepository.getCenter(id);
    }

    @Override
    public boolean save(CenterCreateData data) {
        return centerRepository.createCenter(data);
    }

    @Override
    public boolean update(int id, CenterCreateData data) {
        return centerRepository.updateCenter(id, data);
    }

    @Override
    public boolean delete(int id) {
        return centerRepository.deleteCenter(id);
    }

    @Override
    public boolean fileSave(InputCreateData input) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(Files.newBufferedWriter(Paths.get(input.getInput())))) {
            List<Center> centers = centerRepository.getCenters();
            for (Center center : centers) {
                bufferedWriter.write(convertToCsv(center));
                bufferedWriter.newLine();
            }
            return true;
        } catch (IOException e) {
            log.error("save failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean fileLoad(InputCreateData input) {
        // TODO
        return false;
    }

    private String convertToCsv(Center center) {
        return center.getId() + ";" + center.getName() + ";" + center.getCity() + ";" + center.getEmail() + ";" + center.getTelephoneNumber() + ";" + center.getDailyCapacity();
    }
}
