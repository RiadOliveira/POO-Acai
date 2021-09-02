package src.model.DAO;

import src.model.BO.UserBO;
import src.model.VO.UserVO;

public class Test {
	public static void main(String[] args) {
		System.out.println(BaseDAO.getConnection());
		
		UserVO user = new UserVO();
		
		user.setName("Joao");
		user.setCpf("70228455440");
		user.setPhoneNumber("84912613011");
		user.setPassword("123456789");
		user.setIsLogged(true);

		UserBO.signUp(user);
	
		UserDAO.insert(user);
	}
}
