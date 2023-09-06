package org.hootengine.cameras;

import org.graalvm.compiler.lir.amd64.vector.AMD64VectorMove;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {

    /*
     * Variables
     */

    /**
     * The projection matrix.
     */
    private Matrix4f projectionMatrix;

    /**
     * The view matrix.
     */
    private Matrix4f viewMatrix;

    /**
     * The x and y position.
     */
    public Vector2f position;


    /*
     * Main Objects
     */

    /**
     * A camera to view game objects.
     *
     * @param position The x and y position.
     */
    public Camera(Vector2f position) {

        this.position = position;

        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();

        adjustProjection();

    }


    /*
     * Methods
     */

    /**
     * Adjust the projection for 2D space.
     */
    public void adjustProjection() {

        projectionMatrix.identity();
        projectionMatrix.ortho(0.0f, 32.0f * 40.0f, 0.0f, 32.0f * 21.0f, 0.0f, 100.0f);

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The view matrix.
     */
    public Matrix4f getViewMatrix() {

        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
        viewMatrix.identity();
        viewMatrix = viewMatrix.lookAt(new Vector3f(position.x, position.y, 20.0f), cameraFront.add(position.x, position.y, 0.0f), cameraUp);

        return viewMatrix;

    }

    /**
     * @return The projection matrix.
     */
    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    /**
     * @return The x and y position.
     */
    public Vector2f getPosition() {
        return position;
    }

    /**
     * @return The x position
     */
    public float getX() {
        return position.x;
    }

    /**
     * @return The y position.
     */
    public float getY() {
        return position.y;
    }

}
