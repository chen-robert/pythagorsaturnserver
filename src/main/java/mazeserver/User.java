/**
 * User.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;


/**
 * A user who, when playing, will control a player.
 */
public class User {

    private long _id;
    private int _xp;


    public User(long id) {
        _id = id;
    }

    public void addXP(int amount) {
        _xp += amount;
    }

}
