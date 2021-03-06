package GameLogic;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to set up the level on which the player moves and interacts.
 */
public class FlammanLevel extends GameObject
{
    private BufferedImage background;
    private Vector backgroundPosition;

    private Player player;
    private float timer;



    public FlammanLevel()
    {
        timer = 180f;
    }


    /**
     * Initializes all the images, muffins and collision platforms as well as the player.
     */
    @Override
    public void initialize()
    {
        background = AssetLoader.loadImage("Background.png");
        backgroundPosition = new Vector(-700f / Game.PIXELS_PER_METER, (float)background.getHeight() / Game.PIXELS_PER_METER);

        List<CollisionPlatform> collisionPlatforms = new ArrayList<>();
        List<Muffin> muffins = new ArrayList<>();

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



        Muffin muffinObject;

        instantiateGameObject(muffinObject = new Muffin(new Vector(4.2f, 11.82f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(21.14f, 1.44f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(37.4f, 8.4f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(43.74f, 8.9f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(47.74f, 11.1f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(50.62f, 6.6f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(65.24f, 5.22f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(75.4f, 6.86f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(79.72f, 8.32f)));
        muffins.add(muffinObject);

        MuffinPendulum muffinPendulum;
        instantiateGameObject(muffinPendulum = new MuffinPendulum(new Vector(88.82f, 12.32f)));
        muffins.add(muffinPendulum.getMuffin());

        instantiateGameObject(muffinObject = new Muffin(new Vector(99.98f, 6.06f)));
        muffins.add(muffinObject);
        instantiateGameObject(muffinObject = new Muffin(new Vector(114.1f, 6.06f)));
        muffins.add(muffinObject);

        instantiateGameObject(player = new Player(new Vector(0f, (42f + 84f) / Game.PIXELS_PER_METER), collisionPlatforms, muffins));
    }

    /**
     * Checks if the player has reached the end and also if the timer has run out.
     * If the timer has run out the player loses and if the player reaches the goal in time the player wins.
     * @param deltaTime The time that has passed since the last update call.
     */
    @Override
    public void update(float deltaTime)
    {
        timer -= deltaTime;

        if (timer > 0)
        {
            if (player.getPosition().getX() > 120f)
            {
                Game.won = true;
            }
        }
        else
        {
            Game.lost = true;
        }
    }

    /**
     * Draws the timer, background and score to the screen.
     * @param g Graphics object used in order to draw to the canvas.
     */
    @Override
    public void render(Graphics2D g)
    {
        Vector graphicalPosition = Helper.cartesianToGraphical(backgroundPosition, getGameState());
        g.drawImage(background, (int)graphicalPosition.getX(), (int)graphicalPosition.getY(), null);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(String.valueOf(Game.points), 50, 50);
        g.drawString(String.valueOf(timer), 1155, 50);
    }
}
