/**
 * Square.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;


public class Square extends PhysicsObject {

    @SerializedName("side") float _side;

    public Square(String id, Vector2 position, float side, float elasticity, float inertialDamper, float gravity)
    {
        super(id, position, Vector2.ZERO(), Vector2.ZERO(), elasticity, inertialDamper, gravity);
        _side = side;
    }

    public Square(String id, Vector2 position, Vector2 velocity, float side, float elasticity, float inertialDamper, float gravity)
    {
        super(id, position, velocity, Vector2.ZERO(), elasticity, inertialDamper, gravity);
        _side = side;
    }

    public Square(String id, Vector2 position, Vector2 velocity, Vector2 acceleration, float side, float elasticity, float inertialDamper, float gravity)
    {
        super(id, position, velocity, acceleration, elasticity, inertialDamper, gravity);
        _side = side;
    }

    public float getSide()
    {
        return _side;
    }

    public float setSide(float side)
    {
        _side = Math.max(0, side);
        return _side;
    }
}
