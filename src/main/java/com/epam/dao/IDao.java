package com.epam.dao;

import com.epam.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDao <T>{
    List<T> findAll() throws DaoException, SQLException;
    Optional<T> findEntityById(long id) throws DaoException, SQLException;
    boolean delete(long id) throws DaoException, SQLException;
    boolean create(T t) throws DaoException, SQLException;
    boolean update(T t) throws DaoException, SQLException;
}
