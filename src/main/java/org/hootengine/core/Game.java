package org.hootengine.core;

import org.hootengine.display.Window;

public class Game {

    /*
     * Variables
     */
    private Window window;


    /*
     * Main Objects
     */
    public Game() {

    }


    /*
     * Methods
     */

    public void start() {

        init();

    }

    private void init() {

        window = Window.get();

    }


    /*
     * Getters & Setters
     */
    public Window getWindow() {
        return window;
    }

}
