package controller;

import utils.Screen;
import view.Screens;

public class DashboardPagesRedirect {
    public void redirectToHome() {
        Screens.loadScreen(Screen.homeScreen);
    }

    public void redirectToSales() {
        Screens.loadScreen(Screen.salesScreen);
    }

    public void redirectToProducts() {
        Screens.loadScreen(Screen.productsScreen);
    }

    public void redirectToCustomers() {
        Screens.loadScreen(Screen.customersScreen);
    }

    public void redirectToEmployees() {
        Screens.loadScreen(Screen.employeesScreen);
    }

    public void logout() {
        Screens.loadScreen(Screen.landing);
    }
}
