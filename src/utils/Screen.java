package utils;

public enum Screen {
    homeScreen("homeScreen"), landing("landing"),
    customersScreen("customersScreen"), employeesScreen("employeesScreen"),
    productsScreen("productsScreen"), salesScreen("salesScreen"),
    reportsScreen("reportsScreen");

    private final String screenName;

    Screen(final String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return screenName;
    }
}
