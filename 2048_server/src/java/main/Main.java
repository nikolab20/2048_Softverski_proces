/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import start.SocketCommunication;

/**
 *
 * @author nikolab
 */
public class Main {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                new SocketCommunication().startServer();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }).start();
    }
}
