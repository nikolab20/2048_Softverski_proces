/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.GameStage;
import gui.game.Game;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author nikolab
 */
public class Main extends Application {

    public static boolean isSplashLoaded = false;

    @Override
    public void init() throws Exception {
        Font.loadFont(Game.class.getResource("ClearSans-Bold.ttf").toExternalForm(), 10.0);
    }
    

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("2048 Game");
        stage.setResizable(true);
        GameStage.setStage(stage);
        stage.show();
        GameStage.getInstance().setScene("/gui/splashScreen/SplashScreenFXML.fxml");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
