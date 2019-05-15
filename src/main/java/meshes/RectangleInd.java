package meshes;

import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL15.*;

// rectangle with indices
public class RectangleInd {

    public int vbo;
    public int ebo;
    //                          positions        colors          texture coordinates
    public float[] vertices = {-0.5f,-0.5f,0.0f, 1.0f,0.0f,0.0f, 0.0f, 0.0f,
                                0.5f,-0.5f,0.0f, 0.0f,1.0f,0.0f, 1.0f, 0.0f,
                                0.5f, 0.5f,0.0f, 0.0f,0.0f,1.0f, 1.0f, 1.0f,
                               -0.5f, 0.5f,0.0f, 1.0f,1.0f,1.0f, 0.0f, 1.0f};

    public int[] indices = {0, 1, 2,
                            2, 3, 0};

    public RectangleInd() {
        vbo = glGenBuffers();
        ebo = glGenBuffers();
    }

    public void bind(int target, int id) {
        glBindBuffer(target, id);
    }

    public void uploadData(int target, float[] data, int usage) {  //FloatBuffer data
        glBufferData(target, data, usage);
    }

    public void uploadData(int target, int[] data, int usage) {  //IntBuffer data
        glBufferData(target, data, usage);
    }

    public int getVboID() {
        return vbo;
    }

    public void draw() {
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
    }
}
