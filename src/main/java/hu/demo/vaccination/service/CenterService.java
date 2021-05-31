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
public class CenterService implements CrudOperation<Center, CenterCreateData>, Requestable {
    private final CenterRepository centerRepository;

    @Autowired
    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public String getName(int id) {
        return centerRepository.getName(id);
    }

    public List<Center> findAll() {
        return centerRepository.getCenters();
    }

    public Center getById(int id) {
        return centerRepository.getCenter(id);
    }

    public boolean save(CenterCreateData data) {
        return centerRepository.createCenter(data);
    }

    public boolean update(int id, CenterCreateData data) {
        return centerRepository.updateCenter(id, data);
    }

    public boolean delete(int id) {
        return centerRepository.deleteCenter(id);
    }
}
