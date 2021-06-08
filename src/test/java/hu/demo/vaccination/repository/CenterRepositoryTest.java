package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.dto.center.CenterCreateData;
import hu.demo.vaccination.utility.DataDefinition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class CenterRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    CenterRepository centerRepository;

    @BeforeEach
    void init() {
        centerRepository = new CenterRepository(jdbcTemplate);
        jdbcTemplate.update(DataDefinition.CENTER_DROP_TABLE.getDefinition());
        jdbcTemplate.update(DataDefinition.CENTER_CREATE_TABLE.getDefinition());
        String sql = "INSERT INTO center VALUES " +
                "(1, 'First Hospital', 'Test City', 'a@a.hu', '+3612345678', 100, 'false'), " +
                "(2, 'Second Hospital', 'Test Town', 'b@b.hu', '+3612312378', 200, 'true'), " +
                "(3, 'Third Hospital', 'Testopolis', 'c@c.hu', '+3612312195', 300, 'false')";
        jdbcTemplate.update(sql);
    }

    @AfterEach
    void destruct() {
        String sql = DataDefinition.CENTER_DROP_TABLE.getDefinition();
        jdbcTemplate.update(sql);
    }

    @Test
    void test_getCenter_valid_result() {
        Center testCenter;

        testCenter = centerRepository.getCenter(1);

        assertEquals("Test City", testCenter.getCity());
    }

    @Test
    void test_getCenter_deleted_query() {
        Center testCenter;

        testCenter = centerRepository.getCenter(2);

        assertNull(testCenter);
    }

    @Test
    void test_getCenters_size() {
        List<Center> testCenters;

        testCenters = centerRepository.getCenters();

        assertEquals(2, testCenters.size());
    }

    @Test
    void test_getCenters_every_record_deleted() {
        List<Center> testCenters;
        centerRepository.deleteCenter(1);
        centerRepository.deleteCenter(3);

        testCenters = centerRepository.getCenters();

        assertEquals(0, testCenters.size());
    }

    @Test
    void test_getCenters_table_missing() {
        List<Center> testCenters;
        jdbcTemplate.update(DataDefinition.CENTER_DROP_TABLE.getDefinition());

        testCenters = centerRepository.getCenters();

        assertEquals(0, testCenters.size());
    }

    @Test
    void test_createCenter_new_center() {
        CenterCreateData testCenter = new CenterCreateData("test", "city", "email@gmail.com", "+36201234567", 876);

        assertTrue(centerRepository.createCenter(testCenter));
    }

    @Test
    void test_createCenter_table_missing() {
        jdbcTemplate.update(DataDefinition.CENTER_DROP_TABLE.getDefinition());
        CenterCreateData testCenter = new CenterCreateData("test", "city", "email@gmail.com", "+36201234567", 876);

        assertFalse(centerRepository.createCenter(testCenter));
    }

    @Test
    void test_updateCenter_valid_update() {
        CenterCreateData testCenter = new CenterCreateData("test", "city", "email@gmail.com", "+36201234567", 876);

        assertTrue(centerRepository.updateCenter(1, testCenter));
    }

    @Test
    void test_updateCenter_wrong_id() {
        CenterCreateData testCenter = new CenterCreateData("test", "city", "email@gmail.com", "+36201234567", 876);

        assertFalse(centerRepository.updateCenter(100, testCenter));
    }

    @Test
    void test_updateCenter_table_missing() {
        jdbcTemplate.update(DataDefinition.CENTER_DROP_TABLE.getDefinition());
        CenterCreateData testCenter = new CenterCreateData("test", "city", "email@gmail.com", "+36201234567", 876);

        assertFalse(centerRepository.updateCenter(1, testCenter));
    }

    @Test
    void test_deleteCenter_table_missing() {
        jdbcTemplate.update(DataDefinition.CENTER_DROP_TABLE.getDefinition());

        assertFalse(centerRepository.deleteCenter(1));
    }

    @Test
    void test_getName_valid_result() {
        String name;

        name = centerRepository.getName(1);

        assertEquals("First Hospital", name);
    }

    @Test
    void test_getName_not_found() {
        String name;

        name = centerRepository.getName(0);

        assertEquals("", name);
    }
}