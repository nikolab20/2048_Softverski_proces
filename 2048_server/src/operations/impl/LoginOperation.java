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
public class LoginOperation extends GenericOperation<User, User> {

    private User user;

    @Override
    protected void validate(User entity) throws Exception {
        if (entity.getUsername() == null || entity.getPassword() == null) {
            throw new RuntimeException("Username and password are required!");
        }
    }

    @Override
    public User getResult() {
        return user;
    }

    @Override
    public RequestOperation getSupportedOperation() {
        return RequestOperation.LOG_IN;
    }

    @Override
    protected void execute(User entity) throws Exception {
        user = databaseBroker.findRecord(entity)
                .map(User.class::cast)
                .orElseThrow(() -> new RuntimeException("Bad username!"));
        if (!user.getPassword().equals(entity.getPassword())) {
            throw new RuntimeException("Bad password!");
        }
    }

}
