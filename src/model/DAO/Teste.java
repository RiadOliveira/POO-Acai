package src.model.DAO;

import src.model.VO.UserVO;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseDAO baseDao = new BaseDAO();
		System.out.println(baseDao.getConnection());
		
		UserDAO userDao = new UserDAO();
		UserVO userVo = new UserVO();
		
		userVo.setName("Joao");
		userVo.setCpf("70228455440");
		userVo.setEmail("joao@gmail.com");
		userVo.setPhoneNumber("84912613011");
		userVo.setPassword("123456789");
		userVo.setIsLogged(true);
	
		userDao.insert(userVo);
	}

}
