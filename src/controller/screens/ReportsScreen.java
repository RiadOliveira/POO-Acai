package controller.screens;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import controller.DashboardPagesRedirect;
import errors.ValidationException;
import model.BO.OrderBO;

public class ReportsScreen extends DashboardPagesRedirect {
    public void generateReport() {
        try {
            OrderBO.generateReport(null, null, null);
        } catch (FileNotFoundException | DocumentException | SQLException | ValidationException err) {
            System.out.println(err.getMessage());
        }
    }
}
