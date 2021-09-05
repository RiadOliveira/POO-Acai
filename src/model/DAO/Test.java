package src.model.DAO;

import src.model.BO.UserBO;
import src.model.VO.UserVO;

public class Test {
	public static void main(String[] args) {		
		UserVO user = new UserVO();
		
		user.setName("Ríad Oliveira");
		user.setCpf("70232845440");
		user.setPhoneNumber("84912613011");
		user.setPassword("123456789");
		user.setIsAdmin(false);

		UserBO.signUp(user);	
	}
}
