package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

import java.util.UUID;

import errors.ValidationException;
import model.DAO.OrderDAO;
import model.DAO.OrderProductDAO;
import model.DAO.ProductDAO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;

public class OrderProductBO {
	private static OrderDAO<OrderVO> orderDAO = new OrderDAO<OrderVO>();
	private static ProductDAO<ProductVO> productDAO = new ProductDAO<ProductVO>();
	private static OrderProductDAO<OrderProductVO> orderProductDAO = new OrderProductDAO<OrderProductVO>();

	public static void insert(OrderProductVO orderProduct) throws Exception {
        if(orderDAO.findById(orderProduct.getOrder()) == null) {
            throw new Exception("Order not found.");
        }

        if(productDAO.findById(orderProduct.getProduct()) == null) {
            throw new Exception("Product not found.");
        }

        orderProductDAO.insert(orderProduct);

        OrderVO updatedOrder = orderProduct.getOrder();

        updatedOrder.setTotalPrice(updatedOrder.getTotalPrice() + 
            orderProduct.getQuantity() * orderProduct.getProduct().getPrice()
        );

        OrderBO.update(updatedOrder);
    }

    public static OrderProductVO findById(OrderProductVO orderProduct) throws SQLException, ValidationException {
        ResultSet findedOrderProductDB = orderProductDAO.findById(orderProduct);

        OrderVO order = new OrderVO();
        order.setId(UUID.fromString(findedOrderProductDB.getString("order_id")));
        order = OrderBO.findById(order);
        
        ProductVO product = new ProductVO();
        product.setId(UUID.fromString(findedOrderProductDB.getString("product_id")));
        product = ProductBO.findById(product);
        
        OrderProductVO findedOrderProduct = new OrderProductVO();
        findedOrderProduct.setId(UUID.fromString(findedOrderProductDB.getString("id")));
        findedOrderProduct.setOrder(order);
        findedOrderProduct.setProduct(product);
        findedOrderProduct.setQuantity(findedOrderProductDB.getInt("quantity"));

        return findedOrderProduct;
    }

    public static List<OrderProductVO> findByOrder(OrderVO order) throws SQLException, ValidationException {
        ResultSet findedOrderProductsDB = orderProductDAO.findByOrder(order);
        List<OrderProductVO> findedOrderProducts = new ArrayList<OrderProductVO>();

        while(findedOrderProductsDB.next()) {
            ProductVO product = new ProductVO();
            product.setId(UUID.fromString(findedOrderProductsDB.getString("product_id")));
            product = ProductBO.findById(product);
            
            OrderProductVO findedOrderProduct = new OrderProductVO();
            findedOrderProduct.setId(UUID.fromString(findedOrderProductsDB.getString("id")));
            findedOrderProduct.setOrder(order);
            findedOrderProduct.setProduct(product);
            findedOrderProduct.setQuantity(findedOrderProductsDB.getInt("quantity"));

            findedOrderProducts.add(findedOrderProduct);
        }

        return findedOrderProducts;
    }

    public static List<OrderProductVO> findByProduct(ProductVO product) throws SQLException, ValidationException {
        ResultSet findedOrderProductsDB = orderProductDAO.findByProduct(product);
        List<OrderProductVO> findedOrderProducts = new ArrayList<OrderProductVO>();

        while(findedOrderProductsDB.next()) {
            OrderVO order = new OrderVO();
            order.setId(UUID.fromString(findedOrderProductsDB.getString("order_id")));
            order = OrderBO.findById(order);
            
            OrderProductVO findedOrderProduct = new OrderProductVO();
            findedOrderProduct.setId(UUID.fromString(findedOrderProductsDB.getString("id")));
            findedOrderProduct.setOrder(order);
            findedOrderProduct.setProduct(product);
            findedOrderProduct.setQuantity(findedOrderProductsDB.getInt("quantity"));

            findedOrderProducts.add(findedOrderProduct);
        }

        return findedOrderProducts;
    }

    public static List<OrderProductVO> findAll() throws SQLException, ValidationException {
        ResultSet findedOrderProductsDB = orderProductDAO.findAll();
        List<OrderProductVO> findedOrderProducts = new ArrayList<OrderProductVO>();

        while(findedOrderProductsDB.next()) {
            OrderVO order = new OrderVO();
            order.setId(UUID.fromString(findedOrderProductsDB.getString("order_id")));
            order = OrderBO.findById(order);
            
            ProductVO product = new ProductVO();
            product.setId(UUID.fromString(findedOrderProductsDB.getString("product_id")));
            product = ProductBO.findById(product);
            
            OrderProductVO findedOrderProduct = new OrderProductVO();
            findedOrderProduct.setId(UUID.fromString(findedOrderProductsDB.getString("id")));
            findedOrderProduct.setOrder(order);
            findedOrderProduct.setProduct(product);
            findedOrderProduct.setQuantity(findedOrderProductsDB.getInt("quantity"));

            findedOrderProducts.add(findedOrderProduct);
        }

        return findedOrderProducts;
    }

    public static void update(OrderProductVO orderProduct) throws Exception {
        OrderProductVO findedOrderProduct = OrderProductBO.findById(orderProduct);

        if(findedOrderProduct == null) {
            throw new Exception("OrderProduct not found.");
        }

        OrderVO updatedOrder = orderProduct.getOrder();
        int quantityDifference = Math.abs(findedOrderProduct.getQuantity() - orderProduct.getQuantity());
        double priceDifference = quantityDifference * orderProduct.getProduct().getPrice();

        if(findedOrderProduct.getQuantity() > orderProduct.getQuantity()) {
            updatedOrder.setTotalPrice(updatedOrder.getTotalPrice() - priceDifference);
        } else if(findedOrderProduct.getQuantity() < orderProduct.getQuantity()) {
            updatedOrder.setTotalPrice(updatedOrder.getTotalPrice() + priceDifference);
        }

        orderProductDAO.update(orderProduct);
        OrderBO.update(updatedOrder);
    }

    public static void delete(OrderProductVO orderProduct) throws Exception {
        if(orderProductDAO.findById(orderProduct) == null) {
            throw new Exception("OrderProduct not found.");
        }

        orderProductDAO.delete(orderProduct);

        double orderProductTotalPrice = orderProduct.getQuantity() * orderProduct.getProduct().getPrice();

        OrderVO updatedOrder = orderProduct.getOrder();
        updatedOrder.setTotalPrice(updatedOrder.getTotalPrice() - orderProductTotalPrice);

        OrderBO.update(updatedOrder);
    }
}
