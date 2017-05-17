package GameLogic.Physics;

import Engine.GameObject;
import Engine.Vector;
import GameLogic.Game;
import GameLogic.Helper;

import java.awt.*;

public class Pendulum extends GameObject
{
    private Vector topPoint;
    private float length;
    private float angle;
    private float angularAcceleration;
    private float angularVelocity;
    private float dampening;



    public Pendulum(Vector fixedPosition, float _length, float startAngle, float _dampening, float initialVelocity)
    {
        topPoint = fixedPosition;
        length = _length;
        angle = startAngle;
        dampening = _dampening;
        angularVelocity = initialVelocity;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void update(float deltaTime)
    {
        if (Game.scrollOffset > topPoint.getX() - 12f)
        {
            angularAcceleration = (float) ((-Game.GRAVITY / length) * Math.sin(angle));
            angularVelocity += angularAcceleration * deltaTime;

            angularVelocity *= dampening;

            angle += (angularVelocity) * deltaTime;
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setStroke(new BasicStroke(2));

        float newX = (float)(Math.sin(angle) * (length * Game.PIXELS_PER_METER));
        float newY = (float)(Math.cos(angle) * (length * Game.PIXELS_PER_METER));

        Vector graphicalPosition = Helper.cartesianToGraphical(new Vector(topPoint.getX(), topPoint.getY()), getGameState());

        g.drawLine((int)graphicalPosition.getX(), (int)graphicalPosition.getY(), (int)(graphicalPosition.getX() + newX), (int)(graphicalPosition.getY() + newY));

        g.setStroke(new BasicStroke(1));
    }



    public Vector getSwingPoint()
    {
        return new Vector((float)(topPoint.getX() + Math.sin(angle) * length), (float)(topPoint.getY() - Math.cos(angle) * length));
    }
}
