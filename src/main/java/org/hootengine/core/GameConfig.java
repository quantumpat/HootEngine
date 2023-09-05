package org.hootengine.core;

public class GameConfig {

    /*
     * Variables
     */

    /**
     * The width (in pixels) of the game window.
     */
    private int width = 1280;

    /**
     * The height (in pixels) of the game window.
     */
    private int height = 720;

    /**
     * The title of the game, appears at the top of the game window.
     */
    private String title = "New Game";

    /**
     * The author of the game.
     */
    private String author = "John Doe";

    /**
     * The current version of the game.
     */
    private String version = "1.0.0";

    /*
     * Main Objects
     */

    /**
     * Configurations to be used by the game.
     */
    public GameConfig() {

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The game's window width (in pixels).
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width The width of the game's window (in pixels).
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return The game's window height (in pixels).
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height The height of the game's window (in pixels).
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return The title of the game, appears at the top of the game window.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title of the game.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The author of the game.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author The author of the game.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return The current version of the game.
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The current version of the game.
     */
    public void setVersion(String version) {
        this.version = version;
    }

}
