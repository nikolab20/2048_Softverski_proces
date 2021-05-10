/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations;

import db.IDatabaseBroker;
import db.impl.DatabaseBroker;
import domain.DomainObject;
import request.RequestOperation;

/**
 *
 * @author nikolab
 */
public abstract class GenericOperation<T extends DomainObject, R> {

    protected IDatabaseBroker databaseBroker;

    public GenericOperation() {
        databaseBroker = new DatabaseBroker();
    }

    protected abstract void validate(T entity) throws Exception;

    public abstract R getResult();

    public abstract RequestOperation getSupportedOperation();

    private void startTransaction() throws Exception {

    }

    protected abstract void execute(T entity) throws Exception;

    private void commitTransaction() throws Exception {

    }

    private void rollbackTransaction() throws Exception {

    }
    
    public final void templateExecute(T entity) throws Exception {
        try {
            validate(entity);
            try {
                startTransaction();
                execute(entity);
                commitTransaction();
            } catch (Exception e) {
                rollbackTransaction();
                System.err.println(e);
                throw e;
            }
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
    }

}
