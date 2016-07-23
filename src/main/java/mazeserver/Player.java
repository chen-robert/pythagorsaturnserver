/**
 * Player.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;


public class Player {

    private Game _game;
    private long _id;
    private int _x;
    private int _y;


    public Player(long id, Game game, int x, int y) {
        _game = game;
        _id = id;
        _x = x;
        _y = y;
    }

}
