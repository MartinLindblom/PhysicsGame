package GameLogic;

import Engine.GameState;
import Engine.Vector;

import java.awt.*;

/**
 * Used to create a platform on which the player can stand.
 */
public class CollisionPlatform
{
    private Vector position;
    private Vector size;



    public CollisionPlatform(Vector startTopLeft, Vector _size)
    {
        position = startTopLeft;
        size = _size;
    }


    /**
     * Checks if a position (the feet of the player) is inside the platforms zone.
     * @param feetPosition Position of the players feet.
     * @return True if it is else false.
     */
    public boolean isColliding(Vector feetPosition)
    {
        if (feetPosition.getX() > position.getX() && feetPosition.getX() < position.getX() + size.getX())
        {
            if (feetPosition.getY() < position.getY() && feetPosition.getY() > position.getY() - size.getY())
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the y-position to which the player should be snapped in case of collision.
     * @return The top edge's y-position of the platform.
     */
    public float getTop()
    {
        return position.getY();
    }

    /**
     * Draws it on the screen.
     * @param g Graphics2D object
     * @param gameState The state of the game, used to convert from cartesian to graphical coordinates.
     */
    public void draw(Graphics2D g, GameState gameState)
    {
        Vector graphicalPosition = Helper.cartesianToGraphical(new Vector(position.getX(), position.getY()), gameState);
        g.fillRect((int)graphicalPosition.getX(), (int)graphicalPosition.getY(), (int)(size.getX() * Game.PIXELS_PER_METER), (int)(size.getY() * Game.PIXELS_PER_METER));
    }
}
