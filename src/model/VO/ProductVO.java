package src.model.VO;

import java.util.UUID;

import src.utils.Category;

public class ProductVO {
    private UUID id;
    private String name;
    private Category category;
    private double price;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        try {
            if(id == null) { //UUID can't be created from empty string.
                throw new Exception("Product's id can't be null.");
            }
    
            this.id = id;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        try {
            if(name == null || name.equals("")) {
                throw new Exception("Product's name can't be null or empty.");
            }
    
            this.name = name;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        try {
            if(category == null) {
                throw new Exception("Product's category can't be null.");
            }
    
            this.category = category;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
        } 
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        try {
            if(price <= 0) {
                throw new Exception("Price of a product must be greater than zero.");
            }

            this.price = price;
        } catch (Exception err) {
            //Handle exception.
        	System.out.println(err.getMessage());
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
