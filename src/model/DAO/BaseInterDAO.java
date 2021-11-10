package model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import errors.ValidationException;

public interface BaseInterDAO<VO> {
	public abstract void insert(VO entity) throws SQLException, ValidationException;
	public abstract void update(VO entity) throws SQLException;
	public abstract void delete(VO entity) throws SQLException;
	public abstract ResultSet findAll() throws SQLException;
	public abstract ResultSet findById(VO entity) throws SQLException;
}
