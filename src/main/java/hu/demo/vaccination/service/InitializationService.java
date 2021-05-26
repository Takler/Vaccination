package hu.demo.vaccination.service;

import hu.demo.vaccination.repository.InitializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InitializationService {
    private final InitializationRepository initializationRepository;

    @Autowired
    public InitializationService(InitializationRepository initializationRepository) {
        this.initializationRepository = initializationRepository;
    }

    public void initialization() {
        initializationRepository.initialization();
    }
}
