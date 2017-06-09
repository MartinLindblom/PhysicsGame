import GameLogic.Game;

/**
 * Starting point of the game.
 */
public class Main
{
    /**
     * Creates a new {@link Game}
     * @param args Arguments passed to the program.
     */
    public static void main(String[] args)
    {
        new Game(1280, 720, "BG-Fika").run();
    }
}
