package Engine;

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



    public boolean isKeyDown(int keyCode)
    {
        return getInputManager().getKeyState(keyCode) == KeyState.HELD || getInputManager().getKeyState(keyCode) == KeyState.PRESSED;
    }

    public boolean isKeyUp(int keyCode)
    {
        return getInputManager().getKeyState(keyCode) == KeyState.RELEASED;
    }

    public boolean isKeyPressed(int keyCode)
    {
        return getInputManager().getKeyState(keyCode) == KeyState.PRESSED;
    }
}
