package com.mthree.nick.vending.ui;

import com.mthree.nick.vending.dao.NoItemInventoryException;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUserIO {
    final private Scanner console = new Scanner(System.in);

    public void print(String msg) {
        System.out.println(msg);
    }

    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    public BigDecimal readMoney(String msgPrompt) {
        boolean invalidInput = true;
        BigDecimal num;

        while (invalidInput) {
            try {
                num = new BigDecimal(this.readString(msgPrompt));
                return num;
            }
            catch (NumberFormatException e) {
                this.print("Try a valid number");
            }
        }
        return new BigDecimal("0");
    }
}