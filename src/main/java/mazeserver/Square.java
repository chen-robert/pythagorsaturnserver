package mazeserver;

/**
 * Created by FinnBear on 7/23/16.
 */
public class Square extends PhysicsObject {
    float _width;
    float _height;

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
