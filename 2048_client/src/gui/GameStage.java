/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author nikolab
 */
public class GameStage {

    private final Stage stage;
    private static GameStage instance;

    public GameStage(Stage stage) {
        this.stage = stage;
    }

    public synchronized static void setStage(Stage stage) {
        if (instance == null) {
            instance = new GameStage(stage);
        }
    }

    public static GameStage getInstance() {
        if (instance == null) {
            throw new RuntimeException("stage is not set");
        }
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setScene(String FXMLResourcePath) {
        try {
            stage.hide();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(FXMLResourcePath))));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            stage.show();
            setPosition();
        }
    }

    public void setScene(Scene scene) {
        stage.hide();
        stage.setScene(scene);
        stage.show();
        setPosition();
    }

    private void setPosition() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
