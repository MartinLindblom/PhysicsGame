package GameLogic;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;
import GameLogic.Physics.Collider;
import GameLogic.Physics.Formulas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BoxOne extends GameObject
{
    private Vector position;
    private BufferedImage texture;

    private Collider collider;



    public BoxOne(Vector startPosition)
    {
        position = startPosition;
    }

    @Override
    public void initialize()
    {
        texture = AssetLoader.loadImage("BoxOne.png");
        instantiateCollider(collider = new Collider(new Vector(position.getX() + Game.scrollOffset, position.getY()), new Vector(texture.getWidth(), texture.getHeight()), true));
    }

    @Override
    public void update(float deltaTime)
    {
        collider.updatePosition(new Vector(position.getX(), position.getY()));
    }

    @Override
    public void render(Graphics2D g)
    {
        Vector graphicalPosition = Formulas.cartesianToGraphical(new Vector(position.getX() - Game.scrollOffset, position.getY()), getGameState());
        g.drawImage(texture, (int)graphicalPosition.getX(), (int)graphicalPosition.getY(), null);
        collider.draw(g, Color.cyan, false, getGameState());
    }
}
