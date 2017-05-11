package GameLogic.Physics;

import Engine.GameState;
import Engine.Vector;
import GameLogic.Game;

public class Formulas
{
    // Simple drag calculation
    public static Vector calculateDrag(Vector velocity, float dragCoefficient)
    {
        Vector drag = new Vector(0, 0);

        drag.setX(-velocity.getX() * dragCoefficient);
        drag.setY(-velocity.getY() * dragCoefficient);

        return drag;
    }

    public static Vector calculateFriction(Vector velocity, float mass, float frictionCoefficient)
    {
        Vector friction = new Vector(0, 0);

        float direction = 0;

        if (velocity.getX() != 0)
        {
            direction = -velocity.getX() / velocity.getX();
        }

        friction.setX(direction * mass * Game.GRAVITY * frictionCoefficient);

        return friction;
    }

    public static Vector calculateDisplacement(Vector velocity, Vector acceleration, float deltaTime)
    {
        Vector displacement = new Vector(0, 0);

        displacement.setX((velocity.getX() * deltaTime) + (float)(((acceleration.getX() * Math.pow(deltaTime, 2)) / 2f)));
        displacement.setY((velocity.getY() * deltaTime) + (float)(((acceleration.getY() * Math.pow(deltaTime, 2)) / 2f)));

        return displacement;
    }

    public static Vector cartesianToGraphical(Vector v, GameState gameState)
    {
        return new Vector(v.getX(), gameState.getGameWindow().getHeight() - v.getY());
    }
}
