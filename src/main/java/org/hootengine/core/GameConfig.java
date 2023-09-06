package org.hootengine.core;

import org.hootengine.util.color.RGBA;

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

    /**
     * The background color of the screen (really just the clear color).
     */
    private RGBA backgroundColor = new RGBA(0, 0, 0, 1.0);

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
     * @param width The width of the game's window (in pixels).
     * @param height The height of the game's window (in pixels).
     */
    public void setSize(int width, int height) {
        this.width = width;
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

    /**
     * @return The background color of the screen (really just the clear color).
     */
    public RGBA getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor The background color of the screen (really just the clear color).
     */
    public void setBackgroundColor(RGBA backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @param red The red value.
     * @param green The green value.
     * @param blue The blue value.
     * @param alpha The alpha or opacity value.
     */
    public void setBackgroundColor(float red, float green, float blue, float alpha) {
        backgroundColor = new RGBA(red, green, blue, alpha);
    }

    /**
     * @param red The red value.
     * @param green The green value.
     * @param blue The blue value.
     * @param alpha The alpha or opacity value.
     */
    public void setBackgroundColor(int red, int green, int blue, double alpha) {
        backgroundColor = new RGBA(red, green, blue, alpha);
    }

}
