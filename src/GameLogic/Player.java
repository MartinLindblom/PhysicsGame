package GameLogic;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;
import GameLogic.Physics.Collider;
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
    MOVING_RIGHT_RUNNING,
    FLYING_LEFT,
    FLYING_RIGHT
}



public class Player extends GameObject
{
    private static final float DRAG_COEFFICIENT = 7.75f;
    private static final float FRICTION_COEFFICIENT = 0.45f;
    private static final float MASS = 70;
    private static final float MOVEMENT_FORCE = 425f;
    private static final float JUMP_FORCE = 23000;

    private BufferedImage currentTexture;
    private BufferedImage runLeftTexture;
    private BufferedImage runRightTexture;
    private BufferedImage standLeftTexture;
    private BufferedImage standRightTexture;
    private BufferedImage flyingLeftTexture;
    private BufferedImage flyingRightTexture;

    private Vector position;

    private Vector netForce;
    private Vector fa;
    private Vector velocity;
    private Vector acceleration;

    private PlayerState state;
    private boolean isGrounded;

    private float animationElapsedTime;
    private float animationFrameDuration;

    private Collider collider;



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
        flyingLeftTexture = AssetLoader.loadImage("LeftJumping.png");
        flyingRightTexture = AssetLoader.loadImage("RightJumping.png");

        currentTexture = standRightTexture;

        instantiateCollider(collider = new Collider(new Vector(position.getX(), position.getY()), new Vector(currentTexture.getWidth(), currentTexture.getHeight()), false));
    }

    @Override
    public void update(float deltaTime)
    {
        fa = new Vector(0, 0);

        if (position.getY() < 144f / Game.PIXELS_PER_METER)
        {
            position.setY(144f / Game.PIXELS_PER_METER);
            isGrounded = true;
        }

        checkInputs();
        calculateForces();
        updatePosition(deltaTime);
        updateTexture(deltaTime);

        collider.updatePosition(new Vector(position.getX() + ((getGameState().getGameWindow().getWidth() / 2f) / Game.PIXELS_PER_METER), position.getY()));
        collider.setSize(new Vector(currentTexture.getWidth(), currentTexture.getHeight()));
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

            case FLYING_LEFT:
                currentTexture = flyingLeftTexture;
                break;

            case FLYING_RIGHT:
                currentTexture = flyingRightTexture;
                break;
        }

        Vector graphicalPosition = Formulas.cartesianToGraphical(new Vector((getGameState().getGameWindow().getWidth() / 2f) / Game.PIXELS_PER_METER, position.getY()), getGameState());

        g.drawImage(currentTexture, (int)graphicalPosition.getX(), (int)graphicalPosition.getY(), null);

        collider.draw(g, Color.green, false, getGameState());
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
            velocity.setY(0);
        }

        if (getGameState().isKeyUp(KeyEvent.VK_RIGHT) && getGameState().isKeyUp(KeyEvent.VK_LEFT))
        {
            if (getGameState().isKeyUp(KeyEvent.VK_SPACE))
            {
                if (state != PlayerState.FLYING_LEFT && state != PlayerState.FLYING_RIGHT)
                {
                    state = PlayerState.STANDING_STILL;
                }
            }
        }
    }

    private void calculateForces()
    {
        Vector fg = new Vector(0, MASS * -Game.GRAVITY);
        Vector fn = new Vector(0, 0);
        Vector ff = new Vector(0, 0);

        if (isGrounded)
        {
            fn.setY(-fg.getY());

            float negativeXDirection = (velocity.getX() != 0 ? -velocity.getX() / Math.abs(velocity.getX()) : 0);

            ff = new Vector(FRICTION_COEFFICIENT * Math.abs(fn.getY()) * negativeXDirection, 0);
        }

        Vector fb = new Vector(-velocity.getX() * DRAG_COEFFICIENT, -velocity.getY() * DRAG_COEFFICIENT);

        netForce = fa.add(fg.add(fn.add(ff.add(fb))));
    }

    private void updatePosition(float deltaTime)
    {
        acceleration = new Vector(netForce.getX() / MASS, netForce.getY() / MASS);

        position.add(Formulas.calculateDisplacement(velocity, acceleration, deltaTime));

        velocity.add(new Vector(acceleration.getX() * deltaTime, acceleration.getY() * deltaTime));

        Game.scrollOffset = position.getX();
    }

    private void updateTexture(float deltaTime)
    {
        animationElapsedTime += deltaTime;

        if (isGrounded)
        {
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

                    case FLYING_LEFT:
                        state = PlayerState.MOVING_LEFT_STANDING;
                        break;

                    case FLYING_RIGHT:
                        state = PlayerState.MOVING_RIGHT_STANDING;
                        break;
                }
            }
        }
        else
        {
            switch (state)
            {
                case MOVING_LEFT_STANDING:
                    state = PlayerState.FLYING_LEFT;
                    break;

                case MOVING_LEFT_RUNNING:
                    state = PlayerState.FLYING_LEFT;
                    break;

                case MOVING_RIGHT_STANDING:
                    state = PlayerState.FLYING_RIGHT;
                    break;

                case MOVING_RIGHT_RUNNING:
                    state = PlayerState.FLYING_RIGHT;
                    break;
            }
        }
    }
}
