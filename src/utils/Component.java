package utils;

import javafx.scene.control.Control;

public class Component {
    public Control component;
    public String name;

    public Component(Control component, String name) {
        this.name = name;
        this.component = component;
    }
}
