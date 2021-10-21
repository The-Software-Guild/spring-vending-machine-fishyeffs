package com.mthree.nick.vending.dao;

import com.mthree.nick.vending.service.Item;

import java.util.Map;
import java.util.Scanner;

public interface ItemInterfaceDAO {
    void vendItem(String name) /*throws NoItemInventoryException, InsufficientFundsException*/;

    boolean load();

    boolean save();
}
