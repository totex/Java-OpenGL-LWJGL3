package meshes;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_STRIP;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

public class Rectangle {
    private int id;
    //                          positions        colors
    public float[] vertices = {-0.5f,-0.5f,0.0f, 1.0f,0.0f,0.0f,
                                0.5f,-0.5f,0.0f, 0.0f,1.0f,0.0f,
                               -0.5f, 0.5f,0.0f, 0.0f,0.0f,1.0f,
                                0.5f, 0.5f,0.0f, 1.0f,1.0f,1.0f};

    public Rectangle() {
        id = glGenBuffers();
    }

    public void bind(int target) {
        glBindBuffer(target, id);
    }

    public void uploadData(int target, float[] data, int usage) {  //FloatBuffer data
        glBufferData(target, data, usage);
    }

    public int getID() {
        return id;
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    }
}
