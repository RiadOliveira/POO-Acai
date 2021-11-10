package model.VO;

import java.util.UUID;

import errors.ValidationException;
import utils.Category;

public class ProductVO {
    private UUID id;
    private String name;
    private Category category;
    private double price;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) throws ValidationException {
        if(id == null) { //UUID can't be created from empty string.
            throw new ValidationException("Product's id can't be null.");
        }

        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws ValidationException {
        if(name == null || name.equals("")) {
            throw new ValidationException("Product's name can't be null or empty.");
        }

        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) throws ValidationException {
        if(category == null) {
            throw new ValidationException("Product's category can't be null.");
        }

        this.category = category; 
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) throws ValidationException {
        if(price <= 0) {
            throw new ValidationException("Price of a product must be greater than zero.");
        }

        this.price = price;
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
