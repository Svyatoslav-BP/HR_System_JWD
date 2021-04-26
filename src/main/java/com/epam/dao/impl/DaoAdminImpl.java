package com.epam.dao.impl;

import com.epam.dao.BaseDao;
import com.epam.db.ConnectionPool;
import com.epam.domain.Admin;
import com.epam.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoAdminImpl implements BaseDao<Admin> {
    private static DaoAdminImpl instance;
    private static final Logger log = Logger.getLogger(DaoAdminImpl.class);

    private DaoAdminImpl() {
    }

    public static DaoAdminImpl getInstance(){
        if(instance==null) {
            instance = new DaoAdminImpl();
        }
        return instance;
    }

    @Override
    public List<Admin> findAll() throws DaoException{
        List<Admin> resultCollection = new ArrayList<>();
         Connection connection = ConnectionPool.getInstance().getConnectionFromPool();

        try (PreparedStatement statement = connection.prepareStatement("SELECT admin.id, account.login, account.password, account.email \n" +
                "FROM account\n" +
                "INNER JOIN admin ON account.id = admin.account_id")){
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("id"));
                admin.setEmail(resultSet.getString("email"));
                admin.setLogin(resultSet.getString("login"));
                admin.setPassword(resultSet.getString("password"));
                resultCollection.add(admin);
            }
            log.info("Find all Admins");
        } catch (SQLException e) {
            log.error("Can't find all Admins");
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Can't close connection : " + e);
            }
        }
        return resultCollection;
    }

    @Override
    public Optional<Admin> findEntityById(long id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
        Admin admin = null;

        try(PreparedStatement statement = connection.prepareStatement("SELECT admin.id, account.login, account.password, account.email \n" +
                "FROM account\n" +
                "INNER JOIN admin ON account.id = admin.account_id");) {
           final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (id == resultSet.getLong("id")) {
                    admin = new Admin();
                    admin.setId(resultSet.getLong("id"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setLogin(resultSet.getString("login"));
                    admin.setPassword(resultSet.getString("password"));
                    log.info("Find Admin");
                }
            }
        } catch (SQLException e) {
            log.error("Can't find Admin by ID");
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Can't close connection : " + e);
            }
        }
        return Optional.ofNullable(admin);
    }

    @Override
    public boolean delete(long id) throws DaoException {
        boolean result = true;
        Connection connection = ConnectionPool.getInstance().getConnectionFromPool();

        try (PreparedStatement statement= connection.prepareStatement("DELETE FROM account USING admin WHERE account.id = admin.account_id AND admin.id = ? " )){
            statement.setLong(1,id);
            statement.execute();
        } catch (SQLException e) {
            result = false;
            log.error("Can't delete Admin");
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Can't close connection : " + e);
            }
        }
        log.info("Delete Admin");
        return result;
    }

    @Override
    public boolean create(Admin admin) throws DaoException {
        boolean result = true;
        Connection connection = ConnectionPool.getInstance().getConnectionFromPool();
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO account\n" +
                    "(login, password, email)\n" +
                    "values ('"+admin.getLogin()+"','"+admin.getPassword()+"' ,'"+ admin.getEmail()+"');" );

            ResultSet resultSet = statement.executeQuery("SELECT account.id, account.login\n" +
                    "FROM account");
            while (resultSet.next()) {
                if(resultSet.getString("login").equals(admin.getLogin())) {
                    long tempAccountId = resultSet.getLong("id");
                    statement.executeUpdate("INSERT INTO admin\n" +
                            "(account_id)\n" +
                            "values ('" + resultSet.getLong("id") + "'); ");
                    ResultSet resultSet1 = statement.executeQuery("SELECT admin.id, admin.account_id\n" +
                            "FROM admin");
                    while (resultSet1.next()){
                        if(resultSet1.getLong("account_id")==tempAccountId){
                            admin.setId(resultSet1.getLong("id"));
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            result = false;
            log.error("Can't create Admin");
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Can't close connection : " + e);
            }
        }
        log.info("Create Admin");
        return result;
    }

    @Override
    public boolean update(Admin admin) throws DaoException {
        boolean result = true;
        Connection connection = ConnectionPool.getInstance().getConnectionFromPool();

        try(PreparedStatement statement = connection.prepareStatement("UPDATE account\n" +
                "SET password = ?, " +
                "login = ?, " +
                "email = ? \n" +
                "FROM admin\n" +
                "WHERE account.id = admin.account_id AND admin.id = ?")) {
            statement.setString(1,admin.getPassword());
            statement.setString(2,admin.getLogin());
            statement.setString(3,admin.getEmail());
            statement.setLong(4,admin.getId());
            statement.execute();
        } catch (SQLException e) {
            result = false;
            log.error("Can't update Admin");
            throw new DaoException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Can't close connection : " + e);
            }
        }
        log.info("Update Admin");
        return result;
    }
}
