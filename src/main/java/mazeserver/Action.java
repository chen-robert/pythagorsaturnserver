/**
 * Action.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;


/**
 * A keyboard or mouse action.
 */
public class Action {

    @SerializedName("keys") private String[] _keys;

    public Action() {
    }

    public String[] getKeys()
    {
        return _keys;
    }
}
