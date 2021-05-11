/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

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
}
