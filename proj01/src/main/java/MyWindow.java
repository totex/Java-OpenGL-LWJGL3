import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.*;

import meshes.Cube;
import meshes.Triangle;
import org.joml.Vector3f;
import org.lwjgl.opengl.*;

import info.shader.*;
//import meshes.Triangle;
//import meshes.Rectangle;
import meshes.RectangleInd;
import textures.Texture;
//import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;


public class MyWindow {
    private long window;
    private Triangle triangle;
//    private Rectangle rectangle;
//      private RectangleInd rectangle;
      private Cube cube;
      private static ShaderProgram program;
      private int model_loc;
      private static int view_projection_loc;
//      Matrix4f mat;

    private void init(){
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Create the window
        window = glfwCreateWindow(1280, 720, "My window", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        glfwSetWindowPos(window, 400, 200);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // set window resize callback
        glfwSetWindowSizeCallback(window, MyWindow::WindowResize);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        ShaderLoader vertexShader, fragmentShader;

        String shader_src_path = "src\\main\\resources\\shaders\\";

        vertexShader = ShaderLoader.loadShader(GL_VERTEX_SHADER, shader_src_path+"vert1.glsl");
        fragmentShader = ShaderLoader.loadShader(GL_FRAGMENT_SHADER, shader_src_path+"frag1.glsl");

        program = new ShaderProgram();
        program.attachShader(vertexShader); // attach the already compiled vertex shader to the program
        program.attachShader(fragmentShader); // attach the already compiled fragment shader to the program

        program.link();

        vertexShader.delete(); // we can delete the shader sources, the program does not need it anymore
        fragmentShader.delete();



        // creating a simple rectangle
//        rectangle = new Rectangle();
//        rectangle.bind(GL_ARRAY_BUFFER);
//        rectangle.uploadData(GL_ARRAY_BUFFER, rectangle.vertices, GL_STATIC_DRAW);

        // creating a simple rectangle using indices
//        rectangle = new RectangleInd();
//        rectangle.bind(GL_ARRAY_BUFFER, rectangle.vbo);
//        rectangle.uploadData(GL_ARRAY_BUFFER, rectangle.vertices, GL_STATIC_DRAW);
//
//        rectangle.bind(GL_ELEMENT_ARRAY_BUFFER, rectangle.ebo);
//        rectangle.uploadData(GL_ELEMENT_ARRAY_BUFFER, rectangle.indices, GL_STATIC_DRAW);

        // creating a simple triangle
        triangle = new Triangle();

        // creating a Cube
        cube = new Cube();


        int pos = program.getAttributeLocation("position");
        program.enableVertexAttribute(pos);
        program.pointVertexAttribute(pos, 3, 32, 0);

        int color = program.getAttributeLocation("color");
        program.enableVertexAttribute(color);
        program.pointVertexAttribute(color, 3, 32, 12);

        int tex_coords = program.getAttributeLocation("inTexCoords");
        program.enableVertexAttribute(tex_coords);
        program.pointVertexAttribute(tex_coords, 2, 32, 24);

        model_loc = program.getUniformLocation("model");
        view_projection_loc = program.getUniformLocation("view_projection");

        Texture tex = Texture.loadTexture("src/main/resources/images/smiley.png");

        program.use();

        glEnable(GL_DEPTH_TEST);
    }

    private void loop(){

        // Set the clear color
        glClearColor(0.2f, 0.3f, 0.2f, 0.0f);

        program.setProjection(view_projection_loc, 1280, 720);

        double previousTime = glfwGetTime();
        int frameCount = 0;

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {

            // Measure FPS
            double currentTime = glfwGetTime();
            frameCount++;
            if ( currentTime - previousTime >= 1.0 )
            {
                // Display the frame count here any way you want.
                System.out.println(frameCount);

                frameCount = 0;
                previousTime = currentTime;
            }

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            float ct = (float)glfwGetTime();

            program.setUniform(model_loc, ct, new Vector3f(-2, 0, 0));
            triangle.draw();

            program.setUniform(model_loc, ct, new Vector3f(2, 0, 0));
            cube.draw();

            //rectangle.draw();

            glfwSwapBuffers(window); // swap the color buffers
        }
    }

    private static void WindowResize(long window, int width, int height){
        glViewport(0, 0, width, height);
        program.setProjection(view_projection_loc, width, height);
    }

    private void run(){
        init();
        loop();

        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static void main(String[] args) {
        new MyWindow().run();
    }

}
