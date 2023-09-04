package org.hootengine.scene;

import org.hootengine.core.Game;

import java.util.ArrayList;

public class SceneManager {

    /*
     * Variables
     */

    /**
     * The game object.
     */
    private Game game;

    /**
     * All the scenes being managed by the scene manager.
     */
    private ArrayList<Scene> scenes;

    /**
     * The current game scene.
     */
    private Scene currentGameScene = null;

    /**
     * The current ui scene.
     */
    private Scene currentUIScene = null;


    /*
     * Main Objects
     */

    /**
     * Manages all the different game scenes.
     * @param game The game object.
     */
    public SceneManager(Game game) {

        this.game = game;

        scenes = new ArrayList<Scene>();

    }


    /*
     * Methods
     */

    /**
     * Add a scene to be managed by the scene manager.
     *
     * @param scene The scene to be added.
     * @param type The type of the scene (game, ui, ...).
     */
    public void addScene(Scene scene, SceneType type) {

        //Check to make sure the scene hasn't already been added
        for (Scene s: scenes) {
            if (s.equals(scene)) {
                return;
            }
        }

        if (scenes.size() == 0) {
            if (type == SceneType.GAME || type == null) {
                currentGameScene = scene;
            }else if (type == SceneType.UI) {
                currentUIScene = scene;
            }
        }

        scenes.add(scene);

        //Print that the scene's been added
        System.out.println("Scene: " + scene.getName() + " has been added!");

    }

    /**
     * Start a scene.
     *
     * @param name The name of the scene to be started.
     */
    public void startScene(String name) {

        if (currentGameScene != null) {
            currentGameScene.stop();
            currentGameScene = null;
        }

        for (Scene scene: scenes) {
            if (scene.getName().equals(name)) {

                currentGameScene = scene;
                currentGameScene.start();
                return;

            }
        }

    }

    /**
     * Update the scenes (both game and ui).
     *
     * @param delta The time difference.
     */
    public void update(float delta) {

        //Update the game scene
        if (currentGameScene != null) {
            if (currentGameScene.getCanUpdate())
                currentGameScene.update(delta);
        }

        //Update the ui scene.
        if (currentUIScene != null) {
            if (currentUIScene.getCanUpdate())
                currentUIScene.update(delta);
        }

    }


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
     * @return Returns all of the scenes.
     */
    public ArrayList<Scene> getScenes() {
        return scenes;
    }

    /**
     * @param name The name of the scene to be returned.
     * @return Returns a scene.
     */
    public Scene getScene(String name) {

        for (Scene scene: scenes) {

            if (scene.getName().equals(name)) {
                return scene;
            }

        }

        return null;

    }

    /**
     * @return Returns the current game scene.
     */
    public Scene getCurrentGameScene() {
        return currentGameScene;
    }

    /**
     * @return Returns the current ui scene.
     */
    public Scene getCurrentUIScene() {
        return currentUIScene;
    }

}
