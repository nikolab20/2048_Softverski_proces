/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Panel;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.Main;

/**
 *
 * @author nikolab
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!Main.isSplashLoaded) {
            loadSplashScreen();
        }
    }

    private void loadSplashScreen() {
        try {
            Main.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource("/gui/splashScreen/SplashScreenFXML.fxml"));
            root.getChildren().setAll(pane);
            Image image = new Image("/images/2048-512px.png");
            ((ImageView) pane.getChildren().get(0)).setImage(image);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                try {
                    GridPane loginPanel = FXMLLoader.load(getClass().getResource("/gui/login/LoginFormFXML.fxml"));
                    pane.getChildren().setAll(loginPanel);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
