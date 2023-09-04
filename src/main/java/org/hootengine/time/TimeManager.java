package org.hootengine.time;

public class TimeManager {

    /*
     * Variables
     */

    /**
     * The time in which the game started.
     */
    public static float timeStarted = System.nanoTime();


    /*
     * Getters & Setters
     */

    /**
     * @return Returns the current time.
     */
    public static float getTime() {
        return (float) ((System.nanoTime() - timeStarted) * 1E-9);
    }

}
