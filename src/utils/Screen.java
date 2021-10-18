package utils;

public enum Screen {
    customerOrdersHistoricModal("customerOrdersHistoricModal"),
    customersScreen("customersScreen"), employeesScreen("employeesScreen"),
    finishSaleModal("finishSaleModal"), homeScreen("homeScreen"), 
    landing("landing"), newCustomerModal("newCustomerModal"),
    newEmployeeModal("newEmployeeModal"), newProductModal("newProductModal"),
    productsScreen("productsScreen"), salesScreen("salesScreen");

    private final String screenName;

    Screen(final String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return screenName;
    }
}
