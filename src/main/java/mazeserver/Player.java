/**
 * Player.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;


/**
 * A player in a particular game.
 */
public class Player {

    private transient Game _game;
    @SerializedName("ID") private long _id;
    @SerializedName("User") private User _user;
    @SerializedName("X") private int _x;
    @SerializedName("Y") private int _y;


    public Player(long id, User user, Game game, int x, int y) {
        _game = game;
        _id = id;
        _user = user;
        _x = x;
        _y = y;
    }

}
