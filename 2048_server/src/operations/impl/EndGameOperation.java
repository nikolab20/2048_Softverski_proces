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
public class EndGameOperation extends GenericOperation<Game, Game> {

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
        return RequestOperation.END_GAME;
    }

    @Override
    protected void execute(Game entity) throws Exception {
        databaseBroker.updateRecord(entity);
        this.game = databaseBroker.findRecord(entity)
                .map(Game.class::cast)
                .orElseThrow(() -> new RuntimeException("Game not found!"));

        if (this.game.getScore() == -1) {
            throw new RuntimeException("");
        }
    }

}
