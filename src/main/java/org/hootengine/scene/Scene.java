package org.hootengine.scene;

import org.hootengine.cameras.Camera;
import org.hootengine.core.Game;
import org.hootengine.gameobjects.GameObject;
import org.hootengine.gameobjects.GameObjectManager;
import org.hootengine.gameobjects.sprites.Sprite;
import org.hootengine.rendering.Renderer;
import org.joml.Vector2f;

import java.util.ArrayList;

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
     * Manages all the game objects.
     */
    private GameObjectManager gameObjectManager;

    /**
     * The default camera of the scene.
     */
    private Camera defaultCamera;

    private Renderer renderer;

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

        gameObjectManager = new GameObjectManager(this);

        defaultCamera = new Camera(new Vector2f(0, 0));

        renderer = new Renderer(this);

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

        init();
        preload();

        create();

        ArrayList<GameObject> gameObjects = getGameObjectManager().getGameObjects();

        for (GameObject go: gameObjects) {
            go.start();

            if (go instanceof Sprite) {
                renderer.addSprite((Sprite) go);
            }
        }

        //Allows the scene to be updated.
        canUpdate = true;

    }

    /**
     * Called before the update method (looped)
     *
     * @param delta The time difference.
     */
    public void preUpdate(float delta) {

        gameObjectManager.update(delta);

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
     * Initializes the scene.
     */
    public abstract void init();

    /**
     * Preloads all the assets to be used by the scene.
     */
    public abstract void preload();

    /**
     * Creates all the various sprites, sounds, text, ...
     */
    public abstract void create();

    /**
     * Updates the scene.
     * @param delta The time difference.
     */
    public abstract void update(float delta);


    /*
     * Getters & Setters
     */

    /**
     * @return The game object.
     */
    public Game getGame() {
        return game;
    }

    /**
     * @return The name of the scene.
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
     * Manages all the game objects.
     */
    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    /**
     * @return The default camera of the scene.
     */
    public Camera getDefaultCamera() {
        return defaultCamera;
    }

    /**
     * @return Whether the scene is active or not.
     */
    public boolean getActive() {
        return isActive;
    }

    /**
     * @return Whether the scene can update or not.
     */
    public boolean getCanUpdate() {
        return canUpdate;
    }

}
