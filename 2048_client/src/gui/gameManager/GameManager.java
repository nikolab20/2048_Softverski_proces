/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.gameManager;

import domain.Direction;
import domain.Location;
import gui.board.Board;
import gui.tile.Tile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;

/**
 *
 * @author nikolab
 */
public class GameManager extends Group {

    private Board board;
    private final List<Location> locations = new ArrayList<>();
    private final Map<Location, Tile> gameGrid = new HashMap<>();
    private final ParallelTransition parallelTransition = new ParallelTransition();
    private volatile boolean movingTiles = false;
    private int tilesWereMoved = 0;
    private final Set<Tile> mergedToBeRemoved = new HashSet<>();

    public GameManager() {
        board = new Board();
        getChildren().add(board);
        startGame();
    }

    private void initializeGameGrid() {
    }

    private void startGame() {
        Tile tile0 = Tile.newRandomTile();
        tile0.setLocation(new Location(1, 2));
        board.addTile(tile0);
    }

    private void redrawTilesInGameGrid() {
    }

    public void move(Direction direction) {
    }

    private Location findFarthestLocation(Location location, Direction direction) {
        Location farthest = location;
        return farthest;
    }

    private Timeline animateExistingTile(Tile tile, Location newLocation) {
        Timeline timeline = new Timeline();
        return timeline;
    }

    private Location findRandomAvailableLocation() {
        Location location = null;
        return location;
    }

    private void addAndAnimateRandomTile(Location randomLocation) {
    }

    private SequentialTransition animateMergedTile(Tile tile) {
        return new SequentialTransition();
    }

    private int mergeMovementsAvailable() {
        final AtomicInteger numMergeableTile = new AtomicInteger();
        return numMergeableTile.get();
    }

    private Optional<Tile> optionalTile(Location loc) {
        return null;
    }
}
