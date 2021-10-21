package com.mthree.nick.vending.dao;

import com.mthree.nick.vending.service.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public interface ItemInterfaceDAO {
    void vendItem(String name) /*throws NoItemInventoryException, InsufficientFundsException*/;

    HashMap<String, Item> load() throws InvalidFileFormat;

    boolean save();
}
