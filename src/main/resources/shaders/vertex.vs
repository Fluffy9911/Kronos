#version 400 core

in vec3 in_pos;

in vec2 uv;

out vec3 f_color;
out vec2 uv_p;

void main() {
    
    
uv_p = uv;
    gl_Position = vec4(in_pos, 1.0);
    
}
