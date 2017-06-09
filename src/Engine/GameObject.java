package Engine;

import java.awt.*;

/**
 * Holds the logic that is identical for all game objects. As well as a few abstract methods.
 */
public abstract class GameObject
{
    private GameObjectManager gameObjectManager;



    private GameObjectManager getGameObjectManager()
    {
        return gameObjectManager;
    }

    public void setGameObjectManager(GameObjectManager gom)
    {
        gameObjectManager = gom;
    }

    public GameState getGameState()
    {
        return gameObjectManager.getGameState();
    }


    /**
     * Registers this game object at the passed {@link GameObjectManager} so it will receive updates and render calls.
     * @param gom The {@link GameObjectManager} at which to register.
     */
    private void register(GameObjectManager gom)
    {
        setGameObjectManager(gom);
        gom.registerGameObject(this);
    }

    /**
     * Instantiates another game object so that it will be registered and created properly.
     * @param gameObject The game object to register.
     */
    public void instantiateGameObject(GameObject gameObject)
    {
        gameObject.register(getGameObjectManager());
    }


    /**
     * Abstract method that will be called immediately after this game object has been instantiated.
     */
    public abstract void initialize();

    /**
     * Called every game tick.
     * @param deltaTime The time that has passed since the last update call.
     */
    public abstract void update(float deltaTime);

    /**
     * Called every game tick
     * @param g Graphics object used in order to draw to the canvas.
     */
    public abstract void render(Graphics2D g);
}
