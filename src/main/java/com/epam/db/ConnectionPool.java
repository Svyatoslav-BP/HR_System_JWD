package com.epam.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance;
    private final int connectionPoolSize = 10;

    private static final String URL = "jdbc:postgresql://localhost:5432/HR_System";
    private static final String USER = "postgres";
    private static final String PASSWORD = "27122001";

    private List<Connection> availableConnection = new ArrayList<>(connectionPoolSize);


    public static ConnectionPool getInstance(){
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ConnectionPool(){
        initConnectionPool();
    }

    void initConnectionPool(){
        for (int i = 0; i < connectionPoolSize; i++) {
            try{
                ConnectionProxy connectionProxy = new ConnectionProxy(DriverManager.getConnection(URL,USER,PASSWORD));
                availableConnection.add(connectionProxy);
            }
             catch (SQLException e) {
                 System.out.println("SQL Exception occured" + e);
            }
        }
    }

    public Connection getConnectionFromPool(){
        if(availableConnection.isEmpty()){
            initConnectionPool();
        }
        return availableConnection.get(0);
    }

    public void addConnection(Connection connection) {
            try {
                availableConnection.add(connection);
            } catch (Exception e) {
                System.out.println("Exeption" + e);
            }
    }

}
