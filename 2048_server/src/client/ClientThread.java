/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import operations.GenericOperation;
import operations.OperationFinder;
import request.Request;
import response.Response;
import response.ResponseStatus;

/**
 *
 * @author nikolab
 */
public class ClientThread extends Thread {

    private final Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }

    private void handleRequest() throws IOException, ClassNotFoundException {
        while (!isInterrupted()) {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Request request = (Request) in.readObject();
            Response response = new Response();
            System.out.println("Operation: " + request.getOperation());

            try {
                GenericOperation operation = OperationFinder.findOperation(request.getOperation());
                operation.templateExecute(request.getData());
                response.setStatus(ResponseStatus.SUCCESS);
                response.setPayload(operation.getResult());

            } catch (Exception ex) {
                response.setStatus(ResponseStatus.ERROR);
                response.setException(ex);
            }

            sendResponse(response);
        }
    }

    private void sendResponse(Response response) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(response);
    }
}
