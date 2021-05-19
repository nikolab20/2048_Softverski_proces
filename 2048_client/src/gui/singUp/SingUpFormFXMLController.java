/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.singUp;

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
public class SingUpFormFXMLController implements Initializable {

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfRePassword;

    @FXML
    private Button btnSingUp;

    @FXML
    private Button btnBackToLogIn;

    @FXML
    private Label lblError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addSingUpListener();
        addBackToLoginListener();
    }

    private void addSingUpListener() {
        btnSingUp.setOnMouseClicked((event) -> {
            String username = tfUsername.getText();
            String password = pfPassword.getText();
            String rePassword = pfRePassword.getText();
            try {
                User user = Controller.getInstance().singUp(username, password, rePassword);
                GameStage.getInstance().setScene("/gui/login/LoginFormFXML.fxml");
            } catch (Exception ex) {
                System.out.println(ex);
                lblError.setText(ex.getMessage());
            }
        });
    }

    private void addBackToLoginListener() {
        btnBackToLogIn.setOnMouseClicked((event) -> {
            GameStage.getInstance().setScene("/gui/login/LoginFormFXML.fxml");
        });
    }

}
