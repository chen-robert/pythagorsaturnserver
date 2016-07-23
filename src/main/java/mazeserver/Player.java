/**
 * Player.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;


/**
 * A player in a particular game.
 */
public class Player {

    private Game _game;
    private long _id;
    private User _user;
    private int _x;
    private int _y;


    public Player(long id, User user, Game game, int x, int y) {
        _game = game;
        _id = id;
        _user = user;
        _x = x;
        _y = y;
    }

}
