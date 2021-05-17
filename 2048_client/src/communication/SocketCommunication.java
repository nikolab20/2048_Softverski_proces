/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import request.Request;
import response.Response;
import utils.PropertiesReader;

/**
 *
 * @author nikolab
 */
public class SocketCommunication {

    private final Socket socket;

    public SocketCommunication() {
        try {
            socket = new Socket(
                    PropertiesReader.getInstance().get("server.socket.host"),
                    Integer.parseInt(PropertiesReader.getInstance().get("server.socket.port"))
            );
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sendRequest(Request request) throws IOException {
        try {
            ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(request);
        } catch (IOException ex) {
            System.out.println("Greska");
            throw new IOException(ex.getMessage());
        }
    }

    public Response readResponse() throws Exception {
        try {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            return (Response) inSocket.readObject();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

}
