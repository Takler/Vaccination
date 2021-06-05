package hu.demo.vaccination.controller;

import hu.demo.vaccination.domain.Inventory;
import hu.demo.vaccination.dto.InventoryCreateData;
import hu.demo.vaccination.dto.inventory.InventoryInfoData;
import hu.demo.vaccination.dto.inventory.InventoryNameInfoData;
import hu.demo.vaccination.dto.inventory.InventoryStockData;
import hu.demo.vaccination.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/centerid/{centerId}")
    public ResponseEntity<List<InventoryStockData>> getStock(@PathVariable int centerId) {
        List<InventoryStockData> inventoryStockData = inventoryService.getStock(centerId);
        if (inventoryStockData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(inventoryStockData, HttpStatus.OK);
        }
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<InventoryInfoData> getInfo(@PathVariable int id) {
        InventoryInfoData inventoryInfoData = inventoryService.getInfo(id);
        if (inventoryInfoData != null) {
            return new ResponseEntity<>(inventoryInfoData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nameinfo/{id}")
    public ResponseEntity<InventoryNameInfoData> getNameInfo(@PathVariable int id) {
        InventoryNameInfoData inventoryNameInfoData = inventoryService.getNameInfo(id);
        if (inventoryNameInfoData != null) {
            return new ResponseEntity<>(inventoryNameInfoData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getInventories() {
        List<Inventory> inventories = inventoryService.findAll();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable int id) {
        Inventory inventory = inventoryService.getById(id);
        if (inventory != null) {
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createInventory(@RequestBody InventoryCreateData data) {
        boolean saveSuccessful = inventoryService.save(data);
        if (saveSuccessful) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInventory(@PathVariable int id, @RequestBody InventoryCreateData data) {
        boolean updateSuccessful = inventoryService.update(id, data);
        if (updateSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable int id) {
        boolean deleteSuccessful = inventoryService.delete(id);
        if (deleteSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
