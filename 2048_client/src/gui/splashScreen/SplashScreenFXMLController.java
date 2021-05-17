package gui.splashScreen;

import gui.GameStage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nikolab
 */
public class SplashScreenFXMLController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private ImageView slika;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!Main.isSplashLoaded) {
            loadSplashScreen();
        }
    }

    private void loadSplashScreen() {
        Main.isSplashLoaded = true;
        Image image = new Image("images/2048-512px.png");
        
        slika.setImage(image);
        rootPane.getChildren().setAll(slika);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), rootPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.play();
        fadeIn.setOnFinished((e) -> {
            GameStage.getInstance().setScene("/gui/login/LoginFormFXML.fxml");
        });
    }

}
