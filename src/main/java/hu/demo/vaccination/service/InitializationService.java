package hu.demo.vaccination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InitializationService {
    private final InitializationService initializationService;

    @Autowired
    public InitializationService(InitializationService initializationService) {
        this.initializationService = initializationService;
    }

    public void initialization() {
        initializationService.initialization();
    }
}
