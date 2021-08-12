package model.VO;

import java.util.UUID;

import utils.Category;

public class ProductVO {
    private UUID id;
    private String name;
    private Category category;
    private double price;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        try {
            if(price <= 0) {
                throw new Exception("Price of a product needs to be higher than zero.");
            }

            this.price = price;
        } catch (Exception err) {
            //Handle exception.
        }
    }

    public String toString() {
        String obj = "";

        obj = "id: " + this.id + '\n';
        obj += "name: " + this.name + '\n';
        obj += "category: " + this.category + '\n';
        obj += "price: " + this.price;

        return obj;
    }
}
