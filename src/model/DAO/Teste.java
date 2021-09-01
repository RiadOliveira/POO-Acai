package src.model.DAO;

import src.model.VO.UserVO;

public class Teste {

	public static void main(String[] args) {
		System.out.println(BaseDAO.getConnection());
		
		UserVO userVo = new UserVO();
		
		userVo.setName("Joao");
		userVo.setCpf("70228455440");
		userVo.setEmail("joao@gmail.com");
		userVo.setPhoneNumber("84912613011");
		userVo.setPassword("123456789");
		userVo.setIsLogged(true);
	
		UserDAO.insert(userVo);
	}
}
