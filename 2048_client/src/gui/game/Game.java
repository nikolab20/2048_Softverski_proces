/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.game;

import gui.gameManager.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author nikolab
 */
public class Game extends Application {

    private GameManager gameManager;

    @Override
    public void init() {
        Font.loadFont(Game.class.getResource("ClearSans-Bold.ttf").toExternalForm(), 10.0);
    }

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        gameManager = new GameManager();
        root.getChildren().add(gameManager);

        Scene scene = new Scene(root, 600, 700);

        scene.getStylesheets().add(Game.class.getResource("game.css").toExternalForm());
        root.getStyleClass().addAll("game-root");

        primaryStage.setTitle("2048FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
