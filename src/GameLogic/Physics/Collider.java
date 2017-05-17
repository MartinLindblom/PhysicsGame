package GameLogic.Physics;

import Engine.GameObject;
import Engine.GameState;
import Engine.Vector;
import GameLogic.Game;
import GameLogic.Helper;

import java.awt.*;


public class Collider
{
    private GameObject parent;

    private Vector position;
    private Vector size;

    private boolean isStatic;

    private Vector collisionVector;



    public Collider(Vector startPosition, Vector _size, boolean _isStatic, GameObject _parent)
    {
        parent = _parent;

        position = startPosition;
        size = _size;

        isStatic = _isStatic;

        collisionVector = new Vector(0, 0);
    }



    public GameObject getParent()
    {
        return parent;
    }

    public boolean isStatic()
    {
        return isStatic;
    }

    public Vector getCollisionVector()
    {
        return collisionVector;
    }

    public float getWidth()
    {
        return size.getX();
    }

    public float getHeight()
    {
        return size.getY();
    }

    public Vector getCenter()
    {
        return new Vector(position.getX() + ((size.getX() / 2f) / Game.PIXELS_PER_METER), position.getY() + ((size.getY() / 2f) / Game.PIXELS_PER_METER));
    }

    public void setCollisionVector(Vector v)
    {
        collisionVector = v;
    }



    public void checkCollision(Collider other)
    {
        Vector displacement = new Vector(0, 0);

        float deltaX = Math.abs(getCenter().getX() - other.getCenter().getX());
        float deltaY = Math.abs(getCenter().getY() - other.getCenter().getY());

        float totalWidth = ((getWidth() / 2f) + (other.getWidth() / 2f)) / Game.PIXELS_PER_METER;
        float totalHeight = ((getHeight() / 2f) + (other.getHeight() / 2f)) / Game.PIXELS_PER_METER;

        if ((deltaX < totalWidth) && (deltaY < totalHeight))
        {
            if ((totalWidth - deltaX) < (totalHeight - deltaY))
            {
                if (getCenter().getX() < other.getCenter().getX())
                {
                    displacement.setX(-(totalWidth - deltaX));
                }
                else
                {
                    displacement.setX(totalWidth - deltaX);
                }
            }
            else
            {
                if (getCenter().getY() < other.getCenter().getY())
                {
                    displacement.setY(-(totalHeight - deltaY));
                }
                else
                {
                    displacement.setY(totalHeight - deltaY);
                }
            }
        }

        setCollisionVector(displacement);
        other.setCollisionVector(new Vector(-displacement.getX(), -displacement.getY()));
    }



    public void updatePosition(Vector newPosition)
    {
        position = newPosition;
    }

    public void setSize(Vector newSize)
    {
        size = newSize;
    }

    public void draw(Graphics2D g, Color color, boolean filled, GameState gameState)
    {
        g.setColor(color);

        Vector graphicalPosition = Helper.cartesianToGraphical(new Vector(position.getX(), position.getY()), gameState);

        if (filled)
        {
            g.fillRect((int)graphicalPosition.getX(), (int)graphicalPosition.getY(), (int)size.getX(), (int)size.getY());
        }
        else
        {
            g.drawRect((int)graphicalPosition.getX(), (int)graphicalPosition.getY(), (int)size.getX(), (int)size.getY());
        }

        g.setColor(Color.BLACK);
    }
}
