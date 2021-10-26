package com.mthree.nick.vending.dao;

import com.mthree.nick.vending.dao.InvalidFileFormat;
import com.mthree.nick.vending.dao.ItemInterfaceDAO;
import com.mthree.nick.vending.dto.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

@Component
public class ItemDAO implements ItemInterfaceDAO {
    //CRUD operations
    private HashMap<String, Item> inventory;
    private String FILENAME = "item.txt";

    //read vending machine file
    public HashMap<String, Item> load() throws InvalidFileFormat {
        this.inventory = new HashMap<String, Item>();
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            sc.useDelimiter(",|\r\n|\r");
            while (sc.hasNextLine()) {
                //String line = sc.nextLine();
                if (sc.hasNextLine() && !sc.hasNext()) {
                    break;
                }
                Item itemTemp = unmarshall(sc);
                this.inventory.put(itemTemp.getName(), itemTemp);
            }
            return this.inventory;
        }
        catch (InvalidFileFormat | FileNotFoundException e) {
            throw new InvalidFileFormat("Invalid format");
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
            finalString += i;
            finalString += "," + inv.get(i).getPrice();
            finalString += "," + inv.get(i).getStockInventory();
            finalString += "\r\n";
        }
        //finalString = finalString.substring(0, finalString.length() - 1);
        return finalString;
    }

    public boolean audit(String filename, String message) {
        LocalDate ld = LocalDate.now();
        try {
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            out.append("\n[" + LocalDate.now() + "]");
            out.append("[" + LocalTime.now() + "]");
            out.append("Failure to write.");
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
}
