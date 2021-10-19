package com.mthree.nick.vending.service;

import java.math.BigDecimal;
import java.util.HashMap;

public class VendingService {
    private HashMap<String, Item> inventory;

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public Item getItemDetails(Item item) {
        return inventory.get(item.getName());
    }

    public boolean inStock(String name) {
        return inventory.get(name).getStockInventory() > 0;
    }

    public boolean sufficientMoney(BigDecimal amount, String name) {
        if (amount.compareTo(inventory.get(name).getPrice()) == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
