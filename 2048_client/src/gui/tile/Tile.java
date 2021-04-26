/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tile;

import domain.Location;
import gui.board.Board;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author nikolab
 */
public class Tile extends Label {

    private int value;
    private Location location;
    private boolean merged;

    public static Tile newRandomTile() {
        return newTile(new Random().nextDouble() < 0.9 ? 2 : 4);
    }

    public static Tile newTile(int value) {
        return new Tile(value);
    }

    private Tile(int value) {
        final int squareSize = Board.CELL_SIZE - 13;
        setMinSize(squareSize, squareSize);
        setMaxSize(squareSize, squareSize);
        setPrefSize(squareSize, squareSize);
        getStyleClass().addAll("game-label", "game-tile-" + value);
        setAlignment(Pos.CENTER);

        this.value = value;
        this.merged = false;
        setText(Integer.toString(value));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    @Override
    public String toString() {
        return "Tile{" + "value=" + value + ", location=" + location + ", merged=" + merged + '}';
    }

    public void merge(Tile another) {
    }

    public boolean isMergeable(Tile anotherTile) {
        return false;
    }
}
