package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Inventory;
import hu.demo.vaccination.dto.InventoryCreateData;
import hu.demo.vaccination.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService implements CrudOperation<Inventory, InventoryCreateData> {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.getInventories();
    }

    @Override
    public Inventory getById(int id) {
        return inventoryRepository.getInventory(id);
    }

    @Override
    public boolean save(InventoryCreateData data) {
        return inventoryRepository.createInventory(data);
    }

    @Override
    public boolean update(int id, InventoryCreateData data) {
        return inventoryRepository.updateInventory(id, data);
    }

    @Override
    public boolean delete(int id) {
        return inventoryRepository.deleteInventory(id);
    }
}
