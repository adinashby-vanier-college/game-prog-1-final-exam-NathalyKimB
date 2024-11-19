import lang.stride.*;
import java.util.*;
import greenfoot.*;

/**
 * 
 */
public class CannonBall extends Actor
{
    /* making the cannon ball move, part 1*/
    private Point2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private static final double GRAVITY = 9.8 * 10;

    /**
     * Act - do whatever the CannonBall wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public CannonBall()
    {
        /* making the cannon ball move, part 2*/
        position = null;
        velocity =  new  Vector2D(0.0, 0.0);
        acceleration =  new  Vector2D(0.0, GRAVITY);
    }

    /**
     * 
     */
    public void act()
    {
        collision();
        if (isGameLost()) {
            transitionToGameOverWorld();
            Greenfoot.playSound("losing.wav");
        }
        updatePhysics();
    }

    /**
     * check if the cannon ball is touching the ladybug
     */
    public void collision()
    {
        Actor Ladybug = getOneIntersectingObject(Ladybug.class);
        if (Ladybug != null) {
            World world = getWorld();
            world.removeObject(Ladybug);
        }
    }

    /**
     * if the ladybug touches the ladybug then the player status is turned to game lost
     */
    public boolean isGameLost()
    {
        World world = getWorld();
        if (world.getObjects(Ladybug.class).isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * if the status of the player is game lost then they will be brought to GameOverWorld
     */
    public void transitionToGameOverWorld()
    {
        World GameOverWorld =  new  GameOverWorld();
        Greenfoot.setWorld(GameOverWorld);
    }

    /**
     * set the velocity,
     */
    public void setVelocity(Vector2D newValue)
    {
        velocity = newValue;
    }

    /**
     * update the physics
     */
    public void updatePhysics()
    {
        if (position == null) {
            position =  new  Point2D(getX(), getY());
        }
        /* Get time step duration*/
        MyWorld world = (MyWorld)getWorld();
        double dt = world.getTimeStepDuration();
        /* Update velocity*/
        Vector2D velocityVariation = Vector2D.multiply(acceleration, dt);
        velocity = Vector2D.add(velocity, velocityVariation);
        /* Update position*/
        Vector2D positionVariation = Vector2D.multiply(velocity, dt);
        position.add(positionVariation);
        /* Set new actor position*/
        setLocation((int)position.getX(), (int)position.getY());
    }
}
