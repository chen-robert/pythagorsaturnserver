/**
 * Game.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;


public class Game {

    @SerializedName("Maze") // For serializing Game.
    private Maze _maze;

    private Map<Long, Player> _playerHash;
    private int _spawnX;
    private int _spawnY;


    public Game(long mazeId, int height, int width) {
        _maze = new Maze(mazeId, height, width);
        _spawnX = _maze.randomInt(width);
        _spawnY = _maze.randomInt(height);
        _playerHash = new HashMap();
    }

    /**
     * This method creates a new player with the specified ID.
     */
    public void createPlayer(long playerId, User user) {
        Player player = _playerHash.get(playerId);
        if (player != null) {
            throw new IllegalArgumentException("player already exists: " + playerId);
        }

        player = new Player(playerId, user, this, _spawnX, _spawnY);
        _playerHash.put(playerId, player);
    }

    public Maze getMaze() {
        return _maze;
    }

}
