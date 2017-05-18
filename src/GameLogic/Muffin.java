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

    private boolean hidden;



    public Muffin(Vector _startPosition)
    {
        position = _startPosition;
        hidden = false;
    }



    public void updatePosition(Vector newPosition)
    {
        position = new Vector(newPosition.getX() - (((float)texture.getWidth() / 2f) / Game.PIXELS_PER_METER), newPosition.getY() + (((float)texture.getHeight() / 2f) / Game.PIXELS_PER_METER));
    }

    public boolean isHidden()
    {
        return hidden;
    }

    public boolean isColliding(Vector playerPosition, Vector playerSize)
    {
        Vector center = new Vector(position.getX() + (((float)texture.getWidth() / 2f) / Game.PIXELS_PER_METER), position.getY() - (((float)texture.getHeight() / 2f) / Game.PIXELS_PER_METER));

        if (center.getX() >= playerPosition.getX() && center.getX() <= playerPosition.getX() + playerSize.getX())
        {
            if (center.getY() <= playerPosition.getY() && center.getY() >= playerPosition.getY() - playerSize.getY())
            {
                return true;
            }
        }

        return false;
    }

    public void hide()
    {
        hidden = true;
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
        if (!hidden)
        {
            Vector graphicalPosition = Helper.cartesianToGraphical(new Vector(position.getX(), position.getY()), getGameState());
            g.drawImage(texture, (int) graphicalPosition.getX(), (int) graphicalPosition.getY(), null);
        }
    }
}
