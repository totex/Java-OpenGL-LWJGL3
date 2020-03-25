package info.shader;

//import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import org.joml.Matrix4f;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
	
	private final int _id;
    private FloatBuffer buff;
    private Matrix4f matrix;
    private Matrix4f view_projection;

	public ShaderProgram() {
        _id = glCreateProgram();
        buff = BufferUtils.createFloatBuffer(16);
        matrix = new Matrix4f();
//        view_projection = new Matrix4f().translate(new Vector3f(0, 0, -3)).perspective(45, aspect, 0.1f, 100);
//        view_projection = new Matrix4f().lookAt(new Vector3f(0,0,-3), new Vector3f(0,0,0), new Vector3f(0,1,0));

    }
	
	public void attachShader(ShaderLoader shader) {
        glAttachShader(_id, shader.getID());
    }
	
	public void link() {
        glLinkProgram(_id);

        checkStatus();
    }
	
	public void use() {
        glUseProgram(_id);
    }
	
	public void checkStatus() {
        int status = glGetProgrami(_id, GL_LINK_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(_id));
        }
    }
	
	public void delete() {
        glDeleteProgram(_id);
    }
	
	public int getAttributeLocation(CharSequence name) {
        return glGetAttribLocation(_id, name);
    }
	
	public void enableVertexAttribute(int location) {
        glEnableVertexAttribArray(location);
    }

    public int getUniformLocation(CharSequence name){
	    return glGetUniformLocation(_id, name);
    }

    public void setProjection(int loc, int width, int height){
        float aspect = (float)width / height;
        //System.out.println(aspect);
        view_projection = new Matrix4f().perspective(45, aspect, 0.1f, 100).translate(new Vector3f(0, 0, -3));
        glUniformMatrix4fv(loc, false, view_projection.get(buff));
    }

    public void setUniform(int loc, float time, Vector3f vec){

        //time *= 0.02;

//        matrix.translate(new Vector3f(0, 0, 0));
        //matrix.translate(0, 0, 0);
        matrix.identity();
        matrix.translate(vec);
        matrix.rotate(time, 0, 1, 0.2f);
//        matrix.rotation(new AxisAngle4f(time, 0, 0, 1));
//        matrix.translate(new Vector3f(1, 0, 0)).rotate(new AxisAngle4f(0.01f, 0, 0, 1)).translate(new Vector3f(-1, 0, 0));
        //matrix.rotation(new AxisAngle4f(time, 0, 0, 1)).translate(new Vector3f(1.0f, 0, 0)).transformPosition(new Vector3f(0, 0, 0));
        //matrix.rotate(time, 0, 0, 1).translate(new Vector3f(1.0f, 0, 0)).transformPosition(new Vector3f(0, 0, 0));
//        matrix.rotate(time, 0, 0, 1);

//        Vector3f center = new Vector3f(0, 0, 0);
//        Vector3f pointToRotate = new Vector3f();
//        matrix.translate(center)
//                .rotate((float) Math.toRadians(90.0f), 1.0f, 0.0f, 0.0f)
//                .translate(center.negate())
//                .transformPosition(pointToRotate);

        //matrix.set(buff);

        glUniformMatrix4fv(loc, false, matrix.get(buff));

        //glUniform4fv(loc, buff);
    }
	
	public void pointVertexAttribute(int location, int size, int stride, int offset) {
        glVertexAttribPointer(location, size, GL_FLOAT, false, stride, offset);
    }
}
