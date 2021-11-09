package controller;

import utils.Screen;
import view.ScreenLoader;

public class DashboardPagesRedirect {
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
        ScreenLoader.load(Screen.employeesScreen);
    }

    public void logout() {
        ScreenLoader.load(Screen.landing);
    }
}
