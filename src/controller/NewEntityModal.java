package controller;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import utils.Component;

public abstract class NewEntityModal<T extends Node> extends DashboardModal {
    protected void verifyData(Component<TextField> input) throws Exception  {
        if(input.component.getText().length() == 0) {
            throw new Exception("Empty " + input.name);
        }
    }

    protected void verifyInputError(Component<T> input, String message) {
        if(message.contains(input.name)) {
            input.component.setStyle(input.component.getStyle() + "-fx-border-color: red;");
        } else {
            input.component.setStyle(input.component.getStyle() + "-fx-border-color: none;");
        }
    }
}
