/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.board;

import domain.Location;
import gui.tile.Tile;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author nikolab
 */
public class Board extends Group {

    public static final int CELL_SIZE = 128;
    private static final int BORDER_WIDTH = (14 + 2) / 2;
    public static final int GRID_WIDTH = 4 * CELL_SIZE + 2 * BORDER_WIDTH;
    private static final int TOP_HEIGHT = 92;
    private static final int GAP_HEIGHT = 50;

    private final VBox vGame = new VBox(0);
    private final HBox hMid = new HBox();
    private final HBox hBottom = new HBox();

    private final HBox hTop = new HBox(0);
    private final VBox vScore = new VBox(0);
    private final Label lblScore = new Label("0");
    private final Label lblBest = new Label("0");
    private final Label lblPoints = new Label();

    private final Group gridGroup = new Group();
    private final IntegerProperty gameMovePoints = new SimpleIntegerProperty(0);
    private final IntegerProperty gameScoreProperty = new SimpleIntegerProperty(0);
    private final Timeline animateAddedPoints = new Timeline();

    private final HBox overlay = new HBox();
    private final Label lOvrText = new Label();
    private final HBox buttonsOverlay = new HBox();
    private final Button bTry = new Button("Try again");
    private final Button bContinue = new Button("Keep going");

    private final BooleanProperty gameWonProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty gameOverProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty resetGame = new SimpleBooleanProperty(false);

    public Board() {
        createScore();
        createGrid();
    }

    private void createScore() {
        Label lblTitle = new Label("2048");
        Label lblSubtitle = new Label("FX");

        HBox hFill = new HBox();
        HBox.setHgrow(hFill, Priority.ALWAYS);

        VBox vScores = new VBox();
        HBox hScores = new HBox(5);

        Label lblTit = new Label("SCORE");
        vScore.getChildren().addAll(lblTit, lblScore);

        VBox vRecord = new VBox(0);
        Label lblTitBest = new Label("BEST");
        vRecord.getChildren().addAll(lblTitBest, lblBest);

        hScores.getChildren().addAll(vScore, vRecord);
        VBox vFill = new VBox();
        VBox.setVgrow(vFill, Priority.ALWAYS);
        vScores.getChildren().addAll(hScores, vFill);

        hTop.getChildren().addAll(lblTitle, lblSubtitle, hFill, vScores);

        hTop.setMinSize(GRID_WIDTH, TOP_HEIGHT);
        hTop.setPrefSize(GRID_WIDTH, TOP_HEIGHT);
        hTop.setMaxSize(GRID_WIDTH, TOP_HEIGHT);

        vGame.getChildren().add(hTop);

        hMid.setMinSize(GRID_WIDTH, GAP_HEIGHT);

        vGame.getChildren().add(hMid);

        lblTitle.getStyleClass().addAll("game-label", "game-title");
        lblSubtitle.getStyleClass().addAll("game-label", "game-subtitle");
        vScore.getStyleClass().add("game-vbox");
        lblTit.getStyleClass().addAll("game-label", "game-titScore");
        lblScore.getStyleClass().addAll("game-label", "game-score");
        vRecord.getStyleClass().add("game-vbox");
        lblTitBest.getStyleClass().addAll("game-label", "game-titScore");
        lblBest.getStyleClass().addAll("game-label", "game-score");
    }

    private Rectangle createCell(int i, int j) {
        Rectangle cell = null;

        cell = new Rectangle(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        cell.setFill(Color.WHITE);
        cell.setStroke(Color.GREY);

        cell.setArcHeight(CELL_SIZE / 6d);
        cell.setArcWidth(CELL_SIZE / 6d);
        cell.getStyleClass().add("game-grid-cell");

        return cell;
    }

    private void createGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gridGroup.getChildren().add(createCell(i, j));
            }
        }

        gridGroup.getStyleClass().add("game-grid");
        hBottom.getStyleClass().add("game-backGrid");

        gridGroup.setManaged(false);
        gridGroup.setLayoutX(BORDER_WIDTH);
        gridGroup.setLayoutY(BORDER_WIDTH);

        hBottom.setMinSize(GRID_WIDTH, GRID_WIDTH);
        hBottom.setPrefSize(GRID_WIDTH, GRID_WIDTH);
        hBottom.setMaxSize(GRID_WIDTH, GRID_WIDTH);

        hBottom.getChildren().add(gridGroup);

        vGame.getChildren().add(hBottom);
        getChildren().add(0, vGame);
    }

    public void addTile(Tile tile) {
        moveTile(tile, tile.getLocation());
        gridGroup.getChildren().add(tile);
    }

    public void moveTile(Tile tile, Location location) {
        double layoutX = tile.getLocation().getLayoutX(CELL_SIZE) - (tile.getMinWidth()
                / 2);
        double layoutY = tile.getLocation().getLayoutY(CELL_SIZE) - (tile.getMinHeight()
                / 2);
        tile.setLayoutX(layoutX);
        tile.setLayoutY(layoutY);
    }

    public Group getGridGroup() {
        return gridGroup;
    }

    public void setPoints(int points) {
        gameMovePoints.set(points);
    }

    public int getPoints() {
        return gameMovePoints.get();
    }

    public void addPoints(int points) {
    }

    public void animateScore() {
    }

    private void initGameProperties() {
        overlay.setMinSize(GRID_WIDTH, GRID_WIDTH);
        overlay.setAlignment(Pos.CENTER);
        overlay.setTranslateY(TOP_HEIGHT + GAP_HEIGHT);

        overlay.getChildren().setAll(lOvrText);

        buttonsOverlay.setAlignment(Pos.CENTER);
        buttonsOverlay.setTranslateY(TOP_HEIGHT + GAP_HEIGHT + GRID_WIDTH / 2);
        buttonsOverlay.setMinSize(GRID_WIDTH, GRID_WIDTH / 2);
        buttonsOverlay.setSpacing(10);
    }

    public void setGameOver(boolean gameOver) {
        gameOverProperty.set(gameOver);
    }

    public void setGameWin(boolean won) {
        gameWonProperty.set(won);
    }

    public BooleanProperty resetGameProperty() {
        return resetGame;
    }
}
