package GameLogic.Physics;

import Engine.Vector;

public class Formulas
{
    public static Vector calculateDisplacement(Vector velocity, Vector acceleration, float deltaTime)
    {
        Vector displacement = new Vector(0, 0);

        displacement.setX((velocity.getX() * deltaTime) + (float)(((acceleration.getX() * Math.pow(deltaTime, 2)) / 2f)));
        displacement.setY((velocity.getY() * deltaTime) + (float)(((acceleration.getY() * Math.pow(deltaTime, 2)) / 2f)));

        return displacement;
    }
}
