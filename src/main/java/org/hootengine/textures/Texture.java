package org.hootengine.textures;

import org.lwjgl.BufferUtils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture {

    /*
     * Variables
     */

    /**
     * The file path.
     */
    private String path;

    /**
     * The texture id saved to memory.
     */
    private int textureId;


    /*
     * Main Object
     */

    /**
     * A 2D image to be used by the GPU.
     *
     * @param path The file path.
     */
    public Texture(String path) {

        this.path = path;

        //Generate texture on GPU
        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        /*
         * Set texture parameters
         */
        //Repeat the image
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        //When stretching (pixelate)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        //When shrinking (pixelate)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        /*
         * Load image
         */
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(path, width, height, channels, 0);

        //Add image to GPU
        if (image != null) {
            if (channels.get(0) == 3)
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(0),
                        height.get(0), 0, GL_RGB, GL_UNSIGNED_BYTE, image);
            else if (channels.get(0) == 4)
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0),
                        height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            else
                assert false : "ERROR: (Texture) Unknown number of channels '" + channels.get(0) + " while trying to upload file: '" + path + "'";
        }else
            assert false : "ERROR: (Texture) Could not load image '" + path + "'";

        stbi_image_free(image);

    }


    /*
     * Methods
     */

    /**
     * Bind the texture.
     */
    public void bind() {

        glBindTexture(GL_TEXTURE_2D, textureId);

    }

    /**
     * Unbind the texture.
     */
    public void unbind() {

        glBindTexture(GL_TEXTURE_2D, 0);

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The file path.
     */
    public String getPath() {
        return path;
    }

    /**
     * @return The texture id saved to memoery.
     */
    public int getTextureId() {
        return textureId;
    }

}
