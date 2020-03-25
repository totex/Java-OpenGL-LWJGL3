package meshes;


import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL15.*;

// rcube with indices
public class Cube {

    public int vbo;
    public int ebo;
    //                          positions        colors          texture coordinates
    public float[] vertices = {-0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 0.0f,  0.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 1.0f, 0.0f,  1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.0f, 0.0f, 1.0f,  1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  0.0f, 1.0f,

            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,  0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 0.0f,  1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,  1.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f, 1.0f,  0.0f, 1.0f,

            0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,  0.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 1.0f, 0.0f,  1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.0f, 0.0f, 1.0f,  1.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  0.0f, 1.0f,

            -0.5f,  0.5f, -0.5f,  1.0f, 0.0f, 0.0f,  0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 0.0f,  1.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f, 1.0f,  1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  0.0f, 1.0f,

            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,  0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 0.0f,  1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 0.0f, 1.0f,  1.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  0.0f, 1.0f,

            0.5f,  0.5f, -0.5f,  1.0f, 0.0f, 0.0f,  0.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f, 0.0f,  1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  0.0f, 0.0f, 1.0f,  1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  0.0f, 1.0f};

    public int[] indices = {0,  1,  2,  2,  3,  0,
            4,  5,  6,  6,  7,  4,
            8,  9, 10, 10, 11,  8,
            12, 13, 14, 14, 15, 12,
            16, 17, 18, 18, 19, 16,
            20, 21, 22, 22, 23, 20};

    public Cube() {
        vbo = glGenBuffers();
        ebo = glGenBuffers();

        bind(GL_ARRAY_BUFFER, vbo);
        uploadData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        bind(GL_ELEMENT_ARRAY_BUFFER, ebo);
        uploadData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

//        bind(GL_ELEMENT_ARRAY_BUFFER, 0);
//        bind(GL_ARRAY_BUFFER, 0);
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
//        bind(GL_ARRAY_BUFFER, vbo);
//        bind(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
//        bind(GL_ELEMENT_ARRAY_BUFFER, 0);
//        bind(GL_ARRAY_BUFFER, 0);
    }
}

