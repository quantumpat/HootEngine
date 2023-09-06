import org.hootengine.cameras.Camera;
import org.hootengine.core.Game;
import org.hootengine.scene.Scene;
import org.hootengine.shaders.Shader;
import org.hootengine.textures.Texture;
import org.hootengine.time.TimeManager;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class DemoScene extends Scene {

    /*
     * Variables
     */
    private Camera camera;
    private Shader testShader;
    private Texture testTexture;

    private float[] vertexArray = {
            // position               // color
            100.5f, -0.5f, 0.0f,       1.0f, 0.0f, 0.0f, 1.0f,   1, 1, // Bottom right 0
            -0.5f,  100.5f, 0.0f,       0.0f, 1.0f, 0.0f, 1.0f,  0, 0, // Top left     1
            100.5f,  100.5f, 0.0f ,      1.0f, 0.0f, 1.0f, 1.0f, 1, 0, // Top right    2
            -0.5f, -0.5f, 0.0f,       1.0f, 1.0f, 0.0f, 1.0f,    0, 1  // Bottom left  3
    };

    //IMPORTANT: Must be in counter-clockwise order
    private int[] elementArray = {
            /*
                    x        x


                    x        x
             */
            2, 1, 0, // Top right triangle
            0, 1, 3 // bottom left triangle
    };

    private int vaoId, vboId, eboId;


    /*
     * Main Object
     */
    public DemoScene(Game game) {
        super(game, "DemoScene");
    }


    /*
     * Methods
     */
    public void init() {

        camera = new Camera(new Vector2f(0, 0));
        testShader = new Shader("./assets/shaders/defaultTextureShader.glsl");
        testShader.compile();

        testTexture = new Texture("./assets/images/testImage.jpg");


        // ============================================================
        // Generate VAO, VBO, and EBO buffer objects, and send to GPU
        // ============================================================
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // Create a float buffer of vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        // Create VBO upload the vertex buffer
        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create the indices and upload
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboId = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add the vertex attribute pointers
        int positionsSize = 3;
        int colorSize = 4;
        int uvSize = 2;
        int vertexSizeBytes = (positionsSize + colorSize + uvSize) * Float.BYTES;
        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionsSize + colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);

    }

    public void preload() {

    }

    public void create() {

    }

    //Update (looped)
    public void update(float delta) {

        camera.position.x -= 1;
        camera.position.y -= 1;

        //System.out.println("" + (1.0f / delta) + "FPS");

        testShader.use();

        testShader.uploadTexture("TEXTURE_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0);
        testTexture.bind();

        testShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        testShader.uploadMat4f("uView", camera.getViewMatrix());
        testShader.uploadFloat("uTime", TimeManager.getTime());

        // Bind the VAO that we're using
        glBindVertexArray(vaoId);

        // Enable the vertex attribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        // Unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        testShader.detach();

    }

}
