#version 330
layout(location = 0) in vec3 position;
layout(location = 1) in vec3 color;
layout(location = 2) in vec2 inTexCoords;


out vec3 newColor;
out vec2 outTexCoords;

uniform mat4 model;
uniform mat4 view_projection;

void main()
{
    gl_Position = view_projection * model * vec4(position, 1.0f);
//    gl_Position = rotation * vec4(position, 1.0f);
    newColor = color;
    outTexCoords = inTexCoords;
}
