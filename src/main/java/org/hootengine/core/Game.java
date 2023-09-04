package org.hootengine.core;

import org.hootengine.display.Window;
import org.hootengine.scene.SceneManager;

public class Game {

    /*
     * Variables
     */

    /**
     * The main game display.
     */
    private Window window;

    /**
     * Manages all the different game scenes.
     */
    private SceneManager sceneManager;


    /*
     * Main Objects
     */

    /**
     * A new game.
     */
    public Game() {

        sceneManager = new SceneManager(this);

    }


    /*
     * Methods
     */

    /**
     * Starts the game.
     */
    public void start() {

        init();

    }

    /**
     * Initializes the game.
     */
    private void init() {

        window = Window.get(this);

        //Next is 'boot' stage.
        boot();

    }

    /**
     * Boots the game (comes after the initialization phase).
     */
    private void boot() {

        sceneManager.getCurrentGameScene().start();
        window.run();

    }


    /*
     * Getters & Setters
     */

    /**
     * @return Returns the game window.
     */
    public Window getWindow() {
        return window;
    }

    /**
     * @return Returns the scene manager.
     */
    public SceneManager getSceneManager() {
        return sceneManager;
    }

}
