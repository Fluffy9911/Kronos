#version 400 core 
//credits to: https://www.geeks3d.com/20101029/shader-library-pixelation-post-processing-effect-glsl/
in vec2 fpos;
in vec2 tcord;
out vec4 output;

uniform sampler2D tex;

uniform vec2 view;
uniform int use;


void make_kernel(inout vec4 n[9], sampler2D tex, vec2 coord)
{
	float w = 1.0 / view.x;
	float h = 1.0 / view.y;

	n[0] = texture2D(tex, coord + vec2( -w, -h));
	n[1] = texture2D(tex, coord + vec2(0.0, -h));
	n[2] = texture2D(tex, coord + vec2(  w, -h));
	n[3] = texture2D(tex, coord + vec2( -w, 0.0));
	n[4] = texture2D(tex, coord);
	n[5] = texture2D(tex, coord + vec2(  w, 0.0));
	n[6] = texture2D(tex, coord + vec2( -w, h));
	n[7] = texture2D(tex, coord + vec2(0.0, h));
	n[8] = texture2D(tex, coord + vec2(  w, h));
}

vec4 sobel(){
  vec4 n[9];
	make_kernel( n, tex, tcord );

	vec4 sobel_edge_h = n[2] + (2.0*n[5]) + n[8] - (n[0] + (2.0*n[3]) + n[6]);
  	vec4 sobel_edge_v = n[0] + (2.0*n[1]) + n[2] - (n[6] + (2.0*n[7]) + n[8]);
	vec4 sobel = sqrt((sobel_edge_h * sobel_edge_h) + (sobel_edge_v * sobel_edge_v));
return sobel;
}

void main() 
{ 
 
  output = vec4(1.0-sobel().rgb,1.0);

}