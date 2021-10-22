package com.mthree.nick.vending.service;

import java.math.BigDecimal;
import java.util.HashMap;

public interface VendingServiceLayer {
    HashMap<String, Item> getInventory();

    void setInventory(HashMap<String, Item> inv);

    boolean inStock(String name);

    boolean sufficientMoney(BigDecimal amount, String name);
}
