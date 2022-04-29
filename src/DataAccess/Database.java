package DataAccess;

import java.sql.Connection;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection INSTANCE;
    private static DbConnectionInfo DbInfo;
    private Database() {
        Initalise();
    }

    private void Initalise() {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("./db.props");
            props.load(in);
            in.close();

            // Get connection info object
            DbInfo = new DbConnectionInfo(props);

            // get a connection
            INSTANCE = DriverManager.getConnection(DbInfo.Url(),DbInfo.Username(), DbInfo.Password());
        } catch (SQLException sqle) {
            System.err.println(sqle);
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection Instance() {
        if (INSTANCE == null) {
            new Database();
        }
        return INSTANCE;
    }
}
