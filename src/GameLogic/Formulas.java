package GameLogic;

import Engine.Vector;

public class Formulas
{
    /**
     * The formula used to calculate the distance the player should move each frame with respect to the players
     * velocity and acceleration.
     * S = vt + (at^2)/2
     * @param velocity The player's velocity
     * @param acceleration The player's acceleration
     * @param deltaTime Time that has passed since last update.
     * @return The distance that the player should move that frame as a vector.
     */
    public static Vector calculateDisplacement(Vector velocity, Vector acceleration, float deltaTime)
    {
        Vector displacement = new Vector(0, 0);

        displacement.setX((velocity.getX() * deltaTime) + (float)(((acceleration.getX() * Math.pow(deltaTime, 2)) / 2f)));
        displacement.setY((velocity.getY() * deltaTime) + (float)(((acceleration.getY() * Math.pow(deltaTime, 2)) / 2f)));

        return displacement;
    }
}
