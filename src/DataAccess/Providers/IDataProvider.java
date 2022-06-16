package DataAccess.Providers;

import DataAccess.*;
import java.sql.Connection;

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
    Iterable<T> GetEntities();

    /**
     * Get all entities from the database.
     * @param key the search value to perform the lookup
     * @return An iterable list of entities
     * @author Zac Adams
     */
    T GetEntity(String key);

    /**
     * Add an entity to the database.
     * @param object the entity to add
     * @return A true/false success flag to determine if the operation was successful
     * @author Zac Adams
     */
    boolean AddEntity(T object);

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
