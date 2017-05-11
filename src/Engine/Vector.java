package Engine;

public class Vector
{
    private float x;
    private float y;



    public Vector(float _x, float _y)
    {
        x = _x;
        y = _y;
    }



    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float value)
    {
        x = value;
    }

    public void setY(float value)
    {
        y = value;
    }

    public Vector add(Vector v)
    {
        setX(getX() + v.getX());
        setY(getY() + v.getY());

        return this;
    }
}
