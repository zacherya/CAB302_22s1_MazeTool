package DataAccess;

import Exceptions.ConnectionInfoException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class responsible for configuring and handling database credentials.
 * @author Zac Adams
 */
public class DbConnectionInfo {
    private String url;
    private String username;
    private String password;
    private String schema;

    /**
     * Initialise a new instance of database connection info.
     * Session database info will be stored in here. Methods for modifying db session info can
     * go here, including save, read or reload.
     * @param file The location to the database connection file including file type and name
     * @throws ConnectionInfoException if the file can't be located, or if there is an issue making the request
     * @author Zac Adams, CAB302 Practical Files
     */
    public DbConnectionInfo(String file) throws ConnectionInfoException {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("./db.props");
            props.load(in);
            in.close();

            setProps(props);

        } catch (FileNotFoundException fnfe) {
            throw new ConnectionInfoException(fnfe.getLocalizedMessage());
        } catch (IOException ex) {
            throw new ConnectionInfoException(ex.getLocalizedMessage());
        }
    }

    /**
     * Set the Connection Info properties to equal the file's data values.
     * Also handle string sanitation and validation.
     * @param props The properties loaded from the files input stream
     * @author Zac Adams, CAB302 Practical Files
     */
    private void setProps(Properties props) {
        // specify the data source, username and password
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");
        schema = props.getProperty("jdbc.schema");
    }

    /**
     * Method to concat the url and schema as a string for DriverManager to point
     * to a specific database engine and database
     * @return A string containing the url and schema separated by a forward slash '/'
     * @author Zac Adams
     */
    public String Url() {
        return String.format("%s/%s",url,schema);
    }

    /**
     * Method to access the connection info instance's username
     * @return The username for the given connection info
     * @author Zac Adams
     */
    public String Username() {
        return username;
    }

    /**
     * Method to access the connection info instance's password
     * @return The password for the given connection info
     * @author Zac Adams
     */
    public String Password() {
        return password;
    }
}
