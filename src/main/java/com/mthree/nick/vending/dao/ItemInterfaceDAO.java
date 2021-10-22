package com.mthree.nick.vending.dao;

import com.mthree.nick.vending.dto.Item;

import java.util.HashMap;

public interface ItemInterfaceDAO {
    void vendItem(String name) /*throws NoItemInventoryException, InsufficientFundsException*/;

    HashMap<String, Item> load() throws InvalidFileFormat;

    boolean save();
}
