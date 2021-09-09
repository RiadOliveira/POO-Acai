package model.DAO;

import model.BO.ProductBO;
import model.VO.ProductVO;
import model.VO.UserVO;
import utils.Category;

public class Test {
	public static void main(String[] args) {		
		UserVO user = new UserVO();
		
		user.setName("Valdir Aires");
		user.setCpf("70228845440");
		user.setPhoneNumber("84987913011");
		user.setPassword("987654321");
		user.setIsAdmin(true);
		

		ProductVO productVO = new ProductVO();
		
		productVO.setName("Pizza Calabresa");
		productVO.setCategory(Category.pizza);
		productVO.setPrice(25);
		
		ProductBO.update(productVO);
	}
}
