package Game2048.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jukka
 */
import java.sql.*;

public class Database {

    private String databaseAddress;

    /**
     * Constructor for a database
     * @param databaseAddress Location of the database 
     * @throws ClassNotFoundException 
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * Gets connection to the wanted database.
     * @return Conncetion to the database
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
}
