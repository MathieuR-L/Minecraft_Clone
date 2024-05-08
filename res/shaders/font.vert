#version 330 core

layout (location = 0) in vec3 aPosition;
layout (location = 1) in vec3 aColor;
layout (location = 2) in vec2 aTexture;
layout (location = 3) in float aAlpha;

out vec3 color;
out vec2 textureCoords;
out float alpha;

uniform mat4 projection;
uniform mat4 model;

void main() {
    gl_Position = projection * model * vec4(aPosition, 1.0);
    color = aColor;
    alpha = aAlpha;
    textureCoords = aTexture;
}