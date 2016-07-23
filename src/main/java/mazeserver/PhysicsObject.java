package mazeserver;

/**
 * Created by finnb on 7/23/16.
 */
public class PhysicsObject {
    private Vector2 _position;
    private Vector2 _velocity;
    private Vector2 _acceleration;
    private float _elasticity; // 0-1, 0=Absorbs all impacts, 1=No energy absorbed during collision
    private float _gravity; // 0-1, 0=None, 1=Full

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

    public PhysicsObject(Vector2 position, Vector2 velocity, Vector2 acceleration, float elasticity, float gravity)
    {
        _position = position;
        _velocity = velocity;
        _acceleration = acceleration;
        _elasticity = elasticity;
        _gravity = gravity;
    }

    public void update()
    {
        handleAcceleration();
        handleGravity();
        handleVelocity();
        handleCollisions();
    }

    public void handleCollisions()
    {
        System.out.println("Override PhysicsObject.handleCollisions() to use a type specific collision handler.");
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
