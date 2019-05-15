package info.shader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.lwjgl.opengl.GL20.*;

public class ShaderLoader {

    private final int id;

    public ShaderLoader(int type) {
        id = glCreateShader(type);
    }

    public void source(CharSequence source) {
        glShaderSource(id, source);
    }

    public void compile() {
        glCompileShader(id);

        checkStatus();
    }

    private void checkStatus() {
        int status = glGetShaderi(id, GL_COMPILE_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(id));
        }
    }

    public void delete() {
        glDeleteShader(id);
    }

    public int getID() {
        return id;
    }

    public static ShaderLoader createShader(int type, CharSequence source) {
        ShaderLoader shader = new ShaderLoader(type);
        shader.source(source);
        shader.compile();

        return shader;
    }

    public static ShaderLoader loadShader(int type, String path) {
        StringBuilder builder = new StringBuilder();

        try (InputStream in = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load a shader file!"
                    + System.lineSeparator() + ex.getMessage());
        }
        CharSequence source = builder.toString();

        return createShader(type, source);
    }
}
