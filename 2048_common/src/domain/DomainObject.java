/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author nikolab
 */
public interface DomainObject extends Serializable {

    String getTableName();

    String getAtributeValues();

    String getAtributeNames();

    String setAtributeValues();

    String getNameByColumn(int i);

    String getWhereCondition();

    DomainObject getNewRecord(ResultSet rs);
}
