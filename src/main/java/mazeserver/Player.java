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
    private static final float _ELASTICITY = 0.3f;
    private static final float _INERTIALDAMPER = 0.8f;
    private static final float _GRAVITY = 0.5f;
    private static final float _HEIGHT = 0.5f;
    private static final float _WIDTH = 0.3f;

    private transient Game _game;
    @SerializedName("ID") private long _id;
    @SerializedName("user") private User _user;
    @SerializedName("body") private Rectangle _square;


    public Player(long id, User user, Game game, int x, int y) {
        _game = game;
        _id = id;
        _square = new Rectangle(new Vector2(x, y), _WIDTH, _HEIGHT, _ELASTICITY, _INERTIALDAMPER, _GRAVITY);
        _user = user;
    }

}
