package GameLogic;

import Engine.*;
import GameLogic.Flamman.FlammanLevel;

import java.awt.*;
import java.util.ArrayList;

public class Game
{
    public static final float GRAVITY = 9.82f;
    public static final int PIXELS_PER_METER = 50;



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
                instantiateGameObject(new FlammanLevel());
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
        float deltaTime;
        lastTimeStamp = System.nanoTime();

        while (!window.shouldClose())
        {
            deltaTime = (System.nanoTime() - lastTimeStamp) / 1000000000f;
            lastTimeStamp = System.nanoTime();

            java.util.List<GameObject> currentGameObjects = new ArrayList<>(gameObjectManager.getGameObjects());

            Graphics2D g = window.getGraphics();

            for (GameObject gameObject : currentGameObjects)
            {
                gameObject.update(deltaTime);
                gameObject.render(g);
            }

            window.renderCanvas();
        }

        window.close();
    }
}
