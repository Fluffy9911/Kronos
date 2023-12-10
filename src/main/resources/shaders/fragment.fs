#version 400 core 

in vec3 f_color;

out vec4 fragOutput;

void main() {
    fragOutput = vec4(f_color, 1.0);
}
