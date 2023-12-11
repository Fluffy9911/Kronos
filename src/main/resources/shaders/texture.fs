#version 400 core 

in vec3 f_color;
in vec2 uv_p;
uniform sampler2D tex;
out vec4 fragOutput;

void main() {
    fragOutput = vec4(texture2D(tex,uv_p).rgb, 1.0);
}
