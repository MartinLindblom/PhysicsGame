package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * All the possible states of a key.
 */
enum KeyState
{
    PRESSED,
    HELD,
    RELEASED
}

/**
 * Handles the input management.
 */
public class InputManager implements KeyListener
{
    private KeyState[] keyStates;



    public InputManager()
    {
        keyStates = new KeyState[999];

        for (int i = 0; i < keyStates.length; i++)
        {
            keyStates[i] = KeyState.RELEASED;
        }
    }



    public KeyState getKeyState(int keyCode)
    {
        return keyStates[keyCode];
    }


    /**
     * Overrides the keyPressed event and sets the state of selected key to the appropriate state.
     * @param e KeyEvent passed
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        if (keyStates[keyCode] == KeyState.RELEASED)
        {
            keyStates[keyCode] = KeyState.PRESSED;
        }
        else if (keyStates[keyCode] == KeyState.PRESSED)
        {
            keyStates[keyCode] = KeyState.HELD;
        }
    }

    /**
     * Overrides the keyPressed event and sets the state of selected key to the appropriate state.
     * @param e KeyEvent passed
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        keyStates[e.getKeyCode()] = KeyState.RELEASED;
    }

    /**
     * Overrides the keyPressed event and sets the state of selected key to the appropriate state.
     * @param e KeyEvent passed
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
