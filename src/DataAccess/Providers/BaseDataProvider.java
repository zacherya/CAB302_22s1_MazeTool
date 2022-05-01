package DataAccess.Providers;

public abstract class BaseDataProvider<T> implements IDataProvider<T> {

    public Iterable<T> GetEntities() {
        return null;
    }

    public T GetEntity(String key) {
        return null;
    }
    
    public boolean AddEntity(T object) {
        return false;
    }

    public boolean RemoveEntity(T object) {
        return false;
    }

    public boolean EnsureEntity(T object) {
        return false;
    }
}
