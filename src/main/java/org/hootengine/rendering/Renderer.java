package org.hootengine.rendering;

import org.hootengine.gameobjects.sprites.Sprite;
import org.hootengine.gameobjects.sprites.SpriteRenderer;
import org.hootengine.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private Scene scene;
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batches;

    public Renderer(Scene scene) {

        scene = scene;

        batches = new ArrayList<>();

    }

    public void addSprite(Sprite sprite) {

        SpriteRenderer spr = sprite.getSpriteRenderer();
        if (spr != null) {
            addSpriteRenderer(spr);
        }

    }

    private void addSpriteRenderer(SpriteRenderer spriteRenderer) {

        boolean added = false;
        for (RenderBatch batch: batches) {
            if (batch.getHasRoom()) {
                batch.addSpriteRenderer(spriteRenderer);
                added = true;
                break;
            }
        }

        if (!added) {
            RenderBatch newBatch = new RenderBatch(scene, MAX_BATCH_SIZE);
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSpriteRenderer(spriteRenderer);
        }

    }

    public void render() {
        for (RenderBatch batch: batches) {
            batch.render();
        }
    }

}
