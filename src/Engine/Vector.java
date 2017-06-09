package Engine;

/**
 * Class that acts as a vector.
 */
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

    /**
     * Adds another vector to this one.
     * @param v Vector to add
     * @return This vector. Can be used to chain add.
     */
    public Vector add(Vector v)
    {
        setX(getX() + v.getX());
        setY(getY() + v.getY());

        return this;
    }

    @Override
    public String toString()
    {
        return "(" + getX() + ", " + getY() + ")";
    }
}
