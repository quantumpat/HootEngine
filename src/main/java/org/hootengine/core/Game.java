package org.hootengine.core;

import org.hootengine.display.Window;
import org.hootengine.scene.Scene;
import org.hootengine.scene.SceneManager;

public class Game {

    /*
     * Variables
     */

    /**
     * Configurations to be used by the game.
     */
    private GameConfig config;

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

        config = new GameConfig();

        sceneManager = new SceneManager(this);

    }

    public Game(GameConfig config) {

        this.config = config;

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

        window.run();
        sceneManager.getCurrentGameScene().start();

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The game config.
     */
    public GameConfig getConfig() {
        return config;
    }

    /**
     * @return The game window.
     */
    public Window getWindow() {
        return window;
    }

    /**
     * @return The scene manager.
     */
    public SceneManager getSceneManager() {
        return sceneManager;
    }

}
