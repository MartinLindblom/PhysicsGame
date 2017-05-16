package Engine;

import GameLogic.Physics.Collider;

import java.util.ArrayList;
import java.util.List;

public class GameObjectManager
{
    private GameState gameState;
    private List<GameObject> gameObjects;

    private List<Collider> staticColliders;
    private List<Collider> colliders;


    public GameObjectManager(GameState gs)
    {
        gameState = gs;

        gameObjects = new ArrayList<>();

        staticColliders = new ArrayList<>();
        colliders = new ArrayList<>();
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

    public void registerCollider(Collider collider)
    {
        if (collider.isStatic())
        {
            staticColliders.add(collider);
        }
        else
        {
            colliders.add(collider);
        }
    }

    public void unregisterCollider(Collider collider)
    {
        if (collider.isStatic())
        {
            staticColliders.remove(collider);
        }
        else
        {
            colliders.remove(collider);
        }
    }



    public void checkCollisions()
    {
        for (int i = 0; i < colliders.size(); i++)
        {
            for (Collider sc : staticColliders)
            {
                colliders.get(i).checkCollision(sc);
            }

            for (int j = (i + 1); j < colliders.size(); j++)
            {
                colliders.get(i).checkCollision(colliders.get(j));
            }
        }
    }
}
