package utils;

import javafx.scene.control.TextField;

public class Input {
    public TextField component;
    public String name;

    public Input(TextField component, String name) {
        this.name = name;
        this.component = component;
    }
}
