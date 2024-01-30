#version 400 core

in vec3 in_pos;
in vec2 uv;

uniform mat4 proj;
uniform mat4 transform;

out vec2 uv_p;

void main() {
    
    

	uv_p = uv;
	
    gl_Position =  proj* transform*vec4(in_pos, 1.0);
    
}

