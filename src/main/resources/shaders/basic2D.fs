#version 400 core 


in vec2 uv_p;
uniform sampler2D tex;
out vec4 fragOutput;

void main() {

    fragOutput = texture2D(tex,vec2(-uv_p.x,-uv_p.y)).rgba;
    
    if(fragOutput.w == 0){
    discard;
    }
}
