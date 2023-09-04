package org.hootengine.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {

    /*
     * Variables
     */

    /**
     * The key listener instance.
     */
    private static KeyListener instance = null;

    /**
     * Whether a key or multiple keys are up or down.
     */
    private boolean keyPressed[] = new boolean[350];


    /*
     * Main Objects
     */

    /**
     * Sets the properties of the key listener.
     */
    private KeyListener() {}


    /*
     * Callbacks
     */

    /**
     * Checks for any key action.
     *
     * @param window The current game window.
     * @param key The code of the current key action.
     * @param scanCode The scan code.
     * @param action The type of action that's occurring (key up, key down, ...).
     * @param mods The code of any modifier (if any).
     */
    public static void keyCallback(long window, int key, int scanCode, int action, int mods) {

        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        }else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The key listener instance.
     */
    public static KeyListener get() {

        if (KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;

    }

    /**
     * @param keyCode The key code to be checked.
     * @return Whether the key is being pressed or not.
     */
    public static boolean isKeyPressed(int keyCode) {

        if (keyCode < get().keyPressed.length) {
            return get().keyPressed[keyCode];
        }else {
            return false;
        }

    }

}
