package GameLogic.Flamman;

import Engine.GameObject;
import Engine.Vector;
import GameLogic.Player;

import java.awt.*;

public class FlammanLevel extends GameObject
{

    @Override
    public void initialize()
    {
        instantiateGameObject(new FlammanHouse(new Vector(300, 456)));
        instantiateGameObject(new Player(new Vector(10, 1)));
    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(new Color(90, 90, 90));
        g.fillRect(0, getGameState().getGameWindow().getHeight() - 60, getGameState().getGameWindow().getWidth(), getGameState().getGameWindow().getHeight());
        g.setColor(Color.black);
    }
}
