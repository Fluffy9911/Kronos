#version 430 core

in vec3 in_pos; // Input vertex position
in vec2 uv; // Input texture coordinates


uniform mat4 projection; // Projection matrix


out vec3 f_fpos; // Fragment position in screen coordinates
out vec2 vp;
out vec2 ss;
out mat4 p;
void main() {
  
f_fpos = in_pos;
p = projection;

    gl_Position =  vec4(in_pos, 1.0);
    
}