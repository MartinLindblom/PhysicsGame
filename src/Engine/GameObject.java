package Engine;

import java.awt.*;

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



    private void register(GameObjectManager gom)
    {
        setGameObjectManager(gom);
        gom.registerGameObject(this);
    }

    public void instantiateGameObject(GameObject gameObject)
    {
        gameObject.register(getGameObjectManager());
    }



    public abstract void initialize();

    public abstract void update(float deltaTime);

    public abstract void render(Graphics2D g);
}
