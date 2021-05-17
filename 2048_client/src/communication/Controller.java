/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import domain.User;
import request.Request;
import request.RequestOperation;
import response.Response;
import response.ResponseStatus;

/**
 *
 * @author nikolab
 */
public class Controller {

    private static Controller instance;
    private final SocketCommunication socketCommunication;

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
            return (User) response.getPayload();
        }
        throw response.getException();
    }
}
