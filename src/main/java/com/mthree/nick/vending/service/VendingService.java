package com.mthree.nick.vending.service;

import com.mthree.nick.vending.enums.Coins;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class VendingService implements VendingServiceLayer {
    //contains logic and non CRUD operations

    private HashMap<String, Item> inventory;

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<String, Item> inv) {
        this.inventory = inv;
    }

    public Item getItemDetails(String name) {
        return inventory.get(name);
    }

    public boolean inStock(String name) {
        return inventory.get(name).getStockInventory() > 0;
    }

    public boolean sufficientMoney(BigDecimal amount, String name) {
        if (amount.compareTo(inventory.get(name).getPrice()) >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void vend(String item, HashMap<String, Item> inv) {
        int count = inv.get(item).getStockInventory();
        inv.get(item).setStockInventory(count - 1);
    }

    //i have literally no idea if this works
    public BigDecimal getChange(BigDecimal moneyIn, BigDecimal cost) {
        BigDecimal moneyOut = moneyIn.subtract(cost);

        coins(moneyOut, getCoin(moneyOut));

        return moneyOut;
    }

    private BigDecimal coins(BigDecimal coins, BigDecimal currentCoin) {
        coins.setScale(0, RoundingMode.HALF_UP);
        currentCoin.setScale(0, RoundingMode.HALF_UP);
        int i = coins.divide(new BigDecimal("2")).compareTo(coins);

        if (i == -1) {
            currentCoin = getCoin(currentCoin);
            return coins(coins.divide(new BigDecimal("2")), currentCoin);
        }
        else {
            return coins.subtract(currentCoin);
        }
    }

    private BigDecimal getCoin(BigDecimal coin) {

        Coins[] money = {
                Coins.ONE_PENCE, Coins.TWO_PENCE, Coins.TEN_PENCE, Coins.TWENTY_PENCE,
                Coins.FIFTY_PENCE, Coins.POUND, Coins.TWO_POUND
        };

        for (int i = money.length - 1; i >= 0; i--) {
            if (coin.compareTo(money[i].getNumVal()) == -1) {
                return money[i + 1].getNumVal();
            }
        }
        return BigDecimal.ZERO;
    }
}
