package GameLogic;

import Engine.GameState;
import Engine.Vector;

public class Helper
{
    public static Vector cartesianToGraphical(Vector v, GameState gameState)
    {
        return new Vector((gameState.getGameWindow().getWidth() / 2f) + (v.getX() - Game.scrollOffset) * Game.PIXELS_PER_METER, gameState.getGameWindow().getHeight() - (v.getY() * Game.PIXELS_PER_METER));
    }
}
