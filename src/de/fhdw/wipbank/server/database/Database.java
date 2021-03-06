package de.fhdw.wipbank.server.database;

import java.sql.*;
import java.util.Properties;

public class Database {

    public static ResultSet query(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        statement.close();
        connection.close();
        return result;
    }

    public static boolean execute(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        boolean result = statement.execute(sql);
        statement.close();
        connection.close();
        return result;
    }

    public static boolean tableExists(String table) {
        try {
            Connection connection = getConnection();
            ResultSet resultSet = connection.getMetaData().getTables("%", "%", "%", new String[] { "TABLE" });
            boolean tableExists = false;
            while (resultSet.next() && !tableExists) {
                if (resultSet.getString("TABLE_NAME").equalsIgnoreCase("STUDENT")) {
                    tableExists = true;
                }
            }
            resultSet.close();
            return tableExists;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:derby:database;create=true", getCredentials());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Properties getCredentials() {
        Properties properties = new Properties();
        properties.put("user", "user");
        return properties;
    }

}
