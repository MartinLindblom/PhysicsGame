package GameLogic;

import Engine.GameObject;
import Engine.Vector;

import java.awt.*;

public class MuffinPendulum extends GameObject
{
    private Vector position;

    private Pendulum pendulum;
    private Muffin muffin;



    public MuffinPendulum(Vector startPosition)
    {
        position = startPosition;
    }



    public Muffin getMuffin()
    {
        return muffin;
    }



    @Override
    public void initialize()
    {
        instantiateGameObject(pendulum = new Pendulum(position, 210f / Game.PIXELS_PER_METER, (float)(Math.PI / 4f), 0.995f, -3f));
        instantiateGameObject(muffin = new Muffin(pendulum.getSwingPoint()));
    }

    @Override
    public void update(float deltaTime)
    {
        muffin.updatePosition(pendulum.getSwingPoint());
    }

    @Override
    public void render(Graphics2D g)
    {

    }
}
