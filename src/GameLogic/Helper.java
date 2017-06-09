package GameLogic;

import Engine.GameState;
import Engine.Vector;

/**
 * A "helper" class
 */
public class Helper
{
    /**
     * Converts a vector in-game coordinates to screen coordinates.
     * @param v The vector to convert
     * @param gameState The game state in order to get window width & height.
     * @return The screen-pixel position.
     */
    public static Vector cartesianToGraphical(Vector v, GameState gameState)
    {
        return new Vector((gameState.getGameWindow().getWidth() / 2f) + (v.getX() - Game.scrollOffset) * Game.PIXELS_PER_METER, gameState.getGameWindow().getHeight() - (v.getY() * Game.PIXELS_PER_METER));
    }
}
