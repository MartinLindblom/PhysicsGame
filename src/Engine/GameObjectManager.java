package Engine;

import java.util.ArrayList;
import java.util.List;

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
