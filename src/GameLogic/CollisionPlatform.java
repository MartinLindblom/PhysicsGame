package GameLogic;

import Engine.GameState;
import Engine.Vector;

import java.awt.*;

public class CollisionPlatform
{
    private Vector position;
    private Vector size;



    public CollisionPlatform(Vector startTopLeft, Vector _size)
    {
        position = startTopLeft;
        size = _size;
    }



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

    public float getTop()
    {
        return position.getY();
    }

    public void draw(Graphics2D g, GameState gameState)
    {
        Vector graphicalPosition = Helper.cartesianToGraphical(new Vector(position.getX(), position.getY()), gameState);
        g.drawRect((int)graphicalPosition.getX(), (int)graphicalPosition.getY(), (int)(size.getX() * Game.PIXELS_PER_METER), (int)(size.getY() * Game.PIXELS_PER_METER));
    }
}
