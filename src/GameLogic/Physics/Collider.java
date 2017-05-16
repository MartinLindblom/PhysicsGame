package GameLogic.Physics;

import Engine.GameState;
import Engine.Vector;
import GameLogic.Game;

import java.awt.*;


public class Collider
{
    private Vector position;
    private Vector size;

    private boolean isStatic;

    private Vector collisionVector;



    public Collider(Vector startPosition, Vector _size, boolean _isStatic)
    {
        position = startPosition;
        size = _size;
        isStatic = _isStatic;
    }



    public boolean isStatic()
    {
        return isStatic;
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
        return new Vector((position.getX() + size.getX()) / 2f, (position.getY() + size.getY()) / 2f);
    }

    public void setCollisionVector(Vector v)
    {
        collisionVector = v;
    }



    public void checkCollision(Collider other)
    {
        float depthX = ((getWidth() / 2f) + (other.getWidth() / 2f)) - Math.abs(getCenter().getX() - other.getCenter().getX());
        float depthY = ((getHeight() / 2f) + (other.getHeight() / 2f)) - Math.abs(getCenter().getY() - other.getCenter().getY());

        System.out.println(depthX);

        if (depthX <= 0 || depthY <= 0)
        {
            System.out.println("Collision!");

            Vector depthVector = new Vector(0, 0);

            if (getCenter().getX() < other.getCenter().getX())
            {
                depthVector.setX(-depthX);
            }
            else
            {
                depthVector.setX(depthX);
            }

            if (getCenter().getY() < other.getCenter().getY())
            {
                depthVector.setY(-depthY);
            }
            else
            {
                depthVector.setY(depthY);
            }

            setCollisionVector(depthVector);
            other.setCollisionVector(depthVector.invert());
        }
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

        Vector graphicalPosition = Formulas.cartesianToGraphical(new Vector(position.getX() - Game.scrollOffset, position.getY()), gameState);

        if (filled)
        {
            g.drawRect((int)graphicalPosition.getX(), (int)graphicalPosition.getY(), (int)size.getX(), (int)size.getY());
        }
        else
        {
            g.fillRect((int)graphicalPosition.getX(), (int)graphicalPosition.getY(), (int)size.getX(), (int)size.getY());
        }

        g.setColor(Color.BLACK);
    }
}
