package hu.demo.vaccination.service;

import hu.demo.vaccination.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepositoryMock;

    @BeforeEach
    void init() {
        doctorService = new DoctorService(doctorRepositoryMock);
    }

    @Test
    void test_getLastNames_receiveLastName() {
        String lastName = "Gipsz";
        String firstName = "Jakab";
        List<String> returnedLastNames = new ArrayList<>();
        returnedLastNames.add(lastName);

        when(doctorRepositoryMock.getLastName(firstName)).thenReturn(returnedLastNames);

        List<String> lastNames = doctorService.getLastNames(firstName);
        assertEquals(lastName, lastNames.get(0));
        verify(doctorRepositoryMock).getLastName(firstName);
    }
}
