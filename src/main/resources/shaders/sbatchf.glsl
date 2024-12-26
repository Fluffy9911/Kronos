#version 330 core

in vec2 TexCoord;  // Input texture coordinates from vertex shader
out vec4 FragColor; // Output color of the fragment

uniform sampler2D spriteTexture;  // Texture sampler

void main() {
    FragColor = texture(spriteTexture, TexCoord); // Sample the texture
}
