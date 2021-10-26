package com.mthree.nick.vending;

import com.mthree.nick.vending.controller.VendingController;
import com.mthree.nick.vending.dao.ItemDAO;
import com.mthree.nick.vending.service.VendingService;
import com.mthree.nick.vending.ui.ConsoleUserIO;
import com.mthree.nick.vending.ui.VendingView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        /*ConsoleUserIO io = new ConsoleUserIO();
        VendingView view = new VendingView(io);
        ItemDAO iDAO = new ItemDAO();
        VendingService vService = new VendingService(iDAO);
        VendingController vController = new VendingController(vService, iDAO, view);*/


        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.mthree.nick.vending");
        appContext.refresh();

        VendingController vController = (VendingController) appContext.getBean("vendingController", VendingController.class);
        try {
            vController.run();
        }
        catch (Exception e) {
            vController.audit("audit.txt", "File not found");
        }
    }
}
