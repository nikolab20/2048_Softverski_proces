/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.gameManager;

import domain.Direction;
import domain.GridOperator;
import domain.Location;
import gui.board.Board;
import gui.tile.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;

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
        initializeGameGrid();
        startGame();
    }

    private void initializeGameGrid() {
        gameGrid.clear();
        locations.clear();
        GridOperator.traverseGrid((i, j) -> {
            Location location = new Location(i, j);
            locations.add(location);
            gameGrid.put(location, null);
            return 0;
        });
    }

    private void startGame() {
        Tile tile0 = Tile.newRandomTile();
        List<Location> locCopy = locations.stream().collect(Collectors.toList());
        Collections.shuffle(locCopy);
        tile0.setLocation(locCopy.get(0));
        gameGrid.put(tile0.getLocation(), tile0);
        Tile tile1 = Tile.newRandomTile();
        tile1.setLocation(locCopy.get(1));
        gameGrid.put(tile1.getLocation(), tile1);

        redrawTilesInGameGrid();
    }

    private void redrawTilesInGameGrid() {
        gameGrid.values().stream().filter(Objects::nonNull).forEach(board::addTile);
    }

    public void move(Direction direction) {
        synchronized (gameGrid) {
            if (movingTiles) {
                return;
            }
        }

        GridOperator.sortGrid(direction);
        board.setPoints(0);

        tilesWereMoved = GridOperator.traverseGrid((i, j) -> {
            Tile t = gameGrid.get(new Location(i, j));
            if (t != null) {
                final Location newLoc = findFarthestLocation(t.getLocation(), direction);

                Location nextLocation = newLoc.offset(direction);
                Tile tileToBeMerged = nextLocation.isValidFor() ? gameGrid.get(nextLocation) : null;
                if (tileToBeMerged != null && !tileToBeMerged.isMerged() && t.isMergeable(tileToBeMerged)) {
                    tileToBeMerged.merge(t);
                    tileToBeMerged.toFront();
                    gameGrid.put(nextLocation, tileToBeMerged);
                    gameGrid.replace(t.getLocation(), null);
                    parallelTransition.getChildren().add(animateExistingTile(t, nextLocation));
                    parallelTransition.getChildren().add(animateMergedTile(tileToBeMerged));
                    mergedToBeRemoved.add(t);
                    board.addPoints(tileToBeMerged.getValue());
                    return 1;
                }

                if (!newLoc.equals(t.getLocation())) {
                    parallelTransition.getChildren().add(animateExistingTile(t, newLoc));
                    gameGrid.put(newLoc, t);
                    gameGrid.replace(t.getLocation(), null);
                    t.setLocation(newLoc);
                    return 1;
                }
            }
            return 0;
        });

        board.animateScore();

        parallelTransition.setOnFinished(e -> {
            synchronized (gameGrid) {
                movingTiles = false;
            }

            board.getGridGroup().getChildren().removeAll(mergedToBeRemoved);
            mergedToBeRemoved.clear();
            gameGrid.values().stream().filter(Objects::nonNull).forEach(t -> t.setMerged(false));

            Location randomAvailableLocation = findRandomAvailableLocation();
            if (randomAvailableLocation != null) {
                if (tilesWereMoved > 0) {
                    addAndAnimateRandomTile(randomAvailableLocation);
                }
            } else {
                if (mergeMovementsAvailable() == 0) {
                    System.out.println("Game Over");
                }
            }
        });

        synchronized (gameGrid) {
            movingTiles = true;
        }
        parallelTransition.play();
        parallelTransition.getChildren().clear();
    }

    private Location findFarthestLocation(Location location, Direction direction) {
        Location farthest = location;

        do {
            farthest = location;
            location = farthest.offset(direction);
        } while (location.isValidFor() && gameGrid.get(location) == null);

        return farthest;
    }

    private Timeline animateExistingTile(Tile tile, Location newLocation) {
        Timeline timeline = new Timeline();

        KeyValue kvX = new KeyValue(tile.layoutXProperty(),
                newLocation.getLayoutX(Board.CELL_SIZE) - (tile.getMinHeight() / 2),
                Interpolator.EASE_OUT);
        KeyValue kvY = new KeyValue(tile.layoutYProperty(),
                newLocation.getLayoutY(Board.CELL_SIZE) - (tile.getMinHeight() / 2),
                Interpolator.EASE_OUT);
        KeyFrame kfX = new KeyFrame(Duration.millis(65), kvX);
        KeyFrame kfY = new KeyFrame(Duration.millis(65), kvY);
        timeline.getKeyFrames().add(kfX);
        timeline.getKeyFrames().add(kfY);

        return timeline;
    }

    private Location findRandomAvailableLocation() {
        Location location = null;

        List<Location> availableLocations = locations.stream().filter(l -> gameGrid.get(l) == null)
                .collect(Collectors.toList());

        if (availableLocations.isEmpty()) {
            return null;
        }

        Collections.shuffle(availableLocations);
        location = availableLocations.get(0);

        return location;
    }

    private void addAndAnimateRandomTile(Location randomLocation) {
        Tile tile = Tile.newRandomTile();
        tile.setLocation(randomLocation);
        tile.setScaleX(0);
        tile.setScaleY(0);
        board.addTile(tile);
        gameGrid.put(tile.getLocation(), tile);

        final ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(125), tile);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);

        scaleTransition.play();
    }

    private SequentialTransition animateMergedTile(Tile tile) {
        final ScaleTransition scale0 = new ScaleTransition(Duration.millis(80), tile);
        scale0.setToX(1.2);
        scale0.setToY(1.2);
        scale0.setInterpolator(Interpolator.EASE_IN);
        final ScaleTransition scale1 = new ScaleTransition(Duration.millis(80), tile);
        scale1.setToX(1.0);
        scale1.setToY(1.0);
        scale1.setInterpolator(Interpolator.EASE_OUT);
        return new SequentialTransition(scale0, scale1);
    }

    private int mergeMovementsAvailable() {
        final AtomicInteger numMergeableTile = new AtomicInteger();

        Stream.of(Direction.UP, Direction.LEFT).parallel().forEach(direction -> {
            GridOperator.traverseGrid((x, y) -> {
                Location thisloc = new Location(x, y);
                Tile t1 = gameGrid.get(thisloc);
                if (t1 != null) {
                    Location nextLoc = thisloc.offset(direction);
                    if (nextLoc.isValidFor()) {
                        Tile t2 = gameGrid.get(nextLoc);
                        if (t2 != null && t1.isMergeable(t2)) {
                            numMergeableTile.incrementAndGet();
                        }
                    }
                }
                return 0;
            });
        });

        return numMergeableTile.get();
    }

    private Optional<Tile> optionalTile(Location loc) {
        return null;
    }
}
