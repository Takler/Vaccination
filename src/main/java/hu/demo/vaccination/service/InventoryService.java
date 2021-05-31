package hu.demo.vaccination.service;

import hu.demo.vaccination.domain.Inventory;
import hu.demo.vaccination.dto.InventoryCreateData;
import hu.demo.vaccination.dto.inventory.InventoryInfoData;
import hu.demo.vaccination.dto.inventory.InventoryNameInfoData;
import hu.demo.vaccination.dto.inventory.InventoryStockData;
import hu.demo.vaccination.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InventoryService implements CrudOperation<Inventory, InventoryCreateData>, InfoOperation<InventoryInfoData, InventoryNameInfoData> {
    private final InventoryRepository inventoryRepository;
    private final CenterService centerService;
    private final VaccineService vaccineService;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, CenterService centerService, VaccineService vaccineService) {
        this.inventoryRepository = inventoryRepository;
        this.centerService = centerService;
        this.vaccineService = vaccineService;
    }

    public List<InventoryStockData> getStock(int centerId) {
        List<InventoryStockData> result = new ArrayList<>();
        List<Inventory> inventories = inventoryRepository.getInventories();
        for (Inventory inventory : inventories) {
            if (inventory.getCenterId() == centerId) {
                InventoryStockData inventoryStockData = new InventoryStockData();
                inventoryStockData.setAmount(inventory.getAmount());
                inventoryStockData.setVaccineName(vaccineService.getName(inventory.getVaccineId()));
                result.add(inventoryStockData);
            }
        }
        return result;
    }

    @Override
    public InventoryInfoData getInfo(int id) {
        Inventory inventory = inventoryRepository.getInventory(id);
        InventoryInfoData inventoryInfoData = new InventoryInfoData();
        inventoryInfoData.setId(id);
        inventoryInfoData.setCenter(centerService.getById(inventory.getCenterId()));
        inventoryInfoData.setVaccine(vaccineService.getById(inventory.getVaccineId()));
        inventoryInfoData.setAmount(inventory.getAmount());
        return inventoryInfoData;
    }

    @Override
    public InventoryNameInfoData getNameInfo(int id) {
        Inventory inventory = inventoryRepository.getInventory(id);
        InventoryNameInfoData inventoryNameInfoData = new InventoryNameInfoData();
        inventoryNameInfoData.setId(id);
        inventoryNameInfoData.setCenterName(centerService.getName(inventory.getCenterId()));
        inventoryNameInfoData.setVaccineName(vaccineService.getName(inventory.getVaccineId()));
        inventoryNameInfoData.setAmount(inventory.getAmount());
        return inventoryNameInfoData;
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
