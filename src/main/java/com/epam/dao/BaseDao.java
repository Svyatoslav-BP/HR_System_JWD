package com.epam.dao;

import com.epam.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T>{
    List<T> findAll() throws DaoException;
    Optional<T> findEntityById(long id) throws DaoException;
    boolean delete(long id) throws DaoException;
    boolean create(T t) throws DaoException;
    boolean update(T t) throws DaoException;
}
