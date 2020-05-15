package com.managementsystem.dao;

import java.net.UnknownHostException;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.List;

public interface IDaoInterface<T> {
    void create(T t,LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException;

    LinkedHashMap<String, String> retrieve(T t,LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException;

    void update(T t,LinkedHashMap<String, String> data ,String columnName) throws SQLException, ClassNotFoundException, UnknownHostException;

    void delete(T t,LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException;

    <P> boolean isIdPresent(P p, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException;

    List<LinkedHashMap<String,String>> retrieveAll(T t) throws SQLException, ClassNotFoundException;

    String viewMapping(T t,LinkedHashMap<String, String> data ,String columnName) throws SQLException, ClassNotFoundException;
}
