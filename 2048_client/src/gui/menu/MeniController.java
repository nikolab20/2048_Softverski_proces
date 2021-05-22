package gui.menu;

import communication.Controller;
import domain.Direction;
import gui.GameStage;
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
    private MenuItem undo;

    @FXML
    private AnchorPane root;

    @FXML
    private StackPane stackPane;

    @FXML
    private void startGame(ActionEvent event) {
        try {
            if (gameStarted) {
                gameManager.resetGame(true);
            } else {
                gameManager = new GameManager();
                stackPane.getChildren().add(gameManager);

                GameStage.getInstance().getStage().getScene().getStylesheets().add(getClass().getResource("game.css").toExternalForm());
                stackPane.getStyleClass().addAll("game-root");
                GameStage.getInstance().getStage().setResizable(false);

                GameStage.getInstance().getStage().getScene().setOnKeyPressed(ke -> {
                    KeyCode keyCode = ke.getCode();
                    if (keyCode.isArrowKey()) {
                        try {
                            Direction dir = Direction.valueFor(keyCode);
                            gameManager.move(dir);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                });

                undo.visibleProperty().bind(gameManager.getHaveAvailableMoves());

                gameStarted = true;
                if (gameStarted) {
                    start.setText("Reset");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void undoMove(ActionEvent event) {
        gameManager.undoMove();
    }

    @FXML
    private void exit(ActionEvent event) {
        if (gameStarted) {
            try {
                Controller.getInstance().endGame();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

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
        undo.setVisible(false);
        GameStage.getInstance().getStage().setOnCloseRequest((event) -> {
            if (gameStarted) {
                try {
                    Controller.getInstance().endGame();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }

}
