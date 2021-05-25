package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.dto.CenterCreateData;
import hu.demo.vaccination.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CenterService {
    private final CenterRepository centerRepository;

    @Autowired
    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public List<Center> getCenters() {
        return centerRepository.getCenters();
    }

    public Center getCenter(int id) {
        return centerRepository.getCenter(id);
    }

    public boolean createCenter(CenterCreateData data) {
        return centerRepository.createCenter(data);
    }

    public boolean updateCenter(int id, CenterCreateData data) {
        return centerRepository.updateCenter(id, data);
    }

    public boolean deleteCenter(int id) {
        return centerRepository.deleteCenter(id);
    }
}
