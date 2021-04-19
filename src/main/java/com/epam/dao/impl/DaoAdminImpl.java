package com.epam.dao.impl;

import com.epam.dao.IDao;
import com.epam.db.ConnectionPool;
import com.epam.entity.Admin;
import com.epam.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoAdminImpl implements IDao<Admin> {
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
    public List<Admin> findAll() throws DaoException, SQLException {
        List<Admin> resultCollection = new ArrayList<>();
        Connection connection = null;
        Statement statement ;

        try {
            connection = ConnectionPool.getInstance().getConnectionFromPool();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT admin.id, account.login, account.password, account.email \n" +
                    "FROM account\n" +
                    "INNER JOIN admin ON account.id = admin.account_id");
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
            connection.close();
        }
        return resultCollection;
    }

    @Override
    public Optional<Admin> findEntityById(long id) throws DaoException, SQLException {
        Connection connection = null;
        Statement statement ;
        Admin admin = null;

        try {
            connection = ConnectionPool.getInstance().getConnectionFromPool();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT admin.id, account.login, account.password, account.email \n" +
                    "FROM account\n" +
                    "INNER JOIN admin ON account.id = admin.account_id");
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
            connection.close();
        }
        return Optional.ofNullable(admin);
    }

    @Override
    public boolean delete(long id) throws DaoException, SQLException {
        boolean flag = false;
        Connection connection = null;
        Statement statement;

        try {
            connection = ConnectionPool.getInstance().getConnectionFromPool();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM account USING admin WHERE account.id = admin.account_id AND admin.id = "+id );
            flag = true;
        } catch (SQLException e) {
            log.error("Can't delete Admin");
            throw new DaoException(e);
        }
        finally {
            connection.close();
        }
        log.info("Delete Admin");
        return flag;
    }

    @Override
    public boolean create(Admin admin) throws DaoException, SQLException {
        boolean flag = false;
        Connection connection = null;
        Statement statement;

        try {
            connection = ConnectionPool.getInstance().getConnectionFromPool();
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
            flag = true;
        } catch (SQLException e) {
            log.error("Can't create Admin");
            throw new DaoException(e);
        }
        finally {
            connection.close();
        }
        log.info("Create Admin");
        return flag;
    }

    @Override
    public boolean update(Admin admin) throws DaoException, SQLException {
        boolean flag = false;
        Connection connection = null;
        Statement statement;

        try {
            connection = ConnectionPool.getInstance().getConnectionFromPool();
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE account\n" +
                    "SET password = '"+admin.getPassword()+"', " +
                    "login = '"+admin.getLogin()+"', " +
                    "email = '"+admin.getEmail()+"' \n" +
                    "FROM admin\n" +
                    "WHERE account.id = admin.account_id AND admin.id = "+ admin.getId() );
            flag = true;
        } catch (SQLException e) {
            log.error("Can't update Admin");
            throw new DaoException(e);
        }
        finally {
            connection.close();
        }
        log.info("Update Admin");
        return flag;
    }
}
