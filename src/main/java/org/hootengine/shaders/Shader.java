package org.hootengine.shaders;

import org.joml.*;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class Shader {

    /*
     * Variables
     */

    /**
     * The shader file path.
     */
    private String path;

    /**
     * The id of the shader program.
     */
    private int shaderProgramId;

    /**
     * The source of vertex shader.
     */
    private String vertexSource;

    /**
     * The source of fragment shader.
     */
    private String fragmentSource;

    /**
     * Whether the shader is being used or not.
     */
    private boolean isUsed = false;


    /*
     * Main Objects
     */

    /**
     * A open gl shader.
     * @param path The shader file path.
     */
    public Shader(String path) {

        this.path = path;

        //Load the file and set vertex & fragment source
        try {

            String source = new String(Files.readAllBytes(Paths.get(path)));

            //Split the string into each '#type'
            String[] splitString = source.split("(#type)( )+([a-zA-Z]+)");

            //Find the first pattern after (#type 'pattern')
            int index = source.indexOf("#type") + 6;
            int endOfLine = source.indexOf("\n", index);
            String firstPattern = source.substring(index, endOfLine).trim();

            //Find the second pattern after (#type 'pattern')
            index = source.indexOf("#type", endOfLine) + 6;
            endOfLine = source.indexOf("\n", index);
            String secondPattern = source.substring(index, endOfLine).trim();

            if (firstPattern.equals("vertex"))
                vertexSource = splitString[1];
            else if (firstPattern.equals("fragment"))
                fragmentSource = splitString[1];
            else
                throw new IOException("Unexpected token '" + firstPattern + "'");


            if (secondPattern.equals("vertex"))
                vertexSource = splitString[2];
            else if (secondPattern.equals("fragment"))
                fragmentSource = splitString[2];
            else
                throw new IOException("Unexpected token '" + secondPattern + "'");

        }catch (IOException exception) {

            exception.printStackTrace();
            assert false : "Error: Could not open file for shader: '" + path + "'";

        }

    }


    /*
     * Methods
     */

    /**
     * Compiles the shader.
     */
    public void compile() {

        int vertexId, fragmentId;

        /*
         * Vertex
         */
        vertexId = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexId, vertexSource);
        glCompileShader(vertexId);

        //Check for errors
        int success = glGetShaderi(vertexId, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexId, GL_INFO_LOG_LENGTH);
            System.out.println("Error: '" + path + "' (Vertex) compilation failed!");
            System.out.println(glGetShaderInfoLog(vertexId, len));
            assert false : "";
        }

        /*
         * Fragment
         */
        fragmentId = glCreateShader(GL_FRAGMENT_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(fragmentId, fragmentSource);
        glCompileShader(fragmentId);

        // Check for errors in compilation
        success = glGetShaderi(fragmentId, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentId, GL_INFO_LOG_LENGTH);
            System.out.println("Error: '" + path + "' (Fragment) compilation failed!");
            System.out.println(glGetShaderInfoLog(fragmentId, len));
            assert false : "";
        }

        link(vertexId, fragmentId);

    }

    /**
     * Links the vertex and fragment shader to the shader program.
     *
     * @param vertexId The vertex shader id.
     * @param fragmentId The fragment shader id.
     */
    public void link(int vertexId, int fragmentId) {

        shaderProgramId = glCreateProgram();
        glAttachShader(shaderProgramId, vertexId);
        glAttachShader(shaderProgramId, fragmentId);
        glLinkProgram(shaderProgramId);

        // Check for linking errors
        int success = glGetProgrami(shaderProgramId, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgramId, GL_INFO_LOG_LENGTH);
            System.out.println("Error: '" + path + "'\n\tLinking of shaders failed.");
            System.out.println(glGetProgramInfoLog(shaderProgramId, len));
            assert false : "";
        }

    }

    /**
     * Use the shader.
     */
    public void use() {

        if (isUsed)
            return;

        glUseProgram(shaderProgramId);
        isUsed = true;

    }

    /**
     * Detach the shader.
     */
    public void detach() {

        if (!isUsed)
            return;

        glUseProgram(0);
        isUsed = false;

    }

    /**
     * Upload camera matrix.
     *
     * @param name The name of projection variable
     * @param matrix The projection matrix
     */
    public void uploadMat3f(String name, Matrix3f matrix) {

        int location = glGetUniformLocation(shaderProgramId, name);

        use();

        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
        matrix.get(matBuffer);
        glUniformMatrix3fv(location, false, matBuffer);

    }

    /**
     * Upload camera matrix.
     *
     * @param name The name of projection variable
     * @param matrix The projection matrix
     */
    public void uploadMat4f(String name, Matrix4f matrix) {

        int location = glGetUniformLocation(shaderProgramId, name);

        use();

        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        matrix.get(matBuffer);
        glUniformMatrix4fv(location, false, matBuffer);

    }

    /**
     * Upload 2D-Vector.
     *
     * @param name The name of the shader variable.
     * @param vec The value to be set.
     */
    public void uploadVec2f(String name, Vector2f vec) {

        int location = glGetUniformLocation(shaderProgramId, name);

        use();

        glUniform2f(location, vec.x, vec.y);

    }

    /**
     * Upload 3D-Vector.
     *
     * @param name The name of the shader variable.
     * @param vec The value to be set.
     */
    public void uploadVec3f(String name, Vector3f vec) {

        int location = glGetUniformLocation(shaderProgramId, name);

        use();

        glUniform3f(location, vec.x, vec.y, vec.z);

    }

    /**
     * Upload 4D-Vector.
     *
     * @param name The name of the shader variable.
     * @param vec The value to be set.
     */
    public void uploadVec4f(String name, Vector4f vec) {

        int location = glGetUniformLocation(shaderProgramId, name);

        use();

        glUniform4f(location, vec.x, vec.y, vec.z, vec.w);

    }

    /**
     * Upload a float value to the shader.
     *
     * @param name The name of the shader variable.
     * @param value The value to be set.
     */
    public void uploadFloat(String name, float value) {

        int location = glGetUniformLocation(shaderProgramId, name);

        use();

        glUniform1f(location, value);

    }

    /**
     * Upload a int value to the shader.
     *
     * @param name The name of the shader variable.
     * @param value The value to be set.
     */
    public void uploadInt(String name, int value) {

        int location = glGetUniformLocation(shaderProgramId, name);

        use();

        glUniform1i(location, value);

    }

    /**
     * Upload a texture to the shader.
     *
     * @param name The name of the shader variable.
     * @param slot The texture slot value.
     */
    public void uploadTexture(String name, int slot) {

        int location = glGetUniformLocation(shaderProgramId, name);

        use();

        glUniform1i(location, slot);

    }


    /*
     * Getters & Setters
     */

    /**
     * @return The shader file path.
     */
    public String getPath() {
        return path;
    }

    /**
     * @return The id of the shader program.
     */
    public int getShaderProgramId() {
        return shaderProgramId;
    }

    /**
     * @return The source of vertex shader.
     */
    public String getVertexSource() {
        return vertexSource;
    }

    /**
     * @return The source of fragment shader.
     */
    public String getFragmentSource() {
        return fragmentSource;
    }

    /**
     * @return Whether the shader is being used or not.
     */
    public boolean getUsed() {
        return isUsed;
    }

}
