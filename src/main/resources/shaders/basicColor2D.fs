#version 400 core 


in vec3 out_color;


out vec4 fragOutput;

void main() {
    fragOutput = vec4(out_color,1);
    
}
