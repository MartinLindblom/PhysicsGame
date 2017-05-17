package GameLogic;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;
import GameLogic.Physics.Collider;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class BoxOne extends GameObject
{
    private Vector position;
    private BufferedImage texture;

    private Collider collider;

    private float size = 36f;



    public BoxOne(Vector startPosition)
    {
        position = startPosition;
    }

    @Override
    public void initialize()
    {
        texture = AssetLoader.loadImage("BoxOne.png");
        instantiateCollider(collider = new Collider(new Vector(position.getX(), position.getY()), new Vector(texture.getWidth(), texture.getHeight()), true, this));
    }

    @Override
    public void update(float deltaTime)
    {
        collider.updatePosition(new Vector(position.getX(), position.getY()));
        collider.setSize(new Vector(size, size));

        if (getGameState().isKeyDown(KeyEvent.VK_1))
        {
            size += 0.1f;
        }

        if (getGameState().isKeyDown(KeyEvent.VK_Q))
        {
            size -= 0.1f;
        }

        System.out.println(size);
    }

    @Override
    public void render(Graphics2D g)
    {
        Vector graphicalPosition = Helper.cartesianToGraphical(new Vector(position.getX(), position.getY()), getGameState());
        g.drawImage(texture, (int)graphicalPosition.getX(), (int)graphicalPosition.getY(), null);
        collider.draw(g, Color.cyan, false, getGameState());
    }
}
