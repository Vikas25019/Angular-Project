package com.managementsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;

@Component("daoImpl")
public class DaoImplementation<T, U> implements IDaoInterface<T, U> {

    @Autowired
    MysqlDatabaseOperation<T> mysqlDatabaseOperation;

    @Override
    public void create(T t, U u, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException {

        if (u instanceof MysqlDatabaseOperation) {
            mysqlDatabaseOperation.insertIntoDatabase(t, data);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }

    }

    @Override
    public LinkedHashMap<String, String> retrieve(T t, U u, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        LinkedHashMap<String, String> viewData;
        if (u instanceof MysqlDatabaseOperation) {
            viewData = mysqlDatabaseOperation.retrieveFromDatabase(t, data);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
        return viewData;
    }

    @Override
    public void update(T t, U u, LinkedHashMap<String, String> data, String columnName) throws SQLException, ClassNotFoundException, UnknownHostException {
        if (u instanceof MysqlDatabaseOperation) {
           mysqlDatabaseOperation.updateInDatabase(t, data, columnName);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
    }

    @Override
    public void delete(T t, U u, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        if (u instanceof MysqlDatabaseOperation) {
            mysqlDatabaseOperation.deleteFromDatabase(t, data);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
    }

    @Override
    public List<LinkedHashMap<String, String>> retrieveAll(T t, U u) throws SQLException, ClassNotFoundException {
        List<LinkedHashMap<String, String>> data;
        if (u instanceof MysqlDatabaseOperation) {
            data = mysqlDatabaseOperation.retrieveAll(t);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
        return data;
    }

    @Override
    public <P, E> boolean isIdPresent(P p, E e, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        if (e instanceof MysqlDatabaseOperation) {
            return mysqlDatabaseOperation.checkIdMysql(p, data);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
    }

    @Override
    public String viewMapping(T t, U u, LinkedHashMap<String, String> data, String columnName) throws SQLException, ClassNotFoundException {
        String id;
        if (u instanceof MysqlDatabaseOperation) {
            id = mysqlDatabaseOperation.viewMappingInfo(t, data, columnName);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
        return id;
    }

}
