#version 400 core

in vec3 in_pos;
in vec3 in_color;

uniform mat4 proj;
uniform mat4 transform;

out vec3 out_color;

void main() {
    
    

	out_color = in_color;
	
    gl_Position =  proj* transform*vec4(in_pos, 1.0);
    
}

