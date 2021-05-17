package gui.menu;

import domain.Direction;
import gui.GameStage;
import gui.game.Game;
import gui.gameManager.GameManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nikolab
 */
public class MeniController implements Initializable {

    private GameManager gameManager;
    private boolean gameStarted = false;

    @FXML
    private MenuItem start;

    @FXML
    private AnchorPane root;

    @FXML
    private StackPane stackPane;

    @FXML
    private void startGame(ActionEvent event) {
        gameManager = new GameManager();
        stackPane.getChildren().add(gameManager);

        GameStage.getInstance().getStage().getScene().getStylesheets().add(Game.class.getResource("game.css").toExternalForm());
        stackPane.getStyleClass().addAll("game-root");
        GameStage.getInstance().getStage().setResizable(false);

        GameStage.getInstance().getStage().getScene().setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.isArrowKey()) {
                Direction dir = Direction.valueFor(keyCode);
                gameManager.move(dir);
            }
        });

        if (gameStarted) {
            start.setText("Reset");
        } else {
            start.setText("Start");
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        GameStage.getInstance().getStage().close();
    }

    @FXML
    private void viewAllTheTime(ActionEvent event) {
    }

    @FXML
    private void viewTop10(ActionEvent event) {
    }

    @FXML
    private void viewAboutGame(ActionEvent event) {
    }

    @FXML
    private void viewAboutAuthor(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
