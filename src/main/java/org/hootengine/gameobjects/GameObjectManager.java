package org.hootengine.gameobjects;

import org.hootengine.core.Game;
import org.hootengine.scene.Scene;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameObjectManager {

    /*
     * Variables
     */

    /**
     * A scene.
     */
    private Scene scene;

    /**
     * The game itself.
     */
    private Game game;

    /**
     * A list of game objects to be managed.
     */
    private ArrayList<GameObject> gameObjects;


    /*
     * Main Objects
     */

    /**
     * Manages all the game objects.
     *
     * @param scene A scene.
     */
    public GameObjectManager(Scene scene) {

        this.scene = scene;

        game = scene.getGame();

        gameObjects = new ArrayList<GameObject>();

    }


    /*
     * Methods
     */

    /**
     * Adds a game object to be managed.
     *
     * @param gameObject A game object.
     */
    public void addObject(GameObject gameObject) {

        //Make sure the game object doesn't already exist
        for (GameObject gb: gameObjects) {
            if (gb.equals(gameObject))
                return;
        }

        gameObjects.add(gameObject);

    }

    /**
     * Updates all the game objects.
     *
     * @param delta The time difference.
     */
    public void update(float delta) {

        //Update each individual game object
        for (GameObject gb: gameObjects) {
            if (gb != null) {
                if (gb.getScene() != null) {
                    if (gb.getScene().equals(game.getSceneManager().getCurrentGameScene()) || gb.getScene().equals(game.getSceneManager().getCurrentUIScene())) {
                        gb.update(delta);
                    }
                }
            }
        }

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The scene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @return The game itself.
     */
    public Game getGame() {
        return game;
    }

    /**
     * @return A list of all game objects.
     */
    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

}
