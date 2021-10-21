package com.mthree.nick.vending.service;

import com.mthree.nick.vending.dao.InvalidFileFormat;
import com.mthree.nick.vending.dao.ItemInterfaceDAO;
import com.mthree.nick.vending.dao.NoItemInventoryException;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ItemDAO implements ItemInterfaceDAO {
    //CRUD operations
    private HashMap<String, Item> inventory;
    private String FILENAME = "items.txt";

    public void vendItem(String name) {

    }

    //read vending machine file
    public boolean load() {
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            while (sc.hasNextLine()) {
                //String line = sc.nextLine();
                Item itemTemp = unmarshall(sc);
                this.inventory.put(itemTemp.getName(), itemTemp);
            }
            return true;
        }
        catch (InvalidFileFormat | FileNotFoundException e) {
            return false;
        }
    }

    private Item unmarshall(Scanner line) throws InvalidFileFormat {
        String name, price;
        int stock;

        try {
            name = line.next();
            price = line.next();
            stock = line.nextInt();
            return new Item(name, new BigDecimal(price), stock);
        }
        catch (InputMismatchException | NumberFormatException e) {
            throw new InvalidFileFormat("Invalid file format");
        }
    }

    //save vending machine file
    public boolean save() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILENAME));
            out.print(marshall(this.inventory));
            out.flush();
            out.close();
            System.out.println("Written " + inventory.size() + " item(s) to " + FILENAME);
            this.inventory.clear();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String marshall(Map<String, Item> inv) {
        String finalString = "";

        for (String i: inv.keySet()) {
            finalString += "\n";
            finalString += i;
            finalString += "," + inv.get(i).getPrice();
            finalString += "," + inv.get(i).getStockInventory();
        }

        return finalString;
    }
}
