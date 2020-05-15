package com.managementsystem.dao;

import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.*;

@Component("daoImpl")
public class DaoImplementation<T, U> implements IDaoInterface<T> {
    //@value("com.mysql.jdbc.Driver")
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //JDBC Driver Name
    private final String DB_URL = "jdbc:mysql://localhost/client_database?autoReconnect=true&useSSL=false"; // JDBC DataBase Url
    private final String USER = "root";
    private final String PASS = "root";

    private final String insertQuery = "INSERT INTO %s values ( %s )";
    private final String selectQuery = "SELECT * FROM %s WHERE %s = %s";
    private final String deleteQuery = "DELETE FROM %s where %s = %s";
    private final String updateQuery = "UPDATE %s SET %s where %s = %s";
    private final String checkQuery = "SELECT count(*) from %s WHERE %s = %s";

    private Connection mySqlDbConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        //Step 1 Register JDBC driver
        Class.forName(JDBC_DRIVER);
        //Step 2 Open a connection
        connection = DriverManager.getConnection(DB_URL, USER, PASS);

        return connection;
    }

    @Override
    public void create(T t, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException {
        String tableName = t.getClass().getSimpleName().toLowerCase();
        Connection connection = this.mySqlDbConnection();
        StringBuilder insertValues = new StringBuilder();

        String select = "select * from %s";
        Statement statement = connection.createStatement();
        String query = String.format(select, tableName);
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData md = rs.getMetaData();

        int col = md.getColumnCount();
        List<String> listOfColumns = new ArrayList<>();
        for (int i = 1; i <= col; i++) {
            String columnName = md.getColumnName(i);
            listOfColumns.add(columnName);
        }

        if (data != null && data.size() > 0) {
            insertValues.append("?");
        }
        for (int i = 1; i < data.size(); i++) {
            insertValues.append(",?");
        }

        String insertSql = String.format(insertQuery, tableName, insertValues);
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

        int i = 1;
        for (String result : listOfColumns) {
            preparedStatement.setString(i, data.get(result));
            i++;
        }

        preparedStatement.executeUpdate();
    }

    @Override
    public LinkedHashMap<String, String> retrieve(T t, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        String columnName = "";
        String select = "select * from %s";
        String tableName = t.getClass().getSimpleName().toLowerCase();
        String query = String.format(select, tableName);

        Set<String> columnsSet = data.keySet();
        for (String name : columnsSet) {
            columnName = name;
        }
        String id = data.get(columnName); //search element
        Connection conn = this.mySqlDbConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData md = rs.getMetaData();
        int col = md.getColumnCount();
        columnsSet = new HashSet<>();
        for (int i = 1; i <= col; i++) {
            String column = md.getColumnName(i);
            columnsSet.add(column);
        }

        String selectSql = String.format(selectQuery, tableName, columnName, id);
        ResultSet results = statement.executeQuery(selectSql);
        LinkedHashMap<String, String> viewData = new LinkedHashMap<>();
        while (results.next()) {
            for (String columns : columnsSet) {
                String result = results.getString(columns);
                viewData.put(columns, result);
            }
        }
        return viewData;
    }

    @Override
    public void update(T t, LinkedHashMap<String, String> data, String columnName) throws SQLException, ClassNotFoundException, UnknownHostException {
        String tableName = t.getClass().getSimpleName().toLowerCase();
        StringBuilder insertColumns = new StringBuilder();
        Set<String> listOfColumns = data.keySet();

        Iterator<String> columns = listOfColumns.iterator();
        insertColumns.append(columns.next() + "= ?");
        while (columns.hasNext()) {
            insertColumns.append(" , " + columns.next() + "= ? ");
        }

        Connection conn = this.mySqlDbConnection();

        String id = data.get(columnName);

        String updateSql = String.format(updateQuery, tableName, insertColumns, columnName, "?");

        PreparedStatement preparedStatement = conn.prepareStatement(updateSql);

        int i = data.size() + 1;
        preparedStatement.setString(i, id);

        int j = 1;
        for (String result : listOfColumns) {
            preparedStatement.setString(j, data.get(result));
            j++;
        }

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(T t, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        String columnName = "";
        Set<String> columns = data.keySet();

        for (String name : columns) {
            columnName = name;
        }
        String id = data.get(columnName);
        String tableName = t.getClass().getSimpleName().toLowerCase();
        Connection conn = this.mySqlDbConnection();
        Statement statement = conn.createStatement();

        String deleteSql = String.format(deleteQuery, tableName, columnName, id);
        statement.executeUpdate(deleteSql);

    }

    @Override
    public List<LinkedHashMap<String, String>> retrieveAll(T t) throws SQLException, ClassNotFoundException {
        String tableName = t.getClass().getSimpleName().toLowerCase();
        String select = "select * from %s";
        String query = String.format(select, tableName);
        Connection conn = this.mySqlDbConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData md = rs.getMetaData();
        int col = md.getColumnCount();
        Set<String> columnsSet = new HashSet<>();
        for (int i = 1; i <= col; i++) {
            String column = md.getColumnName(i);
            columnsSet.add(column);
        }
        List<LinkedHashMap<String, String>> data = new ArrayList<>();
        while (rs.next()) {
            LinkedHashMap<String, String> output = new LinkedHashMap<>();
            for (String name : columnsSet) {
                String result = rs.getString(name);
                output.put(name, result);
            }
            data.add(output);
        }
        return data;
    }

    @Override
    public <P> boolean isIdPresent(P p, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        String columnName = "";
        Set<String> column = data.keySet();
        for (String name : column) {
            columnName = name;
        }

        String tableName = p.getClass().getSimpleName().toLowerCase();
        Connection connection = this.mySqlDbConnection();

        String id = data.get(columnName);
        final String queryCheck = String.format(checkQuery, tableName, columnName, "?");
        final PreparedStatement preparedStatement = connection.prepareStatement(queryCheck);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            final int count = resultSet.getInt(1);
            if (count != 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String viewMapping(T t, LinkedHashMap<String, String> data, String findColumnValue) throws SQLException, ClassNotFoundException {
        String columnName = "";
        Set<String> column = data.keySet();
        for (String name : column) {
            columnName = name;
        }

        String tableName = t.getClass().getSimpleName().toLowerCase();
        String id = data.get(columnName);

        String select = "select * from %s";
        String query = String.format(select, tableName);
        Connection conn = this.mySqlDbConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData md = rs.getMetaData();
        int col = md.getColumnCount();

        boolean flag = false;
        for (int i = 1; i <= col; i++) {
            if (md.getColumnName(i).equals(findColumnValue)) {
                flag = true;
            }
        }
        String idValue = "";
        if (flag) {
            String query1 = "SELECT %s FROM %s where %s = %s";
            rs = statement.executeQuery(String.format(query1, findColumnValue, tableName, columnName, id));
            while (rs.next()) {
                idValue = rs.getString(findColumnValue);
            }
        } else {
            idValue = "";
        }

        return idValue;
    }
}


