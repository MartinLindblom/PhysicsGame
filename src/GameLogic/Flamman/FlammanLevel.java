package GameLogic.Flamman;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;
import GameLogic.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FlammanLevel extends GameObject
{
    private BufferedImage background;
    private Vector backgroundPosition;



    @Override
    public void initialize()
    {
        background = AssetLoader.loadImage("Background.png");
        backgroundPosition = new Vector(-700f / Game.PIXELS_PER_METER, (float)background.getHeight() / Game.PIXELS_PER_METER);

        List<CollisionPlatform> collisionPlatforms = new ArrayList<>();
        collisionPlatforms.add(new CollisionPlatform(new Vector(1.96f, 1.58f), new Vector(0.72f, 0.2f)));

        for (int i = 0; i < 7; i++)
        {
            collisionPlatforms.add(new CollisionPlatform(new Vector(4.06f, 2.76f + (1.28f * i)), new Vector(0.72f, 0.2f))); // Windows
        }

        collisionPlatforms.add(new CollisionPlatform(new Vector(17.84f, 4.76f), new Vector(4.8f, 0.2f)));   // Tree
        collisionPlatforms.add(new CollisionPlatform(new Vector(22.48f, 2.3f), new Vector(1.44f, 0.2f)));   // Box
        collisionPlatforms.add(new CollisionPlatform(new Vector(23.54f, 3.68f), new Vector(1.44f, 0.2f)));  // Box
        collisionPlatforms.add(new CollisionPlatform(new Vector(24.04f, 2.3f), new Vector(1.44f, 0.2f)));   // Box
        collisionPlatforms.add(new CollisionPlatform(new Vector(25.54f, 5.8f), new Vector(0.72f, 0.2f)));   // Sign
        collisionPlatforms.add(new CollisionPlatform(new Vector(26.22f, 7.4f), new Vector(12.96f, 0.2f)));  // Flamman

        collisionPlatforms.add(new CollisionPlatform(new Vector(40.24f, 1.58f), new Vector(0.72f, 0.2f)));  // Trashcan
        collisionPlatforms.add(new CollisionPlatform(new Vector(41.12f, 1.58f), new Vector(0.72f, 0.2f)));  // Trashcan

        collisionPlatforms.add(new CollisionPlatform(new Vector(43.4f, 8.34f), new Vector(1.2f, 0.2f)));  // Tree
        collisionPlatforms.add(new CollisionPlatform(new Vector(58.04f, 2.28f), new Vector(1.44f, 0.2f)));  // Box

        collisionPlatforms.add(new CollisionPlatform(new Vector(59.54f, 3.74f), new Vector(4f, 0.2f)));  // Coop
        collisionPlatforms.add(new CollisionPlatform(new Vector(67.38f, 3.74f), new Vector(4f, 0.2f)));  // Coop

        collisionPlatforms.add(new CollisionPlatform(new Vector(73.82f, 5.36f), new Vector(4.4f, 0.2f)));  // Tree
        collisionPlatforms.add(new CollisionPlatform(new Vector(78.82f, 7f), new Vector(4.1f, 0.2f)));  // Tree

        collisionPlatforms.add(new CollisionPlatform(new Vector(86.58f, 5.82f), new Vector(2.8f, 0.2f)));  // Barba
        collisionPlatforms.add(new CollisionPlatform(new Vector(91.6f, 5.8f), new Vector(2.28f, 0.2f)));  // Barba

        collisionPlatforms.add(new CollisionPlatform(new Vector(96.46f, 5.46f), new Vector(5.2f, 0.2f)));  // Kårallen
        collisionPlatforms.add(new CollisionPlatform(new Vector(101.44f, 6.74f), new Vector(11.25f, 0.2f)));  // Kårallen
        collisionPlatforms.add(new CollisionPlatform(new Vector(112.8f, 5.46f), new Vector(2.88f, 0.2f)));  // Kårallen



        instantiateGameObject(new Muffin(new Vector(4.2f, 11.82f)));
        instantiateGameObject(new Muffin(new Vector(21.14f, 1.44f)));
        instantiateGameObject(new Muffin(new Vector(37.4f, 8.4f)));
        instantiateGameObject(new Muffin(new Vector(43.74f, 8.9f)));
        instantiateGameObject(new Muffin(new Vector(47.74f, 11.1f)));
        instantiateGameObject(new Muffin(new Vector(51.62f, 6.6f)));
        instantiateGameObject(new Muffin(new Vector(65.24f, 5.22f)));
        instantiateGameObject(new Muffin(new Vector(75.4f, 6.86f)));
        instantiateGameObject(new Muffin(new Vector(79.72f, 8.32f)));

        instantiateGameObject(new MuffinPendulum(new Vector(88.82f, 12.32f)));

        instantiateGameObject(new Muffin(new Vector(99.98f, 6.06f)));
        instantiateGameObject(new Muffin(new Vector(114.1f, 6.06f)));

        instantiateGameObject(new Player(new Vector(0f, (42f + 84f) / Game.PIXELS_PER_METER), collisionPlatforms));
    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        Vector graphicalPosition = Helper.cartesianToGraphical(backgroundPosition, getGameState());
        g.drawImage(background, (int)graphicalPosition.getX(), (int)graphicalPosition.getY(), null);
    }
}
