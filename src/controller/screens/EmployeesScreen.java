package controller.screens;

import controller.DashboardPageWithModal;
import controller.DashboardPagesRedirect;
import utils.Modal;
import view.ScreenLoader;

public class EmployeesScreen extends DashboardPagesRedirect implements DashboardPageWithModal {
    public void openModal() {
        ScreenLoader.loadModal(Modal.newEmployeeModal);
    }
}
