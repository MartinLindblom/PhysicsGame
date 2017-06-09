package Engine;

/**
 * Used to keep track and pass around data about the game state.
 */
public class GameState
{
    private GameWindow gameWindow;
    private InputManager inputManager;



    public GameWindow getGameWindow()
    {
        return gameWindow;
    }

    public void setGameWindow(GameWindow gw)
    {
        gameWindow = gw;
    }

    public InputManager getInputManager()
    {
        return inputManager;
    }

    public void setInputManager(InputManager im)
    {
        inputManager = im;
    }



    /**
     * Returns true if passed key is down.
     * @param keyCode The keycode of desired key to check.
     * @return True if key is down else false.
     */
    public boolean isKeyDown(int keyCode)
    {
        return getInputManager().getKeyState(keyCode) == KeyState.HELD || getInputManager().getKeyState(keyCode) == KeyState.PRESSED;
    }

    /**
     * Returns true if passed key is up.
     * @param keyCode The keycode of desired key to check.
     * @return True if key is up else false;
     */
    public boolean isKeyUp(int keyCode)
    {
        return getInputManager().getKeyState(keyCode) == KeyState.RELEASED;
    }
}
