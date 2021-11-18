package controller;

import java.sql.SQLException;

import errors.ValidationException;
import model.BO.UserBO;
import model.VO.UserVO;
import utils.Screen;
import view.ScreenLoader;

public class DashboardPagesRedirect {
    protected static UserVO admin;

    public DashboardPagesRedirect() {
        try {
            admin = UserBO.findAdmin();
        } catch (SQLException | ValidationException err) {
            System.out.println(err.getMessage());
        }
    }

    public void redirectToHome() {
        ScreenLoader.load(Screen.homeScreen);
    }

    public void redirectToSales() {
        ScreenLoader.load(Screen.salesScreen);
    }

    public void redirectToProducts() {
        ScreenLoader.load(Screen.productsScreen);
    }

    public void redirectToCustomers() {
        ScreenLoader.load(Screen.customersScreen);
    }

    public void redirectToEmployees() {
    	if(ScreenLoader.getLoggedUser().getIsAdmin()) {
    		ScreenLoader.load(Screen.employeesScreen);
        }
    }

    public void redirectToReports() {
        ScreenLoader.load(Screen.reportsScreen);
    }

    public void logout() {
        ScreenLoader.load(Screen.landing);
    }
}
