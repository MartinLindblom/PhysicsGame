package GameLogic.Flamman;

import Engine.GameObject;
import Engine.Vector;
import GameLogic.BoxOne;
import GameLogic.Game;
import GameLogic.Player;

import java.awt.*;

public class FlammanLevel extends GameObject
{

    @Override
    public void initialize()
    {
        instantiateGameObject(new FlammanHouse(new Vector(300f / Game.PIXELS_PER_METER, 412f / Game.PIXELS_PER_METER)));
        instantiateGameObject(new Player(new Vector(0, 144f / Game.PIXELS_PER_METER)));
        instantiateGameObject(new BoxOne(new Vector(0, 132f / Game.PIXELS_PER_METER)));
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
