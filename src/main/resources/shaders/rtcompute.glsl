#version 430 core

layout (local_size_x = 1, local_size_y = 1, local_size_z = 1) in;

layout(rgba32f, binding = 0) uniform image2D iOut;

const float pos_infinity = uintBitsToFloat(0x7F800000);
const float neg_infinity = uintBitsToFloat(0xFF800000);

float xorshift(inout int value) {
    // Xorshift*32
    // Based on George Marsaglia's work: http://www.jstatsoft.org/v08/i14/paper
    value ^= value << 13;
    value ^= value >> 17;
    value ^= value << 5;
    return value;
}

int nextInt(inout int seed) {
    seed = int(xorshift(seed));
    return seed;
}

float nextFloat(inout int seed) {
    seed = int(xorshift(seed));
    return abs(fract(float(seed) / 3141.592653));
}

float nextFloatMax(inout int seed, in float max) {
    return nextFloat(seed) * max;
}

float random(inout int seed) {
    return fract(sin(float(seed)) * 43758.5453);
}

float random(float minVal, float maxVal, inout int seed) {
    return mod(random(seed) , (maxVal - minVal)) + minVal;
}

bool isNearZero(vec3 vec, float epsilon) {
    return abs(vec.x) < epsilon && abs(vec.y) < epsilon && abs(vec.z) < epsilon;
}

vec3 randomVec(inout int seed) {
    return vec3(random(seed), random(seed), random(seed));
}

vec3 randomVecBound(inout int seed, float m, float ma) {
    float v1 = random(m, ma, seed);
    float v2 = random(m, ma, seed);
    float v3 = random(m, ma, seed);

    return vec3(v1, v2, v3);
}

struct Material {
    int id;
    vec3 dat;
};

struct Ray {
    vec3 pos;
    vec3 dir;
};

struct Record {
    vec3 p;
    vec3 normal;
    // x is the T and y is if it's a front face
    vec2 dat;
    Material m;
};

struct Interval {
    float mi;
    float ma;
};

struct Info {
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

void writePixel(vec3 col, ivec2 pos);

bool isFrontFace(Record rc);

float getT(Record rc) {
    return rc.dat.x;
}

vec3 negate(vec3 v) {
    return vec3(-v.x, -v.y, -v.z);
}

bool contains(float t, Interval i) {
    return i.mi <= t && t <= i.ma;
}

bool surrounds(float t, Interval i) {
    return i.mi < t && t < i.ma;
}

void setNormal(Record r, Ray ray, vec3 on) {
    vec3 rd = ray.dir;
    if (dot(rd, on) < 0) {
        r.dat.y = 1;
    }
    if (isFrontFace(r)) {
        r.normal = on;
    } else {
        r.normal = negate(on);
    }
}

void setT(float t, Record r) {
    r.dat.x = t;
}

vec3 atRay(float t, Ray r) {
    return r.pos + (r.dir * t);
}

void lambScatter(Ray rin, Record rec, vec3 atten, Ray scat) {
    Material mat = rec.m;
    vec3 sd = rec.normal;
    // Implement your Lambertian scattering here
}

void matScatter(Material m) {
    if (m.id == 0) {
        // Material with id 0
    } else if (m.id == 1) {
        // Material with id 1
    } else if (m.id == 2) {
        // Material with id 2
    }
}

uniform Info sceneInfo;

void writePixel(vec3 col, ivec2 pos) {
    imageStore(iOut, pos, vec4(col, 1.0));
}
bool isFrontFace(Record rc) {
    return rc.dat.y == 1;
}
void main() {
    ivec2 cord = ivec2(gl_GlobalInvocationID.xy);
  //  int s = int(floor(gl_GlobalInvocationID.x * sceneInfo.iW + gl_GlobalInvocationID.y));

    writePixel(vec3(1.0, 0.0, 0.0), cord);
}




