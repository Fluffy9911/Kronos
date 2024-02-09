#version 430 core

layout (local_size_x = 1, local_size_y = 1, local_size_z = 1) in;

layout(rgba32f, binding = 0) uniform image2D iOut;
//helpers
const float pos_infinity = uintBitsToFloat(0x7F800000);
const float neg_infinity = uintBitsToFloat(0xFF800000);

 

float xorshift(in int value) {
    // Xorshift*32
    // Based on George Marsaglia's work: http://www.jstatsoft.org/v08/i14/paper
    value ^= value << 13;
    value ^= value >> 17;
    value ^= value << 5;
    return value;
}

int nextInt(inout float seed) {
    seed = floatBitsToInt(floor(xorshift(int(seed))));
    return int(floor(seed));
}

float nextFloat(inout int seed) {
    seed = int(xorshift(seed));
    // FIXME: This should have been a seed mapped from MIN..MAX to 0..1 instead
    return abs(fract(seed / 3141.592653));
}

float nextFloatMax(inout int seed, in float max) {
    return nextFloat(seed) * max;
}
float random(inout float seed) {
    return fract(sin(seed) * 43758.5453);
}

float random(float minVal, float maxVal, inout float seed) {
    return mod(random(seed) , (maxVal- minVal)) + minVal;
}


bool isNearZero(vec3 vec, float epsilon) {
    return abs(vec.x) < epsilon && abs(vec.y) < epsilon && abs(vec.z) < epsilon;
}

vec3 randomVec(float seed){
    return vec3(random(seed),random(seed),random(seed));
}
vec3 randomVecBound(inout float seed,float m,float ma){
    float v1 = random(m,ma,seed);
    seed * seed;
     float v2 = random(m,ma,seed);
    seed * seed;
     float v3 = random(m,ma,seed);
    seed * seed;
    
    return vec3(v1,v2,v3);
}

struct Material {

int id;
vec3 dat;

};

struct Ray{ 
vec3 pos;
vec3 dir;

};

struct Record {
vec3 p;
vec3 normal;
//x is the T and y is if its a front face
vec2 dat;
Material m;

};

struct Interval {
float mi;
float ma;

};


struct Info
{
float aspect;
float iW;
float iH;
float defocus;
float spp;
float maxDepth;
float vfov;

vec3 center;
vec3 look;
vec3 up;
vec3 u;
vec3 v;
vec3 w;
vec3 du;
vec3 dv;
vec3 ploc;
vec3 pdu;
vec3 pdv;

};


//methods
void writePixel(vec3 col,ivec2 pos);

bool isFrontFace(Record rc);

float getT(Record rc){
    return rc.dat.x;
}

vec3 negate(vec3 v){
    return vec3(-v.x,-v.y,-v.z);
}
//interval
bool contains(float t,Interval i){
    return i.mi <= t && t <= i.ma;
}
bool surrounds(float t,Interval i){
    return i.mi < t && t < i.ma;
}


//record
void setNormal(Record r,Ray ray,vec3 on){
    vec3 rd = ray.dir;
    if(dot(rd,on) <0){
        r.dat.y = 1;

    } 
    if(isFrontFace(r)){
        r.normal = on;
    }else{
        r.normal = negate(on);
    }
}
void setT(float t,Record r){
r.dat.x = t;
}

//ray
vec3 atRay(float t,Ray r){
   return r.pos + (r.dir * t);
}

void lambScatter(Ray rin,Record rec,vec3 atten,Ray scat){
    Material mat = rec.m;

vec3 sd = rec.normal;

}

void matScatter(Material m){
if(m.id == 0)
{

}
if(m.id == 1){

}
if(m.id == 2)
{

}



}

uniform Info sceneInfo;
uniform int seed;
void main() {
     ivec2 cord = ivec2(gl_GlobalInvocationID.xy);
int x = cord.x;
int y = cord.y;
int s = int(seed);
//float seed = (gl_GlobalInvocationID.x * gl_GlobalInvocationID.y);
//seed *= sseed;
writePixel(randomVecBound(int(s),0,1) / sceneInfo.spp,cord);

    
   
}

void writePixel(vec3 col,ivec2 pos){
 imageStore(iOut, pos, vec4(col,1.0));


}

bool isFrontFace(Record rc){
    if(rc.dat.y == 1){
        return true;
    }else{
        return false;
    }
}

