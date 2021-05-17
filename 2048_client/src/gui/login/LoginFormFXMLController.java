/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.login;

import communication.Controller;
import domain.User;
import gui.GameStage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nikolab
 */
public class LoginFormFXMLController implements Initializable {

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSingUp;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblPassword;
    
    @FXML
    private Label lblError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addLoginListener();
    }

    private void addLoginListener() {
        btnLogin.setOnMouseClicked((event) -> {
            String username = tfUsername.getText();
            String password = pfPassword.getText();
            try {
                User user = Controller.getInstance().logIn(username, password);
                GameStage.getInstance().setScene("/gui/menu/Meni.fxml");
            } catch (Exception ex) {
                System.out.println(ex);
                lblError.setText(ex.getMessage());
            }
        });
    }

}
