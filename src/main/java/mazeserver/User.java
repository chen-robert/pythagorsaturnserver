/**
 * User.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;


/**
 * A user who, when playing, will control a player.
 */
public class User {

    @SerializedName("ID") private long _id;
    @SerializedName("XP") private int _xp;


    public User(long id) {
        _id = id;
    }

    public void addXP(int amount) {
        _xp += amount;
    }

}
