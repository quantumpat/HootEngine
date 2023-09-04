package org.hootengine.display;

import org.hootengine.input.KeyListener;
import org.hootengine.input.MouseListener;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
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
     * The window instance.
     */
    public static Window instance = null;

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
     * The window saved to memory.
     */
    private long glfwWindow;


    /*
     * Main Objects
     */

    /**
     * Sets the properties of a new game window.
     */
    private Window() {

        width = 1280;
        height = 720;
        title = "New Game";

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
        loop();

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

    }

    /**
     * Starts the window refresh loop
     */
    private void loop() {

        while (!glfwWindowShouldClose(glfwWindow)) {

            //Poll events
            glfwPollEvents();

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);

        }

    }


    /*
     * Getters & Setters
     */

    /**
     * @return Returns the game window instance.
     */
    public static Window get() {

        if (Window.instance == null) {
            Window.instance = new Window();
        }

        return Window.instance;

    }

}
