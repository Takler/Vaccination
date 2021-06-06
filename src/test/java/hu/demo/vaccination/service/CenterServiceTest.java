package hu.demo.vaccination.service;

import hu.demo.vaccination.config.CenterTestHelper;
import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.dto.center.CenterCreateData;
import hu.demo.vaccination.repository.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        CenterCreateData testCenterCreateData = CenterTestHelper.getFirstTestCenterCreateData();

        when(centerRepositoryMock.createCenter(testCenterCreateData)).thenReturn(true);

        assertTrue(centerService.save(testCenterCreateData));
        verify(centerRepositoryMock, times(1)).createCenter(testCenterCreateData);
    }

    @Test
    void test_update() {
        CenterCreateData testCenterCreateData = CenterTestHelper.getSecondTestCenterCreateData();
        int id = CenterTestHelper.ID_SECOND;

        when(centerRepositoryMock.updateCenter(id, testCenterCreateData)).thenReturn(true);

        assertTrue(centerService.update(id, testCenterCreateData));
        verify(centerRepositoryMock, times(1)).updateCenter(id, testCenterCreateData);
    }

    @Test
    void test_delete() {
        // TODO
        Center testCenter = CenterTestHelper.getFirstTestCenter();

        when(centerRepositoryMock.deleteCenter(testCenter.getId())).thenReturn(true);

        assertTrue(centerService.delete(testCenter.getId()));
        verify(centerRepositoryMock, times(1)).deleteCenter(testCenter.getId());
    }

}