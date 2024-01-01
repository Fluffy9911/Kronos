#version 400 core 


in vec2 uv_p;
uniform sampler2D tex;
uniform float intesity;
out vec4 fragOutput;

void main() {
   
    vec4 o = texture2D(tex,vec2(-uv_p.x,-uv_p.y));
    fragOutput = vec4(o.rgb+intesity,o.a);
    if(fragOutput.w == 0){
    discard;
    }
}
