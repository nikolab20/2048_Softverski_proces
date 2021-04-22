/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import db.IDatabaseBroker;
import db.impl.DatabaseBroker;
import domain.DomainObject;

/**
 *
 * @author nikolab
 */
public abstract class GenericOperation<T extends DomainObject, R> {
    
    protected IDatabaseBroker databaseBroker;

    public GenericOperation() {
        databaseBroker = new DatabaseBroker();
    }
    
    
    
}
