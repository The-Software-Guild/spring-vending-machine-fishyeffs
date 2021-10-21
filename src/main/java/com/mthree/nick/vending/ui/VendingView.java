package com.mthree.nick.vending.ui;

import com.mthree.nick.vending.service.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VendingView {
    private ConsoleUserIO io;

    public VendingView(ConsoleUserIO io) {
        this.io = io;
    }

    public void displayItems(HashMap<String, Item> inv) {
        for (String i: inv.keySet()) {
            System.out.print(i);
            System.out.print(": ");
            System.out.print(inv.get(i).getPrice() + ", ");
            if (inv.get(i).getStockInventory() > 0) {
                System.out.print("in ");
            } else {
                System.out.print("out of ");
            }
            System.out.print("stock\n");
        }
        System.out.println("\nType -1 when inserting coins to exit");
    }

    public void insufficientFunds(BigDecimal amount) {
        System.out.println("You do not have enough funds for this!");
        System.out.println("You inserted £" + amount);
    }

    public void sufficientFunds() {
        System.out.println("Vending item...");
    }

    public String choose() {
        return io.choose();
    }

    public BigDecimal insertChange() {
        return io.readMoney("Insert coins: ");
    }

    public String pause() {
        return io.pause();
    }

    public void vend(Item item) {
        System.out.println("Vending " + item.getName() + " for £" + item.getPrice());
    }
}
