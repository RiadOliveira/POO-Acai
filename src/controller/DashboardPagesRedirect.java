package controller;

import utils.Screen;
import view.ScreenLoader;

public class DashboardPagesRedirect {
    public void redirectToHome() {
        ScreenLoader.loadScreen(Screen.homeScreen);
    }

    public void redirectToSales() {
        ScreenLoader.loadScreen(Screen.salesScreen);
    }

    public void redirectToProducts() {
        ScreenLoader.loadScreen(Screen.productsScreen);
    }

    public void redirectToCustomers() {
        ScreenLoader.loadScreen(Screen.customersScreen);
    }

    public void redirectToEmployees() {
        ScreenLoader.loadScreen(Screen.employeesScreen);
    }

    public void logout() {
        ScreenLoader.loadScreen(Screen.landing);
    }
}
