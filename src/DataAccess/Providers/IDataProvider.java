package DataAccess.Providers;

import DataAccess.*;
import java.sql.Connection;

public interface IDataProvider<T> {

    Connection DataSource = Database.Instance();

    Iterable<T> GetEntities();
    boolean SetEntities();
    boolean AddEntity();
    boolean RemoveEntity();
    boolean EnsureEntity();
}
