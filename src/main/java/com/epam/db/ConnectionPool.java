package com.epam.db;

import com.epam.domain.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConnectionPool {
    private static ConnectionPool instance;

    private final ApplicationProperties applicationProperties = new ApplicationProperties();

    private final int connectionPoolSize = applicationProperties.getConnectionPoolSize();
    private final int maxConnectionPoolSize = applicationProperties.getMaxConnectionPoolSize();
    private final int poolIncreaseStep = applicationProperties.getPoolIncreaseStep();

    private final String URL = applicationProperties.getURL();
    private final String USER = applicationProperties.getUSER();
    private final String PASSWORD = applicationProperties.getPASSWORD();

    private ArrayDeque<Connection> availableConnections = new ArrayDeque<>(connectionPoolSize);
    private ArrayDeque<Connection> takenConnections = new ArrayDeque<>(connectionPoolSize);


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
        createConnections(connectionPoolSize);
    }

    public Connection getConnectionFromPool(){
        Connection connection = null;
        if(!availableConnections.isEmpty()){
            connection = availableConnections.peekFirst();
        }
        else if(availableConnections.size() + takenConnections.size() < maxConnectionPoolSize){
            createConnections(poolIncreaseStep);
            connection = availableConnections.peekFirst();
        }
        if (Objects.nonNull(connection)) {
            availableConnections.pop();
            takenConnections.addLast(connection);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
            try {
                availableConnections.addLast(connection);
                takenConnections.remove(connection);
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
    }

    private void createConnections(final int size){
        for (int i = 0; i < size; i++) {
            try{
                Connection connection = new ConnectionProxy(DriverManager.getConnection(URL,USER,PASSWORD));
                availableConnections.addLast(connection);
            }
            catch (SQLException e) {
                System.out.println("SQL Exception occurred" + e);
            }
        }
    }

}
