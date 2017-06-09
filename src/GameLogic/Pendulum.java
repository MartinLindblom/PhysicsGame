package GameLogic;

import Engine.GameObject;
import Engine.Vector;

import java.awt.*;

/**
 * Used to create a pendulum.
 */
public class Pendulum extends GameObject
{
    private Vector topPoint;
    private float length;
    private float angle;
    private float angularAcceleration;
    private float angularVelocity;
    private float dampening;


    /**
     * Creates a pendulum from starting values.
     * @param fixedPosition The point about which it will swing
     * @param _length The length of the pendulum
     * @param startAngle The angle at which it will start.
     * @param _dampening The dampening ("air resistance") that slows down the pendulum
     * @param initialVelocity Starting velocity of the pendulum.
     */
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

    /**
     * Updates the angle at which the pendulum is.
     * @param deltaTime The time that has passed since the last update call.
     */
    @Override
    public void update(float deltaTime)
    {
        if (Game.scrollOffset > topPoint.getX() - 15f)
        {
            angularAcceleration = (float)((-Game.GRAVITY / length) * Math.sin(angle));
            angularVelocity += angularAcceleration * deltaTime;

            angularVelocity *= dampening;

            angle += (angularVelocity) * deltaTime;
        }
    }

    /**
     * Draws the pendulum to the screen.
     * @param g Graphics object used in order to draw to the canvas.
     */
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
