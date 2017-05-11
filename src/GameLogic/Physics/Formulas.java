package GameLogic.Physics;

import Engine.Vector;

public class Formulas
{
    // Return the drag-force. Ignores fluid density and cross section area
    public static Vector calculateDrag(Vector velocity, float dragCoefficient)
    {
        Vector drag = new Vector(0, 0);

        drag.setX((float)(0.5f * Math.pow(velocity.getX(), 2) * dragCoefficient));
        drag.setY((float)(0.5f * Math.pow(velocity.getY(), 2) * dragCoefficient));

        return drag;
    }
}
