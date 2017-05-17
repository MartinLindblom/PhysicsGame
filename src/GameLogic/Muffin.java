package GameLogic;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Muffin extends GameObject
{
    private Vector position;

    private BufferedImage texture;



    public Muffin(Vector _startPosition)
    {
        position = _startPosition;
    }



    public void updatePosition(Vector newPosition)
    {
        position = new Vector(newPosition.getX() - (((float)texture.getWidth() / 2f) / Game.PIXELS_PER_METER), newPosition.getY() + (((float)texture.getHeight() / 2f) / Game.PIXELS_PER_METER));
    }

    @Override
    public void initialize()
    {
        texture = AssetLoader.loadImage("Muffin.png");
    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        Vector graphicalPosition = Helper.cartesianToGraphical(new Vector(position.getX(), position.getY()), getGameState());
        g.drawImage(texture, (int)graphicalPosition.getX(), (int)graphicalPosition.getY(), null);
    }
}
