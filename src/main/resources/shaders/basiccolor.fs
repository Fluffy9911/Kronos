#version 430 core 


in vec3 f_color;
in vec3 f_normal;
in vec3 f_fpos;

struct Material {
float strength;
};

struct Light{
 vec3 position;

    
    float constant;
    float linear;
    float quadratic;
	
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    
    float dis;
  
};



uniform vec3 viewPos;
uniform Material material;

layout (std430, binding = 1) buffer Lights
{
Light lights[];

};

vec3 CalcSpotLight(Light light, vec3 normal, vec3 fragPos, vec3 viewDir);

out vec4 fragOutput;

  void main() {

vec3 norm = normalize(f_normal);

    vec3 viewDir = normalize(viewPos - f_fpos);

  vec3 result = vec3(0,0,0);

for(int i =0; i < lights.length(); i++){
  result += CalcSpotLight(lights[i],norm,f_fpos,viewDir);
}




fragOutput = vec4(result, 1.0);
}

vec3 CalcSpotLight(Light light, vec3 normal, vec3 fragPos, vec3 viewDir)
{
    vec3 lightDir = normalize(light.position - fragPos);
    // diffuse shading
    float diff = max(dot(normal, lightDir), 0.0);
    // specular shading
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.strength);
    // attenuation
    float distance = length(light.position - fragPos);
    distance /= light.dis;
    float attenuation = 1.0 / (light.constant + light.linear * distance + light.quadratic * (distance * distance));    
    // combine results
    vec3 ambient = (light.ambient *2) * f_color;
    vec3 diffuse = light.diffuse * diff;
    vec3 specular = light.specular * spec;
    ambient *= attenuation;
    diffuse *= attenuation;
    specular *= attenuation;
    return (ambient + diffuse + specular);
}
