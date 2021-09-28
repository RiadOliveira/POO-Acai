package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.VO.PersonVO;

public interface PersonInterDAO<VO extends PersonVO> extends BaseInterDAO<VO> {
	public ResultSet findByCpf(VO entity) throws SQLException;
}
