#version 400 core

in vec3 in_pos;
in vec3 color;
in vec3 normal;
out vec3 f_color;
uniform mat4 proj;
uniform mat4 view;
uniform vec3 lightDirection;  // Direction to the light source
uniform vec3 lightColor;     // Color of the light
uniform vec3 ambientColor;   // Ambient color

void main() {
    
    f_color = color;

    gl_Position = proj * view * vec4(in_pos, 1.0);
}
