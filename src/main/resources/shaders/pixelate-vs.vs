#version 400 core 
 
 


in vec2 in_pos;
in vec2 uv;

out vec2 tcord;
out vec2 fpos;

void main() {

    tcord = uv;
    gl_Position = vec4(in_pos.x,in_pos.y,0, 1.0);
   fpos = in_pos;
}
