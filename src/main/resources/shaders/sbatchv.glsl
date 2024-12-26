#version 330 core

layout(location = 0) in vec3 position;   // Position of the vertex
layout(location = 1) in vec2 texCoord;   // Texture coordinates of the vertex

out vec2 TexCoord; // Output texture coordinates to fragment shader

uniform mat4 projection;  // Projection matrix (for camera)

void main() {
    gl_Position = projection * vec4(position, 1.0);
    TexCoord = texCoord;  // Pass texture coordinates to fragment shader
}
