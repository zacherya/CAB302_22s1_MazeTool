package DataAccess;

import Exceptions.ConnectionInfoException;

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

    /**
     * Private construction method to create single instances of a database connection.
     * As this application has one database source, it's not required to switch between different
     * sources.
     * @see Connection Database.Instance() to obtain a database instance
     */
    private Database() {
        Initalise();
    }

    /**
     * Initalise the database connection using the props file in the root directory
     * and store the connection instance.
     */
    private void Initalise() {
        try {
            // Get connection info object
            DbInfo = new DbConnectionInfo("./db.props");

            // get a connection
            INSTANCE = DriverManager.getConnection(DbInfo.Url(),DbInfo.Username(), DbInfo.Password());
        } catch (SQLException sqle) {
            System.err.println(sqle);
        } catch (ConnectionInfoException cie) {
            System.err.println(cie);
        }
    }

    /**
     * Method to control and obtain the runtime instance of the Database connection.
     * If it does not already exist, a new instance will be created and returned
     * @return The runtime environments current instance of the database connection
     */
    public static Connection Instance() {
        if (INSTANCE == null) {
            new Database();
        }
        return INSTANCE;
    }
}
