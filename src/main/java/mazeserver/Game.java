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

    @SerializedName("ID") private long _id;
    private transient Maze _maze;
    @SerializedName("players") private Map<Long, Player> _playerHash;
    private transient int _spawnX;
    private transient int _spawnY;


    public Game(long mazeId, int height, int width) {
        _maze = new Maze(mazeId, height, width);

        _id = _maze.getId();
        _spawnX = _maze.randomInt(width);
        _spawnY = _maze.randomInt(height);
        _playerHash = new HashMap();
    }

    /**
     * Change the game state based on the user's action.
     */
    public void applyAction(long playerId, Action action) {
        Player player = _playerHash.get(new Long(playerId));
        switch (action.getKey())
        {
            case "U":
                player.getRectangle().changeAcceleration(0, 1);
                break;
            case "D":
                player.getRectangle().changeAcceleration(0, -1);
                break;
            case "R":
                player.getRectangle().changeAcceleration(1, 0);
                break;
            case "L":
                player.getRectangle().changeAcceleration(-1, 0);
                break;
            default:
                System.out.println("Could not process action, key='" + action.getKey() + "'");
        }
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
