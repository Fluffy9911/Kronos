#version 400 core

in vec3 in_pos;
in vec3 in_color;
in vec3 in_normal;

out vec3 f_color;
out vec3 f_normal;
out vec3 f_fpos;

uniform mat4 proj;
uniform mat4 view;
uniform mat4 model;
uniform mat4 omodel

void main() {
    
    
f_color = in_color;


//f_normal = mat3(transpose(inverse(model))) * in_normal;
f_normal = in_normal;
 f_fpos = vec3(model * vec4(in_pos, 1.0));
    gl_Position = proj * view * model* omodel*  vec4(in_pos, 1.0);
    
}
