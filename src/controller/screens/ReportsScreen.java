package controller.screens;

import controller.DashboardPagesRedirect;
import model.BO.OrderBO;

public class ReportsScreen extends DashboardPagesRedirect {
    public void generateReport() {
        try {
            OrderBO.generatePdf();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
