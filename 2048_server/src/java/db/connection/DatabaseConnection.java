/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import settings.SettingsLoader;

/**
 *
 * @author nikolab
 */
public class DatabaseConnection {

    private final Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection() throws SQLException {
        String dbUrl = SettingsLoader.getInstance().getProperty("db.url");
        String dbUser = SettingsLoader.getInstance().getProperty("db.user");
        String dbPassword = SettingsLoader.getInstance().getProperty("db.password");
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        connection.setAutoCommit(false);
    }

    public static DatabaseConnection getInstance() {
        try {
            if (instance == null) {
                instance = new DatabaseConnection();
            }
            return instance;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
    }
}
