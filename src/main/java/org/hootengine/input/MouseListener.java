package org.hootengine.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {

    /*
     * Variables
     */

    /**
     * The mouse listener instance.
     */
    public static MouseListener instance = null;

    /**
     * The current x-position of the mouse.
     */
    private double x;

    /**
     * The current y-position of the mouse.
     */
    private double y;

    /**
     * The last x-position of the mouse.
     */
    private double lastX;

    /**
     * The last y-position of the mouse.
     */
    private double lastY;

    /**
     * The scroll-x property of the mouse.
     */
    private double scrollX;

    /**
     * The scroll-y property of the mouse.
     */
    private double scrollY;

    /**
     * Whether a or multiple mouse buttons are being pressed.
     */
    private boolean mouseButtonPressed[] = new boolean[3];

    /**
     * Is the mouse being dragged.
     */
    private boolean isDragging;


    /*
     * Main Objects
     */

    /**
     * Sets the properties of the mouse listener.
     */
    private MouseListener() {

        scrollX = 0.0;
        scrollY = 0.0;
        x = 0.0;
        y = 0.0;
        lastX = 0.0;
        lastY = 0.0;

    }


    /*
     * Callbacks
     */

    /**
     * Checks if the mouse position has changed.
     *
     * @param window The current game window.
     * @param x The current x-position of the mouse.
     * @param y The current y-position of the mouse.
     */
    public static void mousePosCallback(long window, double x, double y) {

        //Set last x and y to new x and y
        get().lastX = get().x;
        get().lastY = get().y;

        //Set the new x and y
        get().x = x;
        get().y = y;

        //Check to see if mouse is dragging
        get().isDragging = get().mouseButtonPressed[0] ||
                get().mouseButtonPressed[1] || get().mouseButtonPressed[2];

    }

    /**
     * Checks if a mouse button state has changed.
     *
     * @param window The current game window.
     * @param button The mouse button (0 = left, 1 = right, 2 = middle).
     * @param action The type of action that's occurring (press, release, ...).
     * @param mods The code of any modifier (if any).
     */
    public static void mouseButtonCallback(long window, int button, int action, int mods) {

        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        }else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }

    }

    /**
     * Checks if a mouse scroll state has changed.
     *
     * @param window The current game window.
     * @param xOffset The scroll-x value.
     * @param yOffset The scroll-y value.
     */
    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {

        get().scrollX = xOffset;
        get().scrollY = yOffset;

    }

    /**
     * Resets the mouse properties
     */
    public static void endFrame() {

        get().scrollX = 0.0;
        get().scrollY = 0.0;
        get().lastX = get().x;
        get().lastY = get().y;

    }


    /*
     * Getters & Setters
     */

    /**
     * @return Returns the mouse listener.
     */
    public static MouseListener get() {

        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;

    }

    /**
     * @return Returns the current x-position of the mouse.
     */
    public static float getX() {
        return (float) get().x;
    }

    /**
     * @return Returns the current y-position of the mouse.
     */
    public static float getY() {
        return (float) get().y;
    }

    /**
     * @return Returns the last x-position of the mouse.
     */
    public static float getLastX() {
        return (float) get().lastX;
    }

    /**
     * @return Returns the last y-position of the mouse.
     */
    public static float getLastY() {
        return (float) get().lastY;
    }

    /**
     * @return Returns the change in x-position of the mouse.
     */
    public static float getDX() {
        return (float) (get().lastX - get().x);
    }

    /**
     * @return Returns the change in y-position of the mouse.
     */
    public static float getDY() {
        return (float) (get().lastY - get().y);
    }

    /**
     * @return Returns the scroll-x value.
     */
    public static float getScrollX() {
        return (float) get().scrollX;
    }

    /**
     * @return Returns the scroll-y value.
     */
    public static float getScrollY() {
        return (float) get().scrollY;
    }

    /**
     * @return Returns whether a mouse button is down or not.
     * @param button The mouse button to be checked (0 = left, 1 = right, 2 = middle).
     */
    public static boolean mouseButtonDown(int button) {

        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        }else {
            return false;
        }

    }

    /**
     * @return Whether the mouse is dragging or not.
     */
    public static boolean isDragging() {
        return get().isDragging;
    }

}
