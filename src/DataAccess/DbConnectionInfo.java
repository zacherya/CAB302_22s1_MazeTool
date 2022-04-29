package DataAccess;

import java.io.IOException;
import java.util.Properties;

public class DbConnectionInfo {
    private String url;
    private String username;
    private String password;
    private String schema;

    public DbConnectionInfo(Properties props) {
        // specify the data source, username and password
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");
        schema = props.getProperty("jdbc.schema");
    }

    public String Url() {
        return url+"/"+schema;
    }
    public String Username() {
        return username;
    }
    public String Password() {
        return password;
    }
}
