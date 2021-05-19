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
public class UpdateUserOperation extends GenericOperation<User, User> {

    private User user;

    @Override
    protected void validate(User entity) throws Exception {
        if (entity.getId() == -1L) {
            throw new RuntimeException("User not exists!");
        }
    }

    @Override
    public User getResult() {
        return user;
    }

    @Override
    public RequestOperation getSupportedOperation() {
        return RequestOperation.UPDATE_USER;
    }

    @Override
    protected void execute(User entity) throws Exception {
        databaseBroker.updateRecord(entity);
        user = entity;
    }
}
