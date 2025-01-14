package com.springbootapi.springbootapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootapi.springbootapi.model.Inventory;
import com.springbootapi.springbootapi.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class InventoryController {

    private static Logger log = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/products")
    private ResponseEntity<List<Inventory>> getAllProducts() {
        log.info("Fetching all products");
        return ResponseEntity.status(200).body(inventoryService.getProducts());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Integer productId) {
        return ResponseEntity.status(200).body(inventoryService.getProduct(productId));
    }


    @PostMapping("/add-product")
    private ResponseEntity<String> addProduct(@RequestBody Inventory inventory) {
        if(inventoryService.addProduct(inventory)) {
            return ResponseEntity.status(201).body("Inventory added successfully!");
        }else{
            return ResponseEntity.status(400).body("Duplicate productID exists already!");
        }
    }

    @PutMapping("/update-product")
    public ResponseEntity<String> updateProduct(@RequestBody Inventory inventory) {
        if(inventoryService.updateProduct(inventory)) {
            try {
                log.info("Received inventory update: {}", objectMapper.writeValueAsString(inventory));
            } catch (JsonProcessingException e) {
                log.error("Error serializing inventory object", e);
            }
            return ResponseEntity.status(200).body("Inventory updated successfully!");
        }else {
            return ResponseEntity.status(400).body("Inventory updated failed!");
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
        if(inventoryService.deleteProduct(productId)) {
            return ResponseEntity.status(200).body("Inventory deleted successfully!");
        }else{
            return ResponseEntity.status(404).body("Unable to delete Inventory!");
        }
    }
}
