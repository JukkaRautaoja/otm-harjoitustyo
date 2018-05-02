package game2048.dao;

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
import java.util.*;

public class Database {

    private String databaseAddress;

    /**
     * Constructor for a database
     * @param databaseAddress Location of the database 
     * @throws ClassNotFoundException 
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
        this.init();
    }

    /**
     * Gets connection to the wanted database.
     * @return Conncetion to the database
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        String dbURL = System.getenv("JDBC_DATABASE_URL");
        if (dbURL != null && dbURL.length() > 0){
            return DriverManager.getConnection(databaseAddress);
        }
        return DriverManager.getConnection(databaseAddress);
    }
    /**
     * Initialises database if it does not already exists.
     */
    public void init() {
        List<String> commands = this.sqliteCommands();
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String com : commands) {
                System.out.println("Running command >> " + com);
                st.executeUpdate(com);
            }

        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());
        }
    }
    
    private List<String> sqliteCommands() {
        ArrayList<String> list = new ArrayList<>();
        list.add("CREATE TABLE Score (id integer PRIMARY KEY, name varchar, maxScore integer);");
        return list;
    }
}
