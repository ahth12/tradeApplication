package dao;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DatabaseManager {
    private static final String DRIORG_POSTGRESQL_DRIVER = "org.postgresql.Driver";
    private static Connection connection = null;
    private static final String CONNECTION_URL = "jdbc:postgresql://ec2-52-203-118-49.compute-1.amazonaws.com:5432/d4r638sfaktbf7?sslmode=require&currentschema=cashtest";
    private static final String USER = "mrbwoimaqptqdk";
    private static final String PASS = "14f6de1ee720bb9c9e0e50ad36aed3c187099a26735eabfb0958541520f94180";

    public static void databaseConnect() throws ClassNotFoundException, SQLException {
        Class.forName(DRIORG_POSTGRESQL_DRIVER);
        System.out.println("Driver registred");
        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }

    public static void databaseDisconnect() throws SQLException {
        if (connection != null && connection.isClosed() ){
            connection.close();
        }
    }

    public static ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            databaseConnect();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        }
        finally {
            if (resultSet != null ) {
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            databaseDisconnect();
        }
        return crs;
    }

    public static void updateQuery(String query) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        try{
            databaseConnect();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        finally {
            if (statement != null){
                statement.close();
            }
            databaseDisconnect();
        }
    }
}
