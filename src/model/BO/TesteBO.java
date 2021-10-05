package model.BO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.VO.CustomerVO;
import model.VO.OrderVO;
import model.VO.ProductVO;
import model.VO.OrderProductVO;
import utils.OrderStatus;
import utils.PaymentMethod;

public class TesteBO {
	public static void main(String[] args) {
		CustomerBO customerBO = new CustomerBO();
		CustomerVO customer = new CustomerVO();
		ProductVO product = new ProductVO();
		ProductBO productBO = new ProductBO();
		OrderBO orderBO = new OrderBO();
		
		customer.setId(UUID.fromString("fa94ac01-f2b8-4ecc-8255-69a6521ebccf"));
		customer = customerBO.findById(customer);

		product.setId(UUID.fromString("6007b548-e0d1-42e7-8c37-a18358eb8668"));
		product = productBO.findById(product);
		
		PaymentMethod[] paymentMethod = PaymentMethod.values();
		OrderStatus[] orderStatus = OrderStatus.values();
		
		List<OrderProductVO> orderProducts = new ArrayList<OrderProductVO>();
		OrderProductVO orderProduct = new OrderProductVO();
		

		OrderVO order = new OrderVO();
//
//		orderProduct.setOrder(order);
//		orderProduct.setProduct(product);
//		orderProduct.setQuantity(4);
//		orderProducts.add(orderProduct);
//		
//		order.setOrderProducts(orderProducts);
//		order.setCustomer(customer);
//		order.setPaymentMethod(paymentMethod[1]);
//		order.setOrderStatus(orderStatus[1]);
//		order.setDate(LocalDate.now());
//		order.setTime(LocalTime.now());
		
		order.setId(UUID.fromString("8e1cad1d-ed87-410c-a1a9-98f1f0ea8357"));
		
		OrderBO.delete(order);
//		
//		System.out.println(customer.toString());
	}
}
