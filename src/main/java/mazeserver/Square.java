/**
 * Square.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;


public class Square extends PhysicsObject {

    @SerializedName("Width") float _width;
    @SerializedName("Height") float _height;


    public Square(Vector2 position, float width, float height, float elasticity, float gravity)
    {
        super(position, Vector2.ZERO, Vector2.ZERO, elasticity, gravity);
        _width = width;
        _height = height;
    }

    public Square(Vector2 position, Vector2 velocity, float width, float height, float elasticity, float gravity)
    {
        super(position, velocity, Vector2.ZERO, elasticity, gravity);
        _width = width;
        _height = height;
    }

    public Square(Vector2 position, Vector2 velocity, Vector2 acceleration, float width, float height, float elasticity, float gravity)
    {
        super(position, velocity, acceleration, elasticity, gravity);
        _width = width;
        _height = height;
    }

    //To Be Completed
}
