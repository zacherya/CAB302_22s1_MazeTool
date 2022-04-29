package DataAccess.Providers;

public class BaseDataProvider<T> implements IDataProvider {

    public Iterable<T> GetEntities() {
        return null;
    }

    public boolean SetEntities() {
        return false;
    }
    
    public boolean AddEntity() {
        return false;
    }

    public boolean RemoveEntity() {
        return false;
    }

    public boolean EnsureEntity() {
        return false;
    }
}
