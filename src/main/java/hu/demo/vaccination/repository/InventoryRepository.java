package hu.demo.vaccination.repository;

import hu.demo.vaccination.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InventoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Inventory> getInventories() {
        String sql = "SELECT id, center_id, vaccine_id, amount " +
                "FROM inventory";
        return jdbcTemplate.query(sql, new InventoryMapper());
    }

    public Inventory getInventory(int id) {
        String sql = "SELECT id, center_id, vaccine_id, amount " +
                "FROM inventory" +
                "WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new InventoryMapper(), id);
    }

    private static final class InventoryMapper implements RowMapper<Inventory> {
        @Override
        public Inventory mapRow(ResultSet resultSet, int i) throws SQLException {
            Inventory inventory = new Inventory();
            inventory.setId(resultSet.getInt("id"));
            inventory.setCenterId(resultSet.getInt("center_id"));
            inventory.setVaccineId(resultSet.getInt("vaccine_id"));
            inventory.setAmount(resultSet.getInt("amount"));
            return inventory;
        }
    }
}
