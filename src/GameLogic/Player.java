package GameLogic;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


enum PlayerState
{
    STANDING_STILL,
    MOVING_LEFT_STANDING,
    MOVING_LEFT_RUNNING,
    MOVING_RIGHT_STANDING,
    MOVING_RIGHT_RUNNING,
    IN_AIR
}



public class Player extends GameObject
{
    private final float DRAG_COEFFICIENT = 0.99f;
    private final float MASS = 70;
    private final float ACCELERATION_FORCE = 5000f;
    private final float JUMP_FORCE = 10000f;

    private BufferedImage currentTexture;
    private BufferedImage runLeftTexture;
    private BufferedImage runRightTexture;
    private BufferedImage standLeftTexture;
    private BufferedImage standRightTexture;

    private Vector position;

    private Vector velocity;
    private Vector acceleration;

    private PlayerState state;

    private float animationElapsedTime;
    private float animationFrameDuration;



    public Player(Vector startPosition)
    {
        position = startPosition;

        velocity = new Vector(0, 0);
        acceleration = new Vector(0, 0);

        state = PlayerState.MOVING_RIGHT_STANDING;

        animationElapsedTime = 0;
        animationFrameDuration = 0.2f;
    }

    @Override
    public void initialize()
    {
        runLeftTexture = AssetLoader.loadImage("LeftRunning.png");
        runRightTexture = AssetLoader.loadImage("RightRunning.png");
        standLeftTexture = AssetLoader.loadImage("LeftStanding.png");
        standRightTexture = AssetLoader.loadImage("RightStanding.png");

        currentTexture = standRightTexture;
    }

    @Override
    public void update(float deltaTime)
    {
        checkInputs();
        updatePosition(deltaTime);

        if (state != PlayerState.STANDING_STILL)
        {
            updateTexture(deltaTime);
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        switch (state)
        {
            case MOVING_LEFT_STANDING:
                currentTexture = standLeftTexture;
                break;

            case MOVING_LEFT_RUNNING:
                currentTexture = runLeftTexture;
                break;

            case MOVING_RIGHT_STANDING:
                currentTexture = standRightTexture;
                break;

            case MOVING_RIGHT_RUNNING:
                currentTexture = runRightTexture;
                break;

            case IN_AIR:
                //JUMP TEXTURE
                break;
        }

        g.drawImage(currentTexture, (int)position.getX(), (int)position.getY(), null);
    }



    private void checkInputs()
    {
        if (getGameState().isKeyDown(KeyEvent.VK_RIGHT))
        {
            if (state != PlayerState.MOVING_RIGHT_STANDING && state != PlayerState.MOVING_RIGHT_RUNNING)
            {
                state = PlayerState.MOVING_RIGHT_STANDING;
            }

            acceleration.setX(ACCELERATION_FORCE / MASS);
        }

        if (getGameState().isKeyDown(KeyEvent.VK_LEFT))
        {
            if (state != PlayerState.MOVING_LEFT_STANDING && state != PlayerState.MOVING_LEFT_RUNNING)
            {
                state = PlayerState.MOVING_LEFT_STANDING;
            }

            acceleration.setX(-ACCELERATION_FORCE / MASS);
        }

        if (getGameState().isKeyDown(KeyEvent.VK_SPACE))
        {
            state = PlayerState.IN_AIR;

            //acceleration.setY(-jumpAcceleration);
        }

        if (getGameState().isKeyUp(KeyEvent.VK_RIGHT) && getGameState().isKeyUp(KeyEvent.VK_LEFT))
        {
            if (getGameState().isKeyUp(KeyEvent.VK_SPACE))
            {
                state = PlayerState.STANDING_STILL;
            }

            acceleration.setX(0);
        }
    }

    private void updatePosition(float deltaTime)
    {
        velocity.setX(velocity.getX() + (acceleration.getX() * deltaTime));
        velocity.setY(velocity.getY() + (acceleration.getY() * deltaTime));

        velocity.setX(velocity.getX() * DRAG_COEFFICIENT);
        velocity.setY(velocity.getY() * DRAG_COEFFICIENT);

        position.setX(position.getX() + (velocity.getX() * deltaTime));
        position.setY(position.getY() + (velocity.getY() * deltaTime));
    }

    private void updateTexture(float deltaTime)
    {
        animationElapsedTime += deltaTime;

        if (animationElapsedTime > animationFrameDuration)
        {
            animationElapsedTime = 0;

            switch (state)
            {
                case MOVING_LEFT_STANDING:
                    state = PlayerState.MOVING_LEFT_RUNNING;
                    break;

                case MOVING_LEFT_RUNNING:
                    state = PlayerState.MOVING_LEFT_STANDING;
                    break;

                case MOVING_RIGHT_STANDING:
                    state = PlayerState.MOVING_RIGHT_RUNNING;
                    break;

                case MOVING_RIGHT_RUNNING:
                    state = PlayerState.MOVING_RIGHT_STANDING;
                    break;
            }
        }
    }
}
