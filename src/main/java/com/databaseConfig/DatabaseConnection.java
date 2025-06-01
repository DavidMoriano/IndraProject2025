package com.databaseConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.utils.Config.Configurations;
import com.utils.Config.DatabaseConfigurations;

public class DatabaseConnection {

    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        DatabaseConfigurations dbConfigs = Configurations.getInstance().getDatabaseConfigurations();

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionUrl = "jdbc:mysql://" + dbConfigs.getHost() + ":" + dbConfigs.getPort() + "/" + dbConfigs.getDatabase();

        connection = DriverManager.getConnection(connectionUrl, dbConfigs.getUsername(), dbConfigs.getPassword());

        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Conexión a la base de datos cerrada.");
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión a la base de datos:");
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }
}
