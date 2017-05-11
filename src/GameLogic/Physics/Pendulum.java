package GameLogic.Physics;

import Engine.GameObject;
import Engine.Vector;
import GameLogic.Game;

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
        angularAcceleration = (float)((-Game.GRAVITY / length) * Math.sin(angle));
        angularVelocity += angularAcceleration * deltaTime;

        angularVelocity *= dampening;

        angle += (angularVelocity) * deltaTime;
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setStroke(new BasicStroke(2));

        float newX = (float)(Math.sin(angle) * (length * Game.PIXELS_PER_METER));
        float newY = (float)(Math.cos(angle) * (length * Game.PIXELS_PER_METER));

        g.drawLine((int)topPoint.getX(), (int)topPoint.getY(), (int)(topPoint.getX() + newX), (int)(topPoint.getY() + newY));

        g.setStroke(new BasicStroke(1));
    }



    public Vector getSwingPoint()
    {
        return new Vector((float)(topPoint.getX() + Math.sin(angle) * (length * Game.PIXELS_PER_METER)), (float)(topPoint.getY() + Math.cos(angle) * (length * Game.PIXELS_PER_METER)));
    }
}
