package org.hootengine.gameobjects.sprites;

import org.hootengine.gameobjects.GameObject;
import org.hootengine.scene.Scene;
import org.joml.Vector4f;

public class Sprite extends GameObject {

    /*
     * Variables
     */
    private SpriteRenderer spriteRenderer;


    /*
     * Main Objects
     */

    /**
     * A sprite displaying an image or spritesheet to the screen.
     *
     * @param scene The scene the sprite should be added to.
     * @param name The name of the sprite.
     */
    public Sprite(Scene scene, String name, Vector4f vector) {

        super(scene, name);

        spriteRenderer = new SpriteRenderer(this, vector);

    }


    /*
     * Methods
     */

    public void start() {

        spriteRenderer.start();

    }

    /**
     * Updates the sprite.
     *
     * @param delta The time difference.
     */
    @Override
    public void update(float delta) {

    }

    /*
     * Getters & Setters
     */
    public SpriteRenderer getSpriteRenderer() {
        return spriteRenderer;
    }


}
