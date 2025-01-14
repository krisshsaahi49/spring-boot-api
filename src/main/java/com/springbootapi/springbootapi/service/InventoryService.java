package com.springbootapi.springbootapi.service;

import com.springbootapi.springbootapi.model.Inventory;
import com.springbootapi.springbootapi.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public List<Inventory> getProducts() {
        return inventoryRepository.findAll();
    }

    @Transactional
    public Optional<Inventory> getProduct(Integer index) {
        if (index == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if(inventoryRepository.existsByProductID(index)) {
           return inventoryRepository.findById(Long.valueOf(index));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean addProduct(Inventory inventory) {

        if(inventoryRepository.existsByProductID(inventory.getProductID())) {
            return false;
        }else{
            inventoryRepository.save(inventory);
            return true;
        }
    }

    @Transactional
    public boolean updateProduct(Inventory inventory) {
        inventoryRepository.save(inventory);
        return true;
    }

    @Transactional
    public boolean deleteProduct(Integer index) {
        if (index == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (inventoryRepository.existsByProductID(index)) {
            inventoryRepository.deleteByProductID(index);
            return true;
        }
        return false;
    }

}
