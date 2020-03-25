#version 330
in vec3 newColor;
in vec2 outTexCoords;

out vec4 outColor;
uniform sampler2D samplerTex;

void main()
{
    outColor = texture(samplerTex, outTexCoords) * vec4(newColor, 1.0);
}
