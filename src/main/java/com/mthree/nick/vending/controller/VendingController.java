package com.mthree.nick.vending.controller;

import com.mthree.nick.vending.dao.InsufficientFundsException;
import com.mthree.nick.vending.dao.InvalidFileFormat;
import com.mthree.nick.vending.dao.NoItemInventoryException;
import com.mthree.nick.vending.dto.Item;
import com.mthree.nick.vending.dao.ItemDAO;
import com.mthree.nick.vending.service.VendingService;
import com.mthree.nick.vending.ui.VendingView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class VendingController {
    private final VendingView view;
    private final VendingService vendingService;
    private final ItemDAO itemDAO;
    private BigDecimal change;
    private String itemChoice;

    public VendingController(VendingService itemService, ItemDAO itemDAO, VendingView view) {
        this.vendingService = itemService;
        this.itemDAO = itemDAO;
        this.view = view;
    }

    public void run() {
        boolean exit = false;
        try {
            vendingService.setInventory(itemDAO.load());
        }
        catch (InvalidFileFormat e) {

            System.out.println("Invalid file format");
            System.exit(0);
        }
        do {
            view.displayItems(getInventory());
            //choice between exit or insert coins

            exit = insert();
            if (exit) {
                break;
            }
            vend();

        } while (!exit);
        itemDAO.save();
    }

    public void audit(String filename) {
        itemDAO.audit(filename);
    }

    private boolean insert() {
        this.change = view.insertChange();
        this.change.setScale(0, RoundingMode.HALF_UP);
        if (this.change.equals(new BigDecimal("-1"))) {
            return true;
        }
        else {
            System.out.println("Available: £" + this.change);
            return false;
        }
    }

    private void vend()  {
        try {
            if (validChoice()) {
                view.vend(this.getInventory().get(this.itemChoice));
                vendingService.vend(this.itemChoice, this.getInventory());
                this.change = this.change.subtract(
                        this.getInventory().get(this.itemChoice).getPrice()
                );
                System.out.println("Change to be released: £" + this.change);
                this.change = new BigDecimal("0");
            }
        } catch (NoItemInventoryException e) {
            System.out.println("No such item in inventory.");
        } catch (InsufficientFundsException e) {
            view.insufficientFunds(this.change);
        }
        view.pause();
    }

    private boolean validChoice() throws NoItemInventoryException, InsufficientFundsException {
        String choice = view.choose();
        if (getInventory().containsKey(choice)) {
            if (vendingService.inStock(choice)) {
                if (vendingService.sufficientMoney(change, choice)) {
                    this.itemChoice = choice;
                    return true;
                }
                else {
                    throw new InsufficientFundsException("You have not inserted enough money.");
                }
            }
        }
        throw new NoItemInventoryException("Item is not in inventory.");

    }

    private HashMap<String, Item> getInventory() {
        return vendingService.getInventory();
    }
}
