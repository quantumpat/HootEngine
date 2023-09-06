package org.hootengine.display;

import org.hootengine.core.Game;
import org.hootengine.input.KeyListener;
import org.hootengine.input.MouseListener;
import org.hootengine.time.TimeManager;
import org.hootengine.util.color.RGBA;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.MemoryUtil;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author Patrick Carroll
 */
public class Window {

    /*
     * Variables
     */

    /**
     * The game object.
     */
    private Game game;

    /**
     * The window instance.
     */
    public static Window instance = null;

    /**
     * The window saved to memory.
     */
    private long glfwWindow;

    /**
     * The width (in pixels) of the window.
     */
    private int width;

    /**
     * The height (in pixels) of the window.
     */
    private int height;

    /**
     * The title of the window.
     */
    private String title;

    /**
     * The background color of the screen (really just the clear color).
     */
    private RGBA backgroundColor = new RGBA(0, 0, 0, 1.0);


    /*
     * Main Objects
     */

    /**
     * Sets the properties of a new game window.
     */
    private Window(Game game) {

        this.game = game;

        width = game.getConfig().getWidth();
        height = game.getConfig().getHeight();
        title = game.getConfig().getTitle();
        backgroundColor = game.getConfig().getBackgroundColor();

    }


    /*
     * Methods
     */

    /**
     * Initializes the window and starts the refresh loop.
     */
    public void run() {
        System.out.println("Window created using LWJGL version " + Version.getVersion() + "!");

        init();
        startLoop();

        //Free the memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        //Terminate GLFW and the free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    /**
     * Creates a game window and sets the callbacks.
     */
    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GL_TRUE);

        //Needed for macos
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);

        //glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        //glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        //glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //Create the window
        glfwWindow = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (glfwWindow == MemoryUtil.NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        //Set mouse callbacks
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);

        //Set key callbacks
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        //Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        //Enable v-sync
        glfwSwapInterval(1);

        //Show window
        glfwShowWindow(glfwWindow);

        //IMPORTANT!
        GL.createCapabilities();
        GLUtil.setupDebugMessageCallback();

        if (game.getSceneManager().getCurrentGameScene() != null)
            game.getSceneManager().getCurrentGameScene().start();

    }

    /**
     * Start the window refresh loop.
     */
    public void startLoop() {

        float beginTime = TimeManager.getTime();
        float endTime;
        float delta = -1.0f;

        while (!glfwWindowShouldClose(glfwWindow)) {
            //Poll events
            glfwPollEvents();

            glClearColor(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha());
            glClear(GL_COLOR_BUFFER_BIT);

            //Update the scenes
            if (delta >= 0)
                game.getSceneManager().update(delta);

            glfwSwapBuffers(glfwWindow);

            endTime = TimeManager.getTime();
            delta = endTime - beginTime;
            beginTime = endTime;

        }

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The game object.
     */
    public Game getGame() {
        return game;
    }

    /**
     * @return The game window instance.
     */
    public static Window get(Game game) {

        if (Window.instance == null) {
            Window.instance = new Window(game);
        }

        return Window.instance;

    }

    /**
     * @return The window saved to memory.
     */
    public long getGlfwWindow() {
        return glfwWindow;
    }

    /**
     * @return The width (in pixels) of the window.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height (in pixels) of the window.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The title of the window.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The background color of the screen (really just the clear color).
     */
    public RGBA getBackgroundColor() {
        return backgroundColor;
    }

}
