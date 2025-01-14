package com.springbootapi.springbootapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Inventory {

    public Inventory(int productID, String productName, int quantity, int price, String stock_status, int unit) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.stock_status = stock_status;
        this.unit = unit;
    }

    public Inventory() {}

    @Id
    @Column(name = "productid")
    private int productID;

    @Column(name = "productname")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "stockstatus")
    private String stock_status;

    @Column(name = "unit")
    private int unit;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
