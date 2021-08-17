package model.DAO;

import java.util.UUID;

import model.VO.ProductVO;

public class ProductDAO {
    public static UUID insert(ProductVO product) {
        //Inserts product on database.
        
        return UUID.randomUUID(); //To simulates product's id from database.
    }

    public static ProductVO findById(ProductVO product) {
        //Database's find method to get requested product;

        //To simulate database's return:
        return product;
    }

    public static ProductVO[] findAll() {
        //Database's find method to get all products;

        //To simulate database's return:
        ProductVO product1 = new ProductVO();
        ProductVO product2 = new ProductVO();

        ProductVO products[] = {product1, product2};

        return products;
    }

    public static void update(ProductVO product) {
        //Updates product on database using its id.
    }

    public static void delete(ProductVO product) {
        //Deletes product on database using its id.
    }
}
