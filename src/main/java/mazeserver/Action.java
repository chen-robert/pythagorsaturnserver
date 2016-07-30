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

    @SerializedName("key") private String _key;

    public Action() {
    }

    public String getKey()
    {
        return _key;
    }
}
