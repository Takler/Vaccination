package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Inventory;
import hu.demo.vaccination.dto.inventory.InventoryCreateData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class InventoryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InventoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Inventory> getInventories() {
        String sql = "SELECT id, center_id, vaccine_id, amount, deleted " +
                "FROM inventory " +
                "WHERE deleted = FALSE ";
        try {
            return jdbcTemplate.query(sql, new InventoryMapper());
        } catch (DataAccessException e) {
            log.error("getInventories exception: " + e.getMessage());
            return Collections.emptyList();
        }

    }

    public Inventory getInventory(int id) {
        String sql = "SELECT id, center_id, vaccine_id, amount, deleted " +
                "FROM inventory " +
                "WHERE id = ? AND deleted = FALSE ";
        try {
            return jdbcTemplate.queryForObject(sql, new InventoryMapper(), id);
        } catch (DataAccessException e) {
            log.error("getInventory exception: " + e.getMessage());
            return null;
        }

    }

    public boolean createInventory(InventoryCreateData data) {
        String sql = "INSERT INTO inventory (center_id, vaccine_id, amount) " +
                "VALUES (?, ?, ?) ";
        try {
            int rowsAffected = jdbcTemplate.update(sql, data.getCenterId(), data.getVaccineId(), data.getAmount());
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("createInventory exception: " + e.getMessage());
            return false;
        }
    }

    public boolean updateInventory(int id, InventoryCreateData data) {
        String sql = "UPDATE inventory " +
                "SET center_id = ?, vaccine_id = ?, amount = ? " +
                "WHERE id = ? ";
        try {
            int rowsAffected = jdbcTemplate.update(sql, data.getCenterId(), data.getVaccineId(), data.getAmount(), id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("updateInventory exception: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteInventory(int id) {
        String sql = "UPDATE inventory " +
                "SET deleted = true " +
                "WHERE id = ? ";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            return rowsAffected == 1;
        } catch (DataAccessException e) {
            log.error("deleteInventory exception: " + e.getMessage());
            return false;
        }
    }

    private static final class InventoryMapper implements RowMapper<Inventory> {
        @Override
        public Inventory mapRow(ResultSet resultSet, int i) throws SQLException {
            Inventory inventory = new Inventory();
            inventory.setId(resultSet.getInt("id"));
            inventory.setCenterId(resultSet.getInt("center_id"));
            inventory.setVaccineId(resultSet.getInt("vaccine_id"));
            inventory.setAmount(resultSet.getInt("amount"));
            inventory.setDeleted(resultSet.getBoolean("deleted"));
            return inventory;
        }
    }
}
