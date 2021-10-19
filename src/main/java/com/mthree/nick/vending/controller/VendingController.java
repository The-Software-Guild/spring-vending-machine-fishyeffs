package com.mthree.nick.vending.controller;

import com.mthree.nick.vending.service.Item;
import com.mthree.nick.vending.service.VendingService;

import java.math.BigDecimal;
import java.util.HashMap;

public class VendingController {
    private final VendingService vendingService;
    private BigDecimal change;

    public VendingController(VendingService itemService) {
        this.vendingService = itemService;
    }

    public HashMap<String, Item> getInventory() {
        return vendingService.getInventory();
    }

    public void insertChange(String amount) {
        BigDecimal amnt = new BigDecimal(amount);
        this.change = change;

    }
}
