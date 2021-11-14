package utils;

import javafx.scene.Node;

public class Component<T extends Node> {
    public T component;
    public String name;

    public Component(T component, String name) {
        this.name = name;
        this.component = component;
    }
}
