package DataAccess.Providers;

/**
 * Base data provider abstract class.
 * @param <T> the typecast of data to retrieve and insert
 * @author Zac Adams
 */
public abstract class BaseDataProvider<T> implements IDataProvider<T> {

    /**
     * Get all entities from the database.
     * @return An iterable list of entities
     * @author Zac Adams
     */
    public Iterable<T> GetEntities() {
        return null;
    }

    /**
     * Get all entities from the database.
     * @param key the search value to perform the lookup
     * @return An iterable list of entities
     * @author Zac Adams
     */
    public T GetEntity(String key) {
        return null;
    }

    /**
     * Add an entity to the database.
     * @param object the entity to add
     * @return A true/false success flag to determine if the operation was successful
     * @author Zac Adams
     */
    public boolean AddEntity(T object) {
        return false;
    }

    /**
     * Remove an entity from the database.
     * @param object the entity to remove
     * @return A true/false success flag to determine if the operation was successful
     * @author Zac Adams
     */
    public boolean RemoveEntity(T object) {
        return false;
    }

    /**
     * Check whether an entity exists in the database.
     * @param object the entity to check
     * @return If entity exists, return true else return false
     * @author Zac Adams
     */
    public boolean EnsureEntity(T object) {
        return false;
    }
}
