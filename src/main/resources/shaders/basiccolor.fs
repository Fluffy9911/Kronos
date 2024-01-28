#version 400 core 


in vec3 f_color;
in vec3 f_normal;
in vec3 f_fpos;

uniform float as;
uniform vec3 ac;

uniform vec3 lp;
uniform vec3 lightColor;
uniform vec3 viewPos;

out vec4 fragOutput;

void main() {

float specularStrength = 1;


 float ambientStrength = as;
    vec3 ambient = ambientStrength * ac;

   
   

vec3 norm = normalize(f_normal);
vec3 lightDir = normalize(lp - f_fpos);


float diff = max(dot(norm, lightDir), 0.0);
vec3 diffuse = diff * lightColor;


vec3 viewDir = normalize(viewPos - f_fpos);
vec3 reflectDir = reflect(-lightDir, f_normal);  
float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32);
vec3 specular = specularStrength * spec * lightColor;  

    vec3 result = (ambient + diffuse + specular) * f_color;
fragOutput = vec4(result, 1.0);
}
