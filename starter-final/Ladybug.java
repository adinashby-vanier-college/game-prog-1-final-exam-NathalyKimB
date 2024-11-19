// WARNING: This file is auto-generated and any changes to it will be overwritten
import lang.stride.*;
import java.util.*;
import greenfoot.*;

/**
 * 
 */
public class Ladybug extends Actor
{

    /**
     * Act - do whatever the Hero wants to do. This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        /* //to have the ladybug move*/
        if (Greenfoot.isKeyDown("left")) {
            move(3);
        }
        if (Greenfoot.isKeyDown("right")) {
            move(-3);
        }
        if (Greenfoot.isKeyDown("down")) {
            turn(-3);
        }
        if (Greenfoot.isKeyDown("up")) {
            turn(3);
        }
        /* //to transition to GameWon world  when  the ladybug touches the finish location*/
        if (isGameWon()) {
            transitionToGameWon();
            Greenfoot.playSound("win.wav");
        }
        collision();
    }

    /**
     * to see if  the ladybug is touching the FinishLocation
     */
    public boolean foundFinishLocation()
    {
        Actor FinishLocation = getOneObjectAtOffset(0, 0, FinishLocation.class);
        return FinishLocation != null;
    }

    /**
     * if the lady bug is touching FinishLocation, it will remove FinishLocation
     */
    public void collision()
    {
        Actor FinishLocation = getOneIntersectingObject(FinishLocation.class);
        if (FinishLocation != null) {
            World world = getWorld();
            world.removeObject(FinishLocation);
        }
    }

    /**
     * it is checking if if the lady bug is touching FinishLocation, if it is then the player won
     */
    public boolean isGameWon()
    {
        World world = getWorld();
        if (world.getObjects(FinishLocation.class).isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * if the lady bug is touching FinishLocation, it will remove FinishLocationso the player won, then the player is brought to GameWon world
     */
    public void transitionToGameWon()
    {
        World GameWon =  new  GameWon();
        Greenfoot.setWorld(GameWon);
    }
}
