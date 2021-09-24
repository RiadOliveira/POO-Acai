package model.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.BO.OrderProductBO;
import model.VO.CustomerVO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;
import utils.OrderStatus;
import utils.PaymentMethod;

public class Test {
	public static void main(String[] args) throws SQLException {		
		
		OrderVO order = new OrderVO();
		List<OrderProductVO> orderProducts = new ArrayList<OrderProductVO>();
		CustomerVO customer = new CustomerVO();
		ProductVO product = new ProductVO();
		
		order.setId(UUID.fromString("9f831441-ac43-47b7-8c22-7b88118fa3a2"));
		order.setOrderProducts(orderProducts);
		order.setCustomer(customer);
		order.setPaymentMethod(PaymentMethod.card);
		order.setOrderStatus(OrderStatus.analyzing);
		order.setDate(LocalDate.now());
		
		product.setId(UUID.fromString("6007b548-e0d1-42e7-8c37-a18358eb8668"));

		OrderProductVO orderProductVO = new OrderProductVO();
		orderProductVO.setId(UUID.fromString("43284288-8a21-4a84-84cc-f2d79cc2311b"));
		orderProductVO.setOrder(order);
		orderProductVO.setProduct(product);
		orderProductVO.setQuantity(4);

		
		
		OrderProductBO.update(orderProductVO);
	}
}
