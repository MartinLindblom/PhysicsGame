package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of all the game objects and handles registration and un-registration of them.
 */
public class GameObjectManager
{
    private GameState gameState;
    private List<GameObject> gameObjects;


    public GameObjectManager(GameState gs)
    {
        gameState = gs;

        gameObjects = new ArrayList<>();
    }


    public List<GameObject> getGameObjects()
    {
        return gameObjects;
    }

    public GameState getGameState()
    {
        return gameState;
    }


    /**
     * "Registers" a game object by adding it to the list.
     * @param gameObject
     * @return True if object was added and false if not.
     */
    public boolean registerGameObject(GameObject gameObject)
    {
        if (!gameObjects.contains(gameObject))
        {
            gameObjects.add(gameObject);
            gameObject.initialize();

            return true;
        }

        return false;
    }

    /**
     * "Un-registers" a game object by removing it from the list.
     * @param gameObject
     * @return True if object was removed and false if not.
     */
    public boolean unregisterGameObject(GameObject gameObject)
    {
        if (gameObjects.contains(gameObject))
        {
            gameObjects.remove(gameObject);

            return true;
        }

        return false;
    }
}
