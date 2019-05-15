#version 330
layout(location = 0) in vec3 position;
layout(location = 1) in vec3 color;
layout(location = 2) in vec2 inTexCoords;


out vec3 newColor;
out vec2 outTexCoords;

void main()
{
    gl_Position = vec4(position, 1.0f);
    newColor = color;
    outTexCoords = inTexCoords;
}
