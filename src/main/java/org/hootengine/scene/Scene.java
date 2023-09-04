package org.hootengine.scene;

import org.hootengine.core.Game;

public abstract class Scene {

    /*
     * Variables
     */

    /**
     * The game object.
     */
    private Game game;

    /**
     * The name of the scene.
     */
    private String name;

    /**
     * Whether the scene is active or not.
     */
    private boolean isActive = false;

    /**
     * Whether the scene can update or not.
     */
    private boolean canUpdate = false;


    /*
     * Main Objects
     */

    /**
     * Creates a new scene.
     *
     * @param game The game object.
     * @param name The name of the scene.
     */
    public Scene(Game game, String name) {

        this.game = game;
        this.name = name;

    }


    /*
     * Methods
     */

    /**
     * Starts up the scene.
     */
    public void start() {

        if (isActive) {
            return;
        }

        System.out.println("The " + getName() + " scene has started!");

        isActive = true;
        canUpdate = true;

    }

    /**
     * Stops the scene.
     */
    public void stop() {

        if (!isActive) {
            return;
        }

        isActive = false;

    }


    /*
     * Abstract Methods
     */

    /**
     * Updates the scene.
     * @param delta The time difference.
     */
    public abstract void update(float delta);


    /*
     * Getters & Setters
     */

    /**
     * @return Returns the game object.
     */
    public Game getGame() {
        return game;
    }

    /**
     * @return Returns the name of the scene.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name A name for the scene to be called.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns whether the scene is active or not.
     */
    public boolean getActive() {
        return isActive;
    }

    /**
     * @return Returns whether the scene can update or not.
     */
    public boolean getCanUpdate() {
        return canUpdate;
    }

}