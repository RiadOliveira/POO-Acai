package model.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.BO.OrderBO;
import model.VO.CustomerVO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;
import utils.OrderStatus;
import utils.PaymentMethod;

public class Test {
	public static void main(String[] args) throws SQLException {		
		
		OrderVO order = new OrderVO();
		OrderProductVO orderProduct = new OrderProductVO();
		
		List<OrderProductVO> orderProducts = new ArrayList<OrderProductVO>();
		orderProducts.add(orderProduct);
		
		List<CustomerVO> customer = CustomerDAO.findAll();
		
		List<ProductVO> products = ProductDAO.findAll();
		
		List<OrderVO> orders = OrderDAO.findAll();
		
//		order.setId(UUID.randomUUID());
//		order.setOrderProducts(orderProducts);
//		order.setCustomer(customer.get(0));
//		order.setPaymentMethod(PaymentMethod.card);
//		order.setOrderStatus(OrderStatus.analyzing);
//		order.setDate(LocalDate.now());
//		
//		orderProduct.setId(UUID.randomUUID());
//		orderProduct.setOrder(order);
//		orderProduct.setProduct(products.get(0));
//		orderProduct.setQuantity(1);
		
		order = orders.get(0);
		
		order.setPaymentMethod(PaymentMethod.card);
		order.setOrderStatus(OrderStatus.ready);
		
		OrderBO.delete(order);
	}
}
