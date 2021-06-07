package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Center;
import hu.demo.vaccination.dto.center.CenterCreateData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class CenterRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Center> centerRowMapper = ((resultSet, i) -> {
        Center center = new Center();
        center.setId(resultSet.getInt("id"));
        center.setName(resultSet.getString("name"));
        center.setCity(resultSet.getString("city"));
        center.setEmail(resultSet.getString("email"));
        center.setTelephoneNumber(resultSet.getString("telephone_number"));
        center.setDailyCapacity(resultSet.getInt("daily_capacity"));
        center.setDeleted(resultSet.getBoolean("deleted"));
        return center;
    });

    @Autowired
    public CenterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Center> getCenters() {
        String sql = "SELECT id, name, city, email, telephone_number, daily_capacity, deleted " +
                "FROM center " +
                "WHERE deleted = FALSE ";
        try {
            return jdbcTemplate.query(sql, centerRowMapper);
        } catch (DataAccessException e) {
            log.error("getCenters exception: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Center getCenter(int id) {
        String sql = "SELECT id, name, city, email, telephone_number, daily_capacity, deleted " +
                "FROM center " +
                "WHERE id = ? AND deleted = FALSE";
        try {
            return jdbcTemplate.queryForObject(sql, centerRowMapper, id);
        } catch (DataAccessException e) {
            log.error("getCenter exception " + e.getMessage());
            return null;
        }
    }

    public boolean createCenter(CenterCreateData data) {
        String sql = "INSERT INTO center (name, city, email, telephone_number, daily_capacity) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, data.getName(), data.getCity(), data.getEmail(), data.getTelephoneNumber(), data.getDailyCapacity());
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("createCenter exception: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCenter(int id, CenterCreateData data) {
        String sql = "UPDATE center " +
                "SET name = ?, city = ?, email = ?, telephone_number = ?, daily_capacity = ? " +
                "WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, data.getName(), data.getCity(), data.getEmail(), data.getTelephoneNumber(), data.getDailyCapacity(), id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("updateCenter exception: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCenter(int id) {
        String sql = "UPDATE center " +
                "SET deleted = true " +
                "WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("deleteCenter exception: " + e.getMessage());
            return false;
        }
    }

    public String getName(int id) {
        Center center = getCenter(id);
        if (center != null) {
            return center.getName();
        } else {
            return "";
        }
    }
}
