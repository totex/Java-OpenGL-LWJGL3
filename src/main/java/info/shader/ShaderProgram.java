package info.shader;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
	
	private final int id;

	public ShaderProgram() {
        id = glCreateProgram();
    }
	
	public void attachShader(ShaderLoader shader) {
        glAttachShader(id, shader.getID());
    }
	
	public void link() {
        glLinkProgram(id);

        checkStatus();
    }
	
	public void use() {
        glUseProgram(id);
    }
	
	public void checkStatus() {
        int status = glGetProgrami(id, GL_LINK_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(id));
        }
    }
	
	public void delete() {
        glDeleteProgram(id);
    }
	
	public int getAttributeLocation(CharSequence name) {
        return glGetAttribLocation(id, name);
    }
	
	public void enableVertexAttribute(int location) {
        glEnableVertexAttribArray(location);
    }
	
	public void pointVertexAttribute(int location, int size, int stride, int offset) {
        glVertexAttribPointer(location, size, GL_FLOAT, false, stride, offset);
    }
}
