package DataAccess.Providers;

import DataAccess.*;
import java.sql.Connection;

public interface IDataProvider<T> {

    /**
     * Initalise the datasource for use across the data provider tree
     * @authors Zac Adams
     */
    Connection DataSource = Database.Instance();

    /**
     * Get all entities from the database
     * @return An iterable list of entities
     * @authors Zac Adams
     */
    Iterable<T> GetEntities();

    /**
     * Get all entities from the database
     * @return An iterable list of entities
     * @authors Zac Adams
     */
    T GetEntity(String key);

    /**
     * Add an entity to the database
     * @return A true/false success flag to determine if the operation was successful.
     * @authors Zac Adams
     */
    boolean AddEntity(T object);

    /**
     * Remove an entity from the database
     * @return A true/false success flag to determine if the operation was successful.
     * @authors Zac Adams
     */
    boolean RemoveEntity(T object);

    /**
     * Check whether or no an entity exists in the database
     * @return If entity exists, return true else return false
     * @authors Zac Adams
     */
    boolean EnsureEntity(T object);
}
