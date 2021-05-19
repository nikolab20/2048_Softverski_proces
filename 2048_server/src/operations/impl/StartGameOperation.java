/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operations.impl;

import domain.Game;
import operations.GenericOperation;
import request.RequestOperation;

/**
 *
 * @author nikolab
 */
public class StartGameOperation extends GenericOperation<Game, Game> {

    private Game game;

    @Override
    protected void validate(Game entity) throws Exception {
        if (entity.getUserId() == -1L) {
            throw new RuntimeException("User is undefined!");
        }
    }

    @Override
    public Game getResult() {
        return game;
    }

    @Override
    public RequestOperation getSupportedOperation() {
        return RequestOperation.START_GAME;
    }

    @Override
    protected void execute(Game entity) throws Exception {
        Long id = databaseBroker.insertRecord(entity);
        this.game = databaseBroker.findRecord(new Game(id))
                .map(Game.class::cast)
                .orElseThrow(() -> new RuntimeException("Game isn't started!"));
    }

}
