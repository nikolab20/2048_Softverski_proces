/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller_client.Controller;
import controller_client.Controller_Service;

/**
 *
 * @author nikolab
 */
public class GuiController {
    
    Controller_Service service;
    Controller controller;

    public GuiController() {
        service = new Controller_Service();
        controller = service.getControllerPort();
    }
    
    
    
}
