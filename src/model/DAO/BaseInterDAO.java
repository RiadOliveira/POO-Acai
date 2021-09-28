package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseInterDAO<VO> {
	public abstract void insert(VO entity) throws SQLException;
	public abstract void update(VO entity) throws SQLException;
	public abstract void delete(VO entity) throws SQLException;
	public abstract ResultSet findAll() throws SQLException;
	public abstract ResultSet findById(VO entity) throws SQLException;
}
