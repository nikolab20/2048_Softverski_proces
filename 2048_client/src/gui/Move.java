/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Location;
import gui.tile.Tile;
import java.util.Map;

/**
 *
 * @author nikolab
 */
public class Move {

    private Map<Location, Tile> gameGrid;
    private int score;

    public Move() {
    }

    public Move(Map<Location, Tile> gameGrid, int score) {
        this.gameGrid = gameGrid;
        this.score = score;
    }

    public Map<Location, Tile> getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(Map<Location, Tile> gameGrid) {
        this.gameGrid = gameGrid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
