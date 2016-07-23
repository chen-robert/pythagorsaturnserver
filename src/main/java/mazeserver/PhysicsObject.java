/**
 * PhysicsObject.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;
import java.util.*;


public class PhysicsObject {

    @SerializedName("Position") private Vector2 _position;
    @SerializedName("Velocity") private Vector2 _velocity;
    @SerializedName("Acceleration") private Vector2 _acceleration;
    private transient float _elasticity;  // 0-1, 0=Absorbs all impacts, 1=No energy absorbed during collision
    private transient float _gravity;     // 0-1, 0=None, 1=Full

    /*
    public PhysicsObject(Vector2 position, float elasticity, float gravity)
    {
        _position = position;
        _velocity = Vector2.ZERO;
        _acceleration = Vector2.ZERO;
        _elasticity = elasticity;
        _gravity = gravity;
    }

    public PhysicsObject(Vector2 position, Vector2 velocity, float elasticity, float gravity)
    {
        _position = position;
        _velocity = velocity;
        _acceleration = Vector2.ZERO;
        _elasticity = elasticity;
        _gravity = gravity;
    }
    */
    public PhysicsObject(Vector2 position, Vector2 velocity, Vector2 acceleration, float elasticity, float gravity)
    {
        _position = position;
        _velocity = velocity;
        _acceleration = acceleration;
        _elasticity = elasticity;
        _gravity = gravity;
    }


    public void update(List<Object> objectList)
    {
        handleAcceleration();
        handleGravity();
        handleVelocity();
        handleCollisions(objectList);
    }

    public void handleCollisions(List<Object> objectList)
    {
        System.out.println("Override PhysicsObject.handleCollisions() to use a type specific collision handler.");
    }

    public static void handleCollisionCircleCircle(Circle circle1, Circle circle2)
    {

    }

    public static void handleCollisionCircleSquare(Circle circle, Square square)
    {

    }

    private void handleAcceleration()
    {
        _velocity.add(_acceleration);
    }

    private void handleGravity()
    {
        if (_gravity > 0)
        {
            _velocity.changeY(-1 * _gravity);
        }
    }

    private void handleVelocity()
    {
        _position.add(_velocity);
    }

    public Vector2 getPosition()
    {
        return _position;
    }

    public Vector2 setPosition(Vector2 position)
    {
        _position = position;
        return _position;
    }

    public Vector2 changePosition(Vector2 position)
    {
        _position.add(position);
        return _position;
    }

    public Vector2 getVelocity()
    {
        return _velocity;
    }

    public Vector2 setVelocity(Vector2 velocity)
    {
        _velocity = velocity;
        return _velocity;
    }

    public Vector2 changeVelocity(Vector2 velocity)
    {
        _velocity.add(velocity);
        return _velocity;
    }

    public Vector2 getAcceleration()
    {
        return _acceleration;
    }

    public Vector2 setAcceleration(Vector2 acceleration)
    {
        _acceleration = acceleration;
        return _acceleration;
    }

    public Vector2 changeAcceleration(Vector2 acceleration)
    {
        _acceleration.add(acceleration);
        return _acceleration;
    }

    public float getElasticity()
    {
        return _elasticity;
    }

    public float setElasticity(float elasticity)
    {
        _elasticity = Math.max(0, Math.min(1, elasticity));;
        return _elasticity;
    }

    public float getGravity()
    {
        return _gravity;
    }

    public float setGravity(float gravity)
    {
        _gravity = Math.max(0, Math.min(1, gravity));;
        return _gravity;
    }
}
