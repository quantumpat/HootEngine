package org.hootengine.util.color;

public class RGBA {

    /*
     * Variables
     */

    /**
     * The red value.
     */
    private float red;

    /**
     * The green value.
     */
    private float green;

    /**
     * The blue value.
     */
    private float blue;

    /**
     * The alpha or opacity value.
     */
    private float alpha;


    /*
     * Main Objects
     */

    /**
     * A rgb color including alpha (float form).
     *
     * @param red The red value.
     * @param green The green value.
     * @param blue The blue value.
     * @param alpha The alpha or opacity value.
     */
    public RGBA(float red, float green, float blue, float alpha) {

        this.red = red;
        this.green = green;
        this.blue = blue;

        this.alpha = alpha;

    }

    /**
     * A rgb color including alpha (int form).
     *
     * @param red The red value.
     * @param green The green value.
     * @param blue The blue value.
     * @param alpha The alpha or opacity value.
     */
    public RGBA(int red, int green, int blue, double alpha) {

        this.red = (float) red / (float) 255;
        this.green = (float) green / (float) 255;
        this.blue = (float) blue / (float) 255;

        this.alpha = (float) alpha;

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The red value.
     */
    public float getRed() {
        return red;
    }

    /**
     * @return The green value.
     */
    public float getGreen() {
        return green;
    }

    /**
     * @return The blue value.
     */
    public float getBlue() {
        return blue;
    }

    /**
     * @return The alpha or opacity value.
     */
    public float getAlpha() {
        return alpha;
    }

}
