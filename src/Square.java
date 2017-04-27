import Engine.AssetLoader;
import Engine.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Square extends GameObject
{
    private float x;
    private float y;

    private BufferedImage image;



    public Square()
    {
        x = 100;
        y = 100;
    }

    @Override
    public void initialize()
    {
        System.out.println("Created square");

        image = AssetLoader.loadImage("DefaultImage.png");
    }

    @Override
    public void update(float deltaTime)
    {
        if (getGameState().isKeyDown(KeyEvent.VK_W))
        {
            y -= 2 * deltaTime;
        }

        if (getGameState().isKeyDown(KeyEvent.VK_S))
        {
            y += 2 * deltaTime;
        }

        if (getGameState().isKeyDown(KeyEvent.VK_A))
        {
            x -= 2 * deltaTime;
        }

        if (getGameState().isKeyDown(KeyEvent.VK_D))
        {
            x += 2 * deltaTime;
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(image, null, (int)x, (int)y);
    }
}
