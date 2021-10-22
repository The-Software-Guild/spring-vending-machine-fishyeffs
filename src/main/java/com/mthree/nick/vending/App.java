package com.mthree.nick.vending;

import com.mthree.nick.vending.controller.VendingController;
import com.mthree.nick.vending.dao.ItemDAO;
import com.mthree.nick.vending.service.VendingService;
import com.mthree.nick.vending.ui.ConsoleUserIO;
import com.mthree.nick.vending.ui.VendingView;

public class App {
    public static void main(String[] args) {
        ConsoleUserIO io = new ConsoleUserIO();
        VendingView view = new VendingView(io);
        VendingService vService = new VendingService();
        ItemDAO iDAO = new ItemDAO();
        VendingController vController = new VendingController(vService, iDAO, view);
        try {
            vController.run();
        }
        catch (Exception e) {
            vController.audit("audit.txt");
        }
    }
}
