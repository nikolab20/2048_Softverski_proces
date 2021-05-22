/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import domain.Game;
import domain.User;
import java.util.Date;
import request.Request;
import request.RequestOperation;
import response.Response;
import response.ResponseStatus;
import utils.UserSession;

/**
 *
 * @author nikolab
 */
public class Controller {

    private static Controller instance;
    private final SocketCommunication socketCommunication;
    private Game game;

    private Controller() {
        socketCommunication = new SocketCommunication();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public User logIn(String username, String password) throws Exception {
        User user = new User(username, password);
        Request request = new Request(RequestOperation.LOG_IN, user);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            System.out.println(response.getPayload());
            return (User) response.getPayload();
        }
        throw response.getException();
    }

    public User singUp(String username, String password, String rePassword) throws Exception {
        if (!password.equals(rePassword)) {
            throw new RuntimeException("Password isn't confirmed!");
        }

        User user = new User(username, password);
        Request request = new Request(RequestOperation.SING_UP, user);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            return (User) response.getPayload();
        }
        throw response.getException();
    }

    public User update(User user) throws Exception {
        Request request = new Request(RequestOperation.UPDATE_USER, user);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            return (User) response.getPayload();
        }
        throw response.getException();
    }

    public Game startGame() throws Exception {
        if (game != null) {
            this.endGame();
        }
        Game r = new Game(UserSession.getInstance().getUser().getId(), 0, new java.sql.Date(new Date().getTime()));
        Request request = new Request(RequestOperation.START_GAME, r);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.game = (Game) response.getPayload();
            UserSession.getInstance().setScore(0);
            return this.game;
        }
        throw response.getException();
    }

    public Game endGame() throws Exception {
        this.game.setScore(UserSession.getInstance().getScore());
        Request request = new Request(RequestOperation.END_GAME, game);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            this.game = null;
            return (Game) response.getPayload();
        }
        throw response.getException();
    }
}
