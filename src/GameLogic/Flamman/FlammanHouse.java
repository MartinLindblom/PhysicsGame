package GameLogic.Flamman;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;
import GameLogic.Physics.Formulas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FlammanHouse extends GameObject
{
    private BufferedImage texture;
    private Vector position;



    public FlammanHouse(Vector startPosition)
    {
        position = startPosition;
    }

    @Override
    public void initialize()
    {
        texture = AssetLoader.loadImage("FLAMMAN.png");
    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        Vector graphicalPosition = Formulas.cartesianToGraphical(position, getGameState());

        g.drawImage(texture, (int)graphicalPosition.getX(), (int)graphicalPosition.getY(), null);
    }
}
