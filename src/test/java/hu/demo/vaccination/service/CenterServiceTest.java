package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.repository.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CenterServiceTest {
    private CenterService centerService;

    @Mock
    private CenterRepository centerRepositoryMock;

    @BeforeEach
    void init() {
        centerService = new CenterService(centerRepositoryMock);
    }

    @Test
    void test_getName() {
        Center testCenter = CenterTestHelper.getFirstTestCenter();
        when(centerRepositoryMock.getName(testCenter.getId())).thenReturn(testCenter.getName());

        assertEquals(testCenter.getName(), centerService.getName(testCenter.getId()));
    }

    @Test
    void test_findAll_size() {
        List<Center> testCenters = new ArrayList<>();
        testCenters.add(CenterTestHelper.getFirstTestCenter());
        testCenters.add(CenterTestHelper.getSecondTestCenter());

        when(centerRepositoryMock.getCenters()).thenReturn(testCenters);

        List<Center> result = centerService.findAll();
        assertEquals(2, result.size());
        verify(centerRepositoryMock, times(1)).getCenters();
    }

    @Test
    void test_getById() {
        Center testCenter = CenterTestHelper.getFirstTestCenter();

        when(centerRepositoryMock.getCenter(testCenter.getId())).thenReturn(testCenter);

        Center result = centerService.getById(testCenter.getId());
        assertEquals(testCenter, result);
        verify(centerRepositoryMock, times(1)).getCenter(testCenter.getId());
    }

    @Test
    void test_save() {
        // TODO
    }

    @Test
    void test_update() {
        // TODO
    }

    @Test
    void test_delete() {
        // TODO
    }

    private static final class CenterTestHelper {

        private CenterTestHelper() {
        }

        public static Center getFirstTestCenter() {
            Center testCenter = new Center();
            testCenter.setId(1);
            testCenter.setName("Test Hospital");
            testCenter.setCity("Testville");
            testCenter.setEmail("test@hospital.com");
            testCenter.setTelephoneNumber("003617654321");
            testCenter.setDailyCapacity(500);
            testCenter.setDeleted(false);
            return testCenter;
        }

        public static Center getSecondTestCenter() {
            Center testCenter = new Center();
            testCenter.setId(2);
            testCenter.setName("Testpital");
            testCenter.setCity("Tomorrowland");
            testCenter.setEmail("testpital@test.com");
            testCenter.setTelephoneNumber("+3611234567");
            testCenter.setDailyCapacity(800);
            testCenter.setDeleted(false);
            return testCenter;
        }
    }
}