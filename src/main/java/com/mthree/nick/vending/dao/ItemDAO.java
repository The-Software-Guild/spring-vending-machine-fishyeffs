package com.mthree.nick.vending.dao;

import com.mthree.nick.vending.service.Item;

public interface ItemDAO {
    Item vendItem(Item item) throws NoItemInventoryException, InsufficientFundsException;


}
