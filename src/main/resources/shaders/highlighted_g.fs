#version 400 core

in vec2 uv_p;
uniform sampler2D tex;
uniform float intesity;
out vec4 fragOutput;



void main() {
    // Calculate normalized coordinates
    vec3 colorBottomRight = texture2D(tex,vec2(-uv_p.x,-uv_p.y)).rgb;
     vec3 colorTopLeft = vec4(colorBottomRight.rgb+intesity,texture2D(tex,vec2(-uv_p.x,-uv_p.y)).a).rgb;
     // Adjust the resolution based on your needs

    // Interpolate between the two colors
    vec3 finalColor = mix(colorBottomRight, colorTopLeft, max(-uv_p.x,-uv_p.y));

    fragOutput = vec4(finalColor, 1.0);
}
