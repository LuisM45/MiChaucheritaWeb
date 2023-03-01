package edu.epn.web.b2022.g6.appweb.chauchera.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargarda de establecer la conexión con la base de datos MySQL
 * definida por config.ini
 * @author luism
 */
public abstract class MySQLConnection {
    
    /**
     * 
     * @return Retorna una Connection del servidor MySQL definido
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException{
        String host = Config.getProperty("server.db.host.address");
        String port = Config.getProperty("server.db.host.port");
        String database = Config.getProperty("server.db.database");
        String user = Config.getProperty("server.db.user");
        String password = Config.getProperty("server.db.password");
        
        return DriverManager.getConnection(
                "jdbc:mysql://"+host+":"+port+"/"+database,
                user,
                password);
    }
    
}
