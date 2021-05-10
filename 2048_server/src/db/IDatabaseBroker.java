/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.DomainObject;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author nikolab
 */
public interface IDatabaseBroker {
    List<DomainObject> getAllRecord(DomainObject entity) throws SQLException;
    Optional<DomainObject> findRecord(DomainObject entity) throws SQLException;
    List<DomainObject> findRecords(DomainObject entity, DomainObject parent) throws SQLException;
    Long insertRecord(DomainObject entity) throws SQLException;
    void deleteRecord(DomainObject entity) throws SQLException;
    void updateRecord(DomainObject entity, DomainObject entityld) throws Exception;
    void updateRecord(DomainObject entity) throws SQLException;
}
