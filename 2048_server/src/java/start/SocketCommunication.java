/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import client.ClientThread;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author nikolab
 */
public class SocketCommunication {

    private final boolean active = true;

    public void startServer() throws Exception {
        ServerSocket ss = new ServerSocket(9004);
        System.out.println("Server started...");
        while (active) {
            Socket socket = ss.accept();
            System.out.println("New client");
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();
        }
    }
}
