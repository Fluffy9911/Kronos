#version 400 core 
//credits to: https://www.geeks3d.com/20101029/shader-library-pixelation-post-processing-effect-glsl/
in vec2 fpos;
in vec2 tcord;
out vec4 output;

uniform sampler2D tex;
uniform sampler2D edges;
uniform int pixelation;
uniform vec2 view;
uniform int use;



void main() 
{ 
  if(use==1){
   
    vec3 tc = texture2D(tex, tcord).xyz;
 vec3 edgeColor = texture(edges,tcord).rgb;
	output = vec4(edgeColor,1);
  }else{
  vec2 uv = tcord;
  float rt_w = view.x;
  float rt_h = view.y;
  
  
    float dx = pixelation*(1./rt_w);
    float dy = pixelation*(1./rt_h);
    vec2 coord = vec2(dx*floor(uv.x/dx),
                      dy*floor(uv.y/dy));
    vec3 tc = texture2D(tex, coord).xyz;
 	vec3 edgeColor = texture(edges,tcord).rgb;
 
  vec3 finalColor = tc+=edgeColor;

	output = vec4(finalColor, 1.0);
  }
}