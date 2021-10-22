package com.mthree.nick.vending.dto;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal price;
    private int stockInventory;

    public Item(String name, BigDecimal price, int stock) {
        this.name = name;
        this.price = price;
        this.stockInventory = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockInventory() {
        return stockInventory;
    }

    public void setStockInventory(int stockInventory) {
        this.stockInventory = stockInventory;
    }
}
