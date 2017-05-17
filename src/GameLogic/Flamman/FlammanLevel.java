package GameLogic.Flamman;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;
import GameLogic.BoxOne;
import GameLogic.Game;
import GameLogic.Helper;
import GameLogic.Physics.Collider;
import GameLogic.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FlammanLevel extends GameObject
{
    private BufferedImage background;
    private Vector backgroundPosition;

    private Collider debug;



    @Override
    public void initialize()
    {
        background = AssetLoader.loadImage("Background.png");
        backgroundPosition = new Vector(-700 / Game.PIXELS_PER_METER, background.getHeight() / Game.PIXELS_PER_METER);

        //instantiateCollider(debug = new Collider(new Vector(98f / Game.PIXELS_PER_METER, 100f / Game.PIXELS_PER_METER), new Vector(36, 36), true, this));

        instantiateGameObject(new BoxOne(new Vector(98f / Game.PIXELS_PER_METER, 100f / Game.PIXELS_PER_METER)));
        instantiateGameObject(new Player(new Vector(0f, 10f / Game.PIXELS_PER_METER)));
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

        //debug.draw(g, Color.red, true, getGameState());
    }
}
