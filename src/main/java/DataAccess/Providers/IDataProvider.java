package DataAccess.Providers;

import DataAccess.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDataProvider<T> {

    /**
     * Initialise the datasource for use across the data provider tree.
     * @author Zac Adams
     */
    Connection DataSource = Database.Instance();

    /**
     * Get all entities from the database.
     * @return An iterable list of entities
     * @author Zac Adams
     */
    ResultSet GetEntities(String tableName) throws SQLException;

    /**
     * Get all entities from the database.
     * @param id the search value to perform the lookup
     * @return An iterable list of entities
     * @author Zac Adams
     */
    T GetEntity(String id);

    /**
     * Add an entity to the database.
     * @param object the entity to add
     * @return A true/false success flag to determine if the operation was successful
     * @author Zac Adams
     */
    boolean AddEntity(T object) throws SQLException;

    /**
     * Remove an entity from the database.
     * @param object the entity to remove
     * @return A true/false success flag to determine if the operation was successful
     * @author Zac Adams
     */
    boolean RemoveEntity(T object);

    /**
     * Check whether an entity exists in the database.
     * @param object the entity to check
     * @return If entity exists, return true else return false
     * @author Zac Adams
     */
    boolean EnsureEntity(T object);
}
