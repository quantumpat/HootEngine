package org.hootengine.gameobjects.sprites;

import org.hootengine.gameobjects.GameObject;
import org.joml.Vector4f;

public class SpriteRenderer {

    /*
     * Variables
     */

    public Sprite sprite;

    /**
     * The color.
     */
    private Vector4f color;


    /*
     * Main Objects
     */

    /**
     * Renders sprites.
     *
     * @param color The color.
     */
    public SpriteRenderer(Sprite sprite, Vector4f color) {

        this.sprite = sprite;
        this.color = color;

    }



    public void start() {

    }

    /**
     * Updates the sprite renderer (looped).
     *
     * @param delta The time difference.
     */
    public void update(float delta) {

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The sprite to be rendered.
     */
    public Sprite getGameObject() {
        return sprite;
    }

    /**
     * @return The color.
     */
    public Vector4f getColor() {
        return color;
    }

}
