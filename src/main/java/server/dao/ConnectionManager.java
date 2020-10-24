package server.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static ConnectionManager instance;

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private ConnectionManager(String configName) {
        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream(configName);
            properties.load(is);

            driverClassName = properties.getProperty("dataSource.driverClassName");
            url = properties.getProperty("dataSource.url");
            username = properties.getProperty("dataSource.username");
            password = properties.getProperty("dataSource.password");

            Class.forName(driverClassName).newInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static ConnectionManager getInstance() {
        if(instance == null) {
            instance = new ConnectionManager("datasource.properties");
        }
        return instance;
    }
}
