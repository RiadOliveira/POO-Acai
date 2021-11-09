package controller.screens;

import controller.DashboardPageWithModal;
import controller.DashboardPagesRedirect;
import utils.Modal;
import view.ModalLoader;

public class EmployeesScreen extends DashboardPagesRedirect implements DashboardPageWithModal {
    public void openModal() {
        ModalLoader.load(Modal.newEmployeeModal);
    }
}
