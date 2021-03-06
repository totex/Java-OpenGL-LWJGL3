package meshes;

import static org.lwjgl.opengl.GL15.*;
//import java.nio.FloatBuffer;
//import java.nio.IntBuffer;


public class Triangle {
	
	private int vbo;
								//positions      colors
	public float[] vertices = {-0.5f,-0.5f,0.0f, 1.0f,0.0f,0.0f,  0.0f, 0.0f,
								0.5f,-0.5f,0.0f, 0.0f,1.0f,0.0f,  1.0f, 0.0f,
								0.0f, 0.5f,0.0f, 0.0f,0.0f,1.0f,  1.0f, 1.0f};
	
	public Triangle() {
        vbo = glGenBuffers();
		bind(GL_ARRAY_BUFFER);
		uploadData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }
	
	public void bind(int target) {
        glBindBuffer(target, vbo);
    }
	
	public void uploadData(int target, float[] data, int usage) {  //FloatBuffer data
        glBufferData(target, data, usage);
    }
	
	public int getID() {
        return vbo;
    }
	
	public void draw() {
		glDrawArrays(GL_TRIANGLES, 0, 3);
	}
}
