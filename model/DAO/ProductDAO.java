package model.DAO;

import java.util.UUID;

import model.VO.ProductVO;
import utils.Category;

public class ProductDAO {
    public static UUID insert(String name, Category category, double price) {
        //Inserts product on database.
        
        return UUID.randomUUID(); //To simulates product's id from database.
    }

    public static ProductVO findById(UUID id) {
        //Database's find method to get requested product;

        //To simulate database's return:
        ProductVO findedProduct = new ProductVO();

        return findedProduct;
    }

    public static ProductVO[] findAll() {
        //Database's find method to get all products;

        //To simulate database's return:
        ProductVO product1 = new ProductVO();
        ProductVO product2 = new ProductVO();

        ProductVO products[] = {product1, product2};

        return products;
    }

    public static void update(
        UUID id, String name, Category category, double price
    ) {
        //Updates product on database.
    }

    public static void delete(UUID id) {
        //Deletes product on database.
    }
}
