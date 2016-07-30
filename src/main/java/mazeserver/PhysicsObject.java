/**
 * PhysicsObject.java
 *
 * Copyright 2016 Finn Bear.  All rights reserved.
 */
package mazeserver;

import com.google.gson.annotations.SerializedName;
import java.util.*;


public class PhysicsObject {

    private transient String _id;
    @SerializedName("position") private Vector2 _position;
    @SerializedName("velocity") private Vector2 _velocity;
    @SerializedName("acceleration") private Vector2 _acceleration;
    private transient float _elasticity;  // 0-1, 0=Absorbs all impacts, 1=No energy absorbed during collision
    private transient float _gravity;     // 0-1, 0=None, 1=Full
    private transient float _inertialDamper; // 0-1, 0=100% damping, 1=0% damping

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
    public PhysicsObject(String id, Vector2 position, Vector2 velocity, Vector2 acceleration, float elasticity, float inertialDamper, float gravity)
    {
        _id = id;
        _position = position;
        _velocity = velocity;
        _acceleration = acceleration;
        _elasticity = elasticity;
        _inertialDamper = inertialDamper;
        _gravity = gravity;
    }

    public void update(List<Circle> objectList)
    {
        handleCollisions(objectList);
        handleAcceleration();
        handleGravity();
        handleVelocity();
    }

    public void handleCollisions(List<Circle> objectList)
    {
        System.out.println("Override PhysicsObject.handleCollisions() to use a type specific collision handler.");
    }

    public static void handleCollisionCircleCircle(Circle circle1, Circle circle2)
    {
        if (circle1.getPosition().distanceTo2(circle2.getPosition()) < (circle1.getRadius() + circle2.getRadius()) * (circle1.getRadius() + circle2.getRadius()))
        {
            float angle = circle1.getPosition().angleToRad(circle2.getPosition());
            circle1.changeAcceleration(-1 * (float)Math.cos(angle), -1 * (float)Math.sin(angle));
            circle2.changeAcceleration((float)Math.cos(angle), (float)Math.sin(angle));
        }
    }

    public static void handleCollisionSquareSquare(Square square1, Square square2)
    {

    }

    public static void handleCollisionRectangleRectangle(Rectangle rectangle1, Rectangle rectangle2)
    {

    }

    public static void handleCollisionCircleSquare(Circle circle, Square square)
    {

    }

    public static void handleCollisionCircleRectangle(Circle circle, Rectangle rectangle)
    {

    }

    public static void handleCollisionSquareRectangle(Square square, Rectangle rectangle)
    {

    }

    private void handleAcceleration()
    {
        _velocity.add(_acceleration);
        _acceleration.multiplyByConstant(_inertialDamper);
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
        _velocity.multiplyByConstant(_inertialDamper);
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

    public Vector2 setPosition(float x, float y)
    {
        _position = new Vector2(x, y);
        return _position;
    }

    public Vector2 changePosition(Vector2 position)
    {
        _position.add(position);
        return _position;
    }

    public Vector2 changePosition(float x, float y)
    {
        _position.add(x, y);
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

    public Vector2 setVelocity(float x, float y)
    {
        _velocity = new Vector2(x, y);
        return _velocity;
    }

    public Vector2 changeVelocity(Vector2 velocity)
    {
        _velocity.add(velocity);
        return _velocity;
    }

    public Vector2 changeVelocity(float x, float y)
    {
        _velocity.add(x, y);
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

    public Vector2 setAcceleration(float x, float y)
    {
        _acceleration = new Vector2(x, y);
        return _acceleration;
    }

    public Vector2 changeAcceleration(Vector2 acceleration)
    {
        _acceleration.add(acceleration);
        return _acceleration;
    }

    public Vector2 changeAcceleration(float x, float y)
    {
        _acceleration.add(x, y);
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

    public float getInertialDamper()
    {
        return _inertialDamper;
    }

    public float setInertialDamper(float inertialDamper)
    {
        _inertialDamper = Math.max(0, Math.min(1, inertialDamper));
        return _inertialDamper;
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

    public String getId() {
        return _id;
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("id=");
        buffer.append(_id);
        buffer.append(" positionX=");
        buffer.append(_position.getX());
        buffer.append(" positionY=");
        buffer.append(_position.getY());
        buffer.append(" velocityX=");
        buffer.append(_velocity.getX());
        buffer.append(" velocityY=");
        buffer.append(_velocity.getY());
        buffer.append(" accelerationX=");
        buffer.append(_acceleration.getX());
        buffer.append(" accelerationY=");
        buffer.append(_acceleration.getY());
        return buffer.toString();
    }
}
