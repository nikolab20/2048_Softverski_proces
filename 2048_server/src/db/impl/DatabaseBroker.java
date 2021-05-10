/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.impl;

import db.IDatabaseBroker;
import db.connection.DatabaseConnection;
import domain.DomainObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author nikolab
 */
public class DatabaseBroker implements IDatabaseBroker {

    @Override
    public List<DomainObject> getAllRecord(DomainObject entity) throws SQLException {
        List<DomainObject> objects = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(entity.getTableName());
        String query = sb.toString();
        PreparedStatement statement = DatabaseConnection.getInstance()
                .getConnection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            objects.add(entity.getNewRecord(rs));

        }
        return objects;
    }

    @Override
    public Optional<DomainObject> findRecord(DomainObject entity) throws SQLException {
        String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getWhereCondition();
        System.out.println(query);
        Statement st = DatabaseConnection.getInstance()
                .getConnection().prepareStatement(query);
        ResultSet rs = st.executeQuery(query);
        boolean signal = rs.next();
        if (signal == true) {
            return Optional.of(entity.getNewRecord(rs));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public List<DomainObject> findRecords(DomainObject entity, DomainObject parent) throws SQLException {
        List<DomainObject> objects = new LinkedList<>();
        String query = "SELECT * FROM " + entity.getTableName()
                + " WHERE " + parent.getWhereCondition();
        System.out.println(query);
        PreparedStatement statement = DatabaseConnection.getInstance()
                .getConnection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            objects.add(entity.getNewRecord(rs));
        }
        return objects;
    }

    @Override
    public Long insertRecord(DomainObject entity) throws SQLException {
        String upit = "INSERT INTO " + entity.getTableName() + " ("
                + entity.getAtributeNames() + ") VALUES (" + entity.getAtributeValues() + ")";
        return executeUpdate(upit);
    }

    @Override
    public void deleteRecord(DomainObject entity) throws SQLException {
        String upit = "DELETE FROM " + entity.getTableName() + " WHERE "
                + entity.getWhereCondition();
        executeUpdate(upit);
    }

    @Override
    public void updateRecord(DomainObject entity, DomainObject entityld) throws Exception {
        String upit = "UPDATE " + entity.getTableName() + " SET "
                + entity.setAtributeValues() + " WHERE " + entityld.getWhereCondition();
        executeUpdate(upit);
    }

    @Override
    public void updateRecord(DomainObject entity) throws SQLException {
        String upit = "UPDATE " + entity.getTableName() + " SET "
                + entity.setAtributeValues() + " WHERE " + entity.getWhereCondition();
        executeUpdate(upit);
    }

    private Long executeUpdate(String query) throws SQLException {
        System.out.println(query);
        try (Statement st = DatabaseConnection.getInstance().getConnection()
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int rowcount = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            if (rowcount <= 0) {
                throw new RuntimeException("Update query not executed");
            }
            try (ResultSet rs = st.getGeneratedKeys()) {
                rs.next();
                try {
                    return rs.getLong(1);
                } catch (SQLException e) {
                    return -1L;
                }
            }
        }
    }

}
