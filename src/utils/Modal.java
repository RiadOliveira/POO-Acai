package utils;

public enum Modal {
    customerOrdersHistoricModal("customerOrdersHistoricModal"),
    finishSaleModal("finishSaleModal"), newCustomerModal("newCustomerModal"),
    newEmployeeModal("newEmployeeModal"), newProductModal("newProductModal"),
    generateInvoiceModal("generateInvoiceModal");

    private final String modalName;

    Modal(final String modalName) {
        this.modalName = modalName;
    }

    @Override
    public String toString() {
        return modalName;
    }
}
