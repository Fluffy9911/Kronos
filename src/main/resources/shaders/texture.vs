#version 400 core

in vec3 in_pos;

in vec2 uv;
uniform mat4 proj;
uniform mat4 view;
out vec3 f_color;
out vec2 uv_p;

void main() {
    
    
uv_p = uv;
    gl_Position =  proj* vec4(in_pos, 1.0);
    
}
