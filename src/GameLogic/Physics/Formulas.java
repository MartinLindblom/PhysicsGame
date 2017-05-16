package GameLogic.Physics;

import Engine.GameState;
import Engine.Vector;
import GameLogic.Game;

public class Formulas
{
    public static Vector calculateDisplacement(Vector velocity, Vector acceleration, float deltaTime)
    {
        Vector displacement = new Vector(0, 0);

        displacement.setX((velocity.getX() * deltaTime) + (float)(((acceleration.getX() * Math.pow(deltaTime, 2)) / 2f)));
        displacement.setY((velocity.getY() * deltaTime) + (float)(((acceleration.getY() * Math.pow(deltaTime, 2)) / 2f)));

        return displacement;
    }

    public static Vector cartesianToGraphical(Vector v, GameState gameState)
    {
        return new Vector(v.getX() * Game.PIXELS_PER_METER, gameState.getGameWindow().getHeight() - (v.getY() * Game.PIXELS_PER_METER));
    }
}
