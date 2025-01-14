package com.springbootapi.springbootapi.repository;

import com.springbootapi.springbootapi.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByProductID(Integer productID);

    List<Inventory> findByProductID(int productID);

    void deleteByProductID(Integer productID);
}
