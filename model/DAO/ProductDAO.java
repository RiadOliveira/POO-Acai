package model.DAO;

import model.VO.ProductVO;
import utils.Category;

public class ProductDAO {
    public static String insert(String name, Category category, double price) {
        //Insert product on database.
        
        return "productId";
    }

    public static ProductVO findById(String id) {
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
        String id, String name, Category category, double price
    ) {
        //Updates product on database.
    }

    public static void delete(String id) {
        //Deletes product on database.
    }
}
