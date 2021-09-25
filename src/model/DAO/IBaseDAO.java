package model.DAO;

import java.sql.SQLException;

public interface IBaseDAO<Entity> {
    public abstract void insert(Entity entity) throws SQLException;
    public abstract Entity findById(Entity entity) throws SQLException;
    public abstract void update(Entity entity) throws SQLException;
    public abstract void delete(Entity entity) throws SQLException;
}
