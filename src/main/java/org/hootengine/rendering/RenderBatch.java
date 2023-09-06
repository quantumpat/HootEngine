package org.hootengine.rendering;

import org.hootengine.gameobjects.sprites.SpriteRenderer;
import org.hootengine.scene.Scene;
import org.hootengine.shaders.Shader;
import org.hootengine.time.TimeManager;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class RenderBatch {

    private Scene scene;

    // Vertex
    // ======
    // Pos                  Color
    // float, float,        float, float, float, float

    private final int POS_SIZE = 2;
    private final int COLOR_SIZE = 4;

    private final int POS_OFFSET = 0;
    private final int COLOR_OFFSET = POS_OFFSET + POS_SIZE * Float.BYTES;
    private final int VERTEX_SIZE = 6;
    private final int VERTEX_SIZE_BYTES = VERTEX_SIZE * Float.BYTES;

    private SpriteRenderer[] sprites;
    private int numSprites;
    private boolean hasRoom;
    private float[] vertices;

    private int vaoId, vboId;
    private int maxBatchSize;
    private Shader shader;


    public RenderBatch(Scene scene, int maxBatchSize) {

        this.scene = scene;

        shader = new Shader("./assets/shaders/defaultTextureShader.glsl");
        shader.compile();

        sprites = new SpriteRenderer[maxBatchSize];
        maxBatchSize = maxBatchSize;

        vertices = new float[maxBatchSize * 4 * VERTEX_SIZE];

        numSprites = 0;
        hasRoom = true;

    }

    public void start() {

        //Generate and bind a vertex array object
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        //Allocate space for vertices
        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertices.length * Float.BYTES, GL_DYNAMIC_DRAW);

        //Create and upload indicies buffer
        int eboId = glGenBuffers();
        int[] indices = generateIndices();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        //Enable the buffer attribute pointers
        glVertexAttribPointer(0, POS_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, POS_OFFSET);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, COLOR_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, COLOR_OFFSET);
        glEnableVertexAttribArray(1);

    }

    public void render() {

        //For now, we will re-buffer all data every frame.
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);

        //Use shader
        shader.use();
        shader.uploadMat4f("uProjection", scene.getDefaultCamera().getProjectionMatrix());
        shader.uploadMat4f("uView", scene.getDefaultCamera().getViewMatrix());
        shader.uploadFloat("uTime", TimeManager.getTime());

        //Draw
        glDrawElements(GL_TRIANGLES, numSprites * 6, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        shader.detach();

    }

    private void loadVertexProperties(int index) {

        SpriteRenderer spriteRenderer = sprites[index];

        //Find offset within array (4 vertices per sprite)
        int offset = index * 4 * VERTEX_SIZE;

        Vector4f color = spriteRenderer.getColor();

        //Add vertices with appropriate properties
        float xAdd = 1.0f;
        float yAdd = 1.0f;

        for (int i = 0; i < 4; i++) {

            if (i == 1) {
                yAdd = 0.0f;
            }else if (i == 2) {
                xAdd = 0.0f;
            }else if (i == 3) {
                yAdd = 1.0f;
            }

            //Load position
            vertices[offset] = spriteRenderer.sprite.transform.position.x + (xAdd * spriteRenderer.sprite.transform.scale.x);
            vertices[offset + 1] = spriteRenderer.sprite.transform.position.y + (yAdd * spriteRenderer.sprite.transform.scale.y);

            //Load color
            vertices[offset + 2] = color.x;
            vertices[offset + 3] = color.y;
            vertices[offset + 4] = color.z;
            vertices[offset + 5] = color.w;

            offset += VERTEX_SIZE;

        }

    }

    private int[] generateIndices() {

        //6 indices per quad (3 per triangle)
        int[] elements = new int[6 * maxBatchSize];
        for (int i = 0; i < maxBatchSize; i++) {
            loadElementIndices(elements, i);
        }

        return elements;

    }

    private void loadElementIndices(int[] elements, int index) {

        int offsetArrayIndex = 6 * index;
        int offset = 4 * index;

        //Triangle 1
        elements[offsetArrayIndex] = offset + 3;
        elements[offsetArrayIndex + 1] = offset + 2;
        elements[offsetArrayIndex + 2] = offset + 0;

        //Triangle 2
        elements[offsetArrayIndex + 3] = offset + 0;
        elements[offsetArrayIndex + 4] = offset + 2;
        elements[offsetArrayIndex + 5] = offset + 1;

    }

    public void addSpriteRenderer(SpriteRenderer spriteRenderer) {

        //Get index and add render object
        int index = numSprites;
        sprites[index] = spriteRenderer;
        numSprites++;

        //Add properties to local vertices array
        loadVertexProperties(index);

        if (numSprites >= maxBatchSize) {
            hasRoom = false;
        }

    }


    public boolean getHasRoom() {
        return hasRoom;
    }

}
