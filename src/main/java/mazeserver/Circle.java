/**
 * Circle.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;
import java.util.*;


public class Circle extends PhysicsObject {

    @SerializedName("radius") private float _radius;

    public Circle(Vector2 position, float radius, float elasticity, float inertialDamper, float gravity)
    {
        super(position, Vector2.ZERO, Vector2.ZERO, elasticity, inertialDamper, gravity);
        _radius = radius;
    }

    public Circle(Vector2 position, Vector2 velocity, float radius, float elasticity, float inertialDamper, float gravity)
    {
        super(position, velocity, Vector2.ZERO, elasticity, inertialDamper, gravity);
        _radius = radius;
    }

    public Circle(Vector2 position, Vector2 velocity, Vector2 acceleration, float radius, float elasticity, float inertialDamper, float gravity)
    {
        super(position, velocity, acceleration, elasticity, inertialDamper, gravity);
        _radius = radius;
    }

    @Override
    public void handleCollisions(List<Circle> objectList)
    {
        for (int i = 0; i < objectList.size(); i += 1)
        {
            Object object = objectList.get(i);
            if (object.getClass().equals(Circle.class))
            {
                PhysicsObject.handleCollisionCircleCircle(this, (Circle)object);
            }
            if (object.getClass().equals(Square.class))
            {
                PhysicsObject.handleCollisionCircleSquare(this, (Square)object);
            }
        }
    }

    public float getRadius()
    {
        return _radius;
    }

    public float setRadius(float radius)
    {
        _radius = Math.max(0, radius);
        return _radius;
    }
}
