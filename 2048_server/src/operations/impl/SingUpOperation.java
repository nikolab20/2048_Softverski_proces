/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations.impl;

import domain.User;
import operations.GenericOperation;
import request.RequestOperation;

/**
 *
 * @author nikolab
 */
public class SingUpOperation extends GenericOperation<User, User> {

    @Override
    protected void validate(User entity) throws Exception {
        if (entity.getUsername() == null || entity.getUsername().isEmpty()) {
            throw new Exception("Username is required!");
        }
        if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
            throw new Exception("Password is required!");
        }
    }

    @Override
    public User getResult() {
        return null;
    }

    @Override
    public RequestOperation getSupportedOperation() {
        return RequestOperation.SING_UP;
    }

    @Override
    protected void execute(User entity) throws Exception {
        boolean exists = databaseBroker.findRecord(new User(entity.getUsername()))
                .map(User.class::cast)
                .isPresent();

        if (!exists) {
            databaseBroker.insertRecord(entity);
        } else {
            throw new RuntimeException("Username already exists!");
        }
    }

}
