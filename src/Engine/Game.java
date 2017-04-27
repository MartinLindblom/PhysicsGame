package Engine;

import java.awt.*;
import java.util.ArrayList;

public class Game
{
    private GameWindow window;
    private GameObjectManager gameObjectManager;

    private long lastTimeStamp;



    public Game(int windowWidth, int windowHeight, String windowTitle)
    {
        window = new GameWindow(windowWidth, windowHeight, windowTitle);
        InputManager inputManager = new InputManager();
        window.addKeyListener(inputManager);

        GameState gameState = new GameState();
        gameState.setGameWindow(window);
        gameState.setInputManager(inputManager);

        gameObjectManager = new GameObjectManager(gameState);

        GameObject root = new GameObject()
        {
            @Override
            public void initialize()
            {
                // Instantiate the first objects here!
            }

            @Override
            public void update(float deltaTime)
            {

            }

            @Override
            public void render(Graphics2D g)
            {

            }
        };
        root.setGameObjectManager(gameObjectManager);
        gameObjectManager.registerGameObject(root);
    }



    public void run()
    {
        lastTimeStamp = System.nanoTime();

        while (!window.shouldClose())
        {
            java.util.List<GameObject> currentGameObjects = new ArrayList<>(gameObjectManager.getGameObjects());

            Graphics2D g = window.getGraphics();

            for (GameObject gameObject : currentGameObjects)
            {
                gameObject.update(getDeltaTime());

                gameObject.render(g);
            }

            window.renderCanvas();
        }

        window.close();
    }

    private float getDeltaTime()
    {
        float deltaTime = (System.nanoTime() - lastTimeStamp) / 1000000000f;
        lastTimeStamp = System.nanoTime();

        return deltaTime;
    }
}
