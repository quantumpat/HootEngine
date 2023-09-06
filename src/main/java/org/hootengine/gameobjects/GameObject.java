package org.hootengine.gameobjects;

import org.hootengine.core.Game;
import org.hootengine.scene.Scene;

public abstract class GameObject {

    /**
     * The scene the game object should be added to.
     */
    private Scene scene;

    /**
     * The name of the game object.
     */
    private String name;

    /**
     * The game itself.
     */
    private Game game;

    public Transform transform;


    /*
     * Main Objects
     */

    /**
     * A game object to be used by the scene.
     *
     * @param scene The scene the sprite should be added to.
     * @param name The name of the game object.
     */
    public GameObject(Scene scene, String name) {

        this.scene = scene;
        this.name = name;

        game = scene.getGame();

        transform = new Transform();

        scene.getGameObjectManager().addObject(this);

    }


    /*
     * Methods
     */
    public void start() {

    }


    /*
     * Abstract Methods
     */

    /**
     * Updates the game object.
     *
     * @param delta The time difference.
     */
    public abstract void update(float delta);


    /*
     * Getters & Setters
     */

    /**
     * @return The scene the game object exists in.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @return The name of the game object.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The game itself.
     */
    public Game getGame() {
        return game;
    }

}
