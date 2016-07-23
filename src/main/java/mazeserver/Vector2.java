package mazeserver;

/**
 * Created by finnb on 7/23/16.
 */
public class Vector2 {
    private float _x;
    private float _y;

    public final static Vector2 ZERO = new Vector2(0, 0);
    public final static Vector2 ONE = new Vector2(1, 1);

    public Vector2(float x, float y)
    {
        _x = x;
        _y = y;
    }

    public float getX()
    {
        return _x;
    }

    public float getY()
    {
        return _y;
    }

    public float setX(float x)
    {
        _x = x;
        return _x;
    }

    public float setY(float y)
    {
        _y = y;
        return _y;
    }

    public float changeX(float x)
    {
        _x += x;
        return _x;
    }

    public float changeY(float y)
    {
        _y +=  y;
        return _y;
    }

    public Vector2 add(Vector2 vector)
    {
        _x += vector.getX();
        _y += vector.getY();
        return this;
    }

    public Vector2 subtract(Vector2 vector)
    {
        _x -= vector.getX();
        _y -= vector.getY();
        return this;
    }

    public float distanceTo(Vector2 vector)
    {
        return (float)Math.sqrt((_x - vector.getX()) * (_x - vector.getX()) + (_y - vector.getY()) * (_y - vector.getY()));
    }

    public float distance(Vector2 vector1, Vector2 vector2)
    {
        return (float)Math.sqrt((vector1.getX() - vector2.getX()) * (vector1.getX() - vector2.getX()) + (vector1.getY() - vector2.getY()) * (vector1.getY() - vector2.getY()));
    }

    public float angleToRad(Vector2 vector)
    {
        float angle = (float)Math.atan2(vector.getY() - _y, vector.getX() - _x);
        if (angle < 0) // Disallow negative representations of angles
        {
            angle += Math.PI; // 180 degree equivalent
        }
        return angle;
    }

    public float angleToDeg(Vector2 vector)
    {
        float angle = (float)Math.toDegrees(Math.atan2(vector.getY() - _y, vector.getX() - _x));
        if (angle < 0) // Disallow negative representations of angles
        {
            angle += 180;
        }
        return angle;
    }
}
