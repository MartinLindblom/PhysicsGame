package GameLogic;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;
import GameLogic.Physics.Formulas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;



enum PlayerState
{
    STANDING_STILL,
    MOVING_LEFT_STANDING,
    MOVING_LEFT_RUNNING,
    MOVING_RIGHT_STANDING,
    MOVING_RIGHT_RUNNING
}



public class Player extends GameObject
{
    private final float DRAG_COEFFICIENT = 1f;
    private final float FRICTION_COEFFICIENT = 10f;
    private final float MASS = 70;
    private final float MOVEMENT_FORCE = 100000f;
    private final float JUMP_FORCE = 9000f;

    private BufferedImage currentTexture;
    private BufferedImage runLeftTexture;
    private BufferedImage runRightTexture;
    private BufferedImage standLeftTexture;
    private BufferedImage standRightTexture;

    private Vector position;

    private Vector netForce;
    private Vector fa;
    private Vector velocity;
    private Vector acceleration;

    private PlayerState state;
    private boolean isGrounded;

    private float animationElapsedTime;
    private float animationFrameDuration;



    public Player(Vector startPosition)
    {
        position = startPosition;

        netForce = new Vector(0, 0);
        fa = new Vector(0, 0);
        velocity = new Vector(0, 0);
        acceleration = new Vector(0, 0);

        state = PlayerState.MOVING_RIGHT_STANDING;
        isGrounded = true;

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
        fa = new Vector(0, 0);

        if (position.getY() <= 144)
        {
            position.setY(144);
            isGrounded = true;
        }

        checkInputs();
        calculateForces();
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
        }

        Vector graphicalPosition = Formulas.cartesianToGraphical(position, getGameState());

        g.drawImage(currentTexture, (int)graphicalPosition.getX(), (int)graphicalPosition.getY(), null);
    }



    private void checkInputs()
    {
        if (getGameState().isKeyDown(KeyEvent.VK_RIGHT) && isGrounded)
        {
            if (state != PlayerState.MOVING_RIGHT_STANDING && state != PlayerState.MOVING_RIGHT_RUNNING)
            {
                state = PlayerState.MOVING_RIGHT_STANDING;
            }

            fa.setX(MOVEMENT_FORCE);
        }

        if (getGameState().isKeyDown(KeyEvent.VK_LEFT) && isGrounded)
        {
            if (state != PlayerState.MOVING_LEFT_STANDING && state != PlayerState.MOVING_LEFT_RUNNING)
            {
                state = PlayerState.MOVING_LEFT_STANDING;
            }

            fa.setX(-MOVEMENT_FORCE);
        }

        if (getGameState().isKeyDown(KeyEvent.VK_SPACE) && isGrounded)
        {
            isGrounded = false;

            fa.setY(JUMP_FORCE);
        }

        if (getGameState().isKeyUp(KeyEvent.VK_RIGHT) && getGameState().isKeyUp(KeyEvent.VK_LEFT))
        {
            if (getGameState().isKeyUp(KeyEvent.VK_SPACE))
            {
                state = PlayerState.STANDING_STILL;
            }
        }

        if (getGameState().isKeyDown(KeyEvent.VK_R))
        {
            position = new Vector(50, getGameState().getGameWindow().getHeight() - 144);
            velocity = new Vector(0, 0);
        }
    }

    private void calculateForces()
    {
        Vector fg = new Vector(0, MASS * -Game.GRAVITY);
        Vector fn = new Vector(0, 0);

        if (isGrounded)
        {
            fn.setY(-fg.getY());
        }

        float negativeXDirection = (fa.getX() != 0 ? -fa.getX() / fa.getX() : 0);

        Vector ff = new Vector(FRICTION_COEFFICIENT * fn.getY() * negativeXDirection, 0);
        Vector fb = new Vector(-velocity.getX() * DRAG_COEFFICIENT, -velocity.getY() * DRAG_COEFFICIENT);

        netForce = fa.add(fg.add(fn.add(ff.add(fb))));
    }

    private void updatePosition(float deltaTime)
    {
        acceleration = new Vector(netForce.getX() / MASS, netForce.getY() / MASS);

        position.add(Formulas.calculateDisplacement(velocity, acceleration, deltaTime));

        velocity.add(new Vector(acceleration.getX() * deltaTime, acceleration.getY() * deltaTime));
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
