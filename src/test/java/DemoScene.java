import org.hootengine.cameras.Camera;
import org.hootengine.core.Game;
import org.hootengine.gameobjects.GameObject;
import org.hootengine.gameobjects.sprites.Sprite;
import org.hootengine.gameobjects.sprites.SpriteRenderer;
import org.hootengine.scene.Scene;
import org.hootengine.shaders.Shader;
import org.hootengine.textures.Texture;
import org.hootengine.time.TimeManager;
import org.joml.Vector2f;
import org.joml.Vector4f;
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
        testShader = new Shader("./assets/shaders/defaultTextureShader.glsl");
        testShader.compile();

        testTexture = new Texture("./assets/images/testImage.jpg");

        int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float)(600 - xOffset * 2);
        float totalHeight = (float)(300 - yOffset * 2);
        float sizeX = totalWidth / 100.0f;
        float sizeY = totalHeight / 100.0f;
        float padding = 3;

        for (int x=0; x < 100; x++) {
            for (int y=0; y < 100; y++) {
                float xPos = xOffset + (x * sizeX) + (padding * x);
                float yPos = yOffset + (y * sizeY) + (padding * y);

                Sprite sprite = new Sprite(this, "Patrick", (new Vector4f(xPos / totalWidth, yPos / totalHeight, 1, 1)));
            }
        }

    }

    public void preload() {

    }

    public void create() {

    }

    //Update (looped)
    public void update(float delta) {

        //System.out.println("" + (1.0f / delta) + "FPS");

    }

}
