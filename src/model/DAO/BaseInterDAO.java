package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseInterDAO<VO> {
	public void insert(VO entity) throws SQLException;
	public void update(VO entity) throws SQLException;
	public void delete(VO entity) throws SQLException;
	public ResultSet findAll() throws SQLException;
	public ResultSet findById(VO entity) throws SQLException;
}
