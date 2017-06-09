package GameLogic;

import Engine.AssetLoader;
import Engine.GameObject;
import Engine.Vector;
import GameLogic.Physics.Formulas;

import java.util.ArrayList;
import java.util.List;

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
    private final float DRAG_COEFFICIENT = 7.75f;
    private final float FRICTION_COEFFICIENT = 0.5f;
    private final float MASS = 70;
    private final float MOVEMENT_FORCE = 450f;
    private final float JUMP_FORCE = 25000;
    private final float ANIMATION_FRAME_DURATION = 0.2f;

    private BufferedImage currentTexture;
    private BufferedImage runLeftTexture;
    private BufferedImage runRightTexture;
    private BufferedImage standLeftTexture;
    private BufferedImage standRightTexture;
    private BufferedImage flyingLeftTexture;
    private BufferedImage flyingRightTexture;

    private Vector position;

    private List<CollisionPlatform> collisionPlatforms;
    private List<Muffin> muffins;

    private Vector netForce;
    private Vector fa;
    private Vector velocity;
    private Vector acceleration;

    private PlayerState state;
    private boolean isGrounded;

    private float animationElapsedTime;



    public Player(Vector startPosition, List<CollisionPlatform> _collisionPlatforms, List<Muffin> _muffins)
    {
        position = startPosition;

        collisionPlatforms = new ArrayList<>(_collisionPlatforms);
        muffins = new ArrayList<>(_muffins);

        netForce = new Vector(0, 0);
        fa = new Vector(0, 0);
        velocity = new Vector(0, 0);
        acceleration = new Vector(0, 0);

        state = PlayerState.MOVING_RIGHT_STANDING;
        isGrounded = false;

        animationElapsedTime = 0;
    }

    public Vector getPosition()
    {
        return position;
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
    }

    @Override
    public void update(float deltaTime)
    {
        fa = new Vector(0, 0);

        checkInputs();
        calculateForces();
        updatePosition(deltaTime);
        checkCollisions();
        updateTexture(deltaTime);
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

        for (CollisionPlatform ca : collisionPlatforms)
        {
            g.setColor(Color.BLACK);
            ca.draw(g, getGameState());
        }

        Vector graphicalPosition = Helper.cartesianToGraphical(new Vector(position.getX(), position.getY()), getGameState());
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

    private void checkCollisions()
    {
        isGrounded = false;

        Vector feetPosition = new Vector(position.getX() + (((float)standRightTexture.getWidth() / 2f) / Game.PIXELS_PER_METER), position.getY() - ((float)standRightTexture.getHeight() / Game.PIXELS_PER_METER));

        for (CollisionPlatform ca : collisionPlatforms)
        {
            if (ca.isColliding(feetPosition))
            {
                position.setY(ca.getTop() + ((float)standRightTexture.getHeight() / Game.PIXELS_PER_METER));
                isGrounded = true;
            }
        }

        for (Muffin m : muffins)
        {
            if (!m.isHidden())
            {
                if (m.isColliding(position, new Vector((float) standRightTexture.getWidth() / Game.PIXELS_PER_METER, (float) standRightTexture.getHeight() / Game.PIXELS_PER_METER)))
                {
                    m.hide();
                    Game.points++;
                }
            }
        }

        if (position.getY() < (42f + 84f) / Game.PIXELS_PER_METER)
        {
            position.setY((42f + 84f) / Game.PIXELS_PER_METER);
            isGrounded = true;
        }
    }

    private void updateTexture(float deltaTime)
    {
        animationElapsedTime += deltaTime;

        if (isGrounded)
        {
            if (animationElapsedTime > ANIMATION_FRAME_DURATION)
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
