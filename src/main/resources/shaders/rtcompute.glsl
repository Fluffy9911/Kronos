#version 430 core

layout (local_size_x = 1, local_size_y = 1, local_size_z = 1) in;

layout(rgba32f, binding = 0) uniform image2D iOut;

const float pos_infinity = uintBitsToFloat(0x7F800000);
const float neg_infinity = uintBitsToFloat(0xFF800000);
const float defocus_angle = 10;
vec3 pixelDeltaU;
vec3 pixelDeltaV;
vec3 pixel00Loc;
vec3 du;
vec3 dv;
vec3 center;
float defocusRadius;
int seed;

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
float random() {
    return random(seed);
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
uniform Info sceneInfo;
vec3 atRay(float t, Ray r) {
    return r.pos + (r.dir * t);
}

float getT(Record rc) {
    return rc.dat.x;
}

bool isFrontFace(Record rc) {
    return rc.dat.y == 1;
}

void writePixel(vec3 col, ivec2 pos);





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

vec3 randomInUnitDisk() {
    while (true) {
        vec2 p = vec2(random(-1.0, 1.0,seed), random(-1.0, 1.0,seed));
        
        // Convert the 2D point to 3D by setting z to 0
        vec3 point = vec3(p.x, p.y, 0.0);
        
        if (dot(point, point) < 1.0)
            return point;
    }
}


vec3 pixelSampleSquare() {
    // Returns a random point in the square surrounding a pixel at the origin.
    float px = -0.5 + random();
    float py = -0.5 + random();
    return (pixelDeltaU * px) + (pixelDeltaV * py);
}

// Define global variables
vec3 defocusDiskSample() {
    // Returns a random point in the camera defocus disk.
    vec3 p = randomInUnitDisk();
    vec3 offset = (du * p.x) + (dv * p.y);
    return sceneInfo.center + offset;
}
Ray getRay(int i, int j) {
    // Get a randomly sampled camera ray for the pixel at location i, j.

    vec3 pixelCenter = pixel00Loc + (pixelDeltaU * float(i)) + (pixelDeltaV * float(j));
    vec3 pixelSample = pixelCenter + pixelSampleSquare();

    vec3 rayOrigin = (defocus_angle <= 0.0) ? center : defocusDiskSample();
    vec3 rayDirection = pixelSample - rayOrigin;
Ray ray;
ray.pos = rayOrigin;
ray.dir = rayDirection;
    return ray;
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



void writePixel(vec3 col, ivec2 pos) {
    imageStore(iOut, pos, vec4(col, 1.0));
}




void initialize() {
    // Determine viewport dimensions.
  seed = 7645645;
  center = sceneInfo.center;
    float focus_dist = 3.4; // Distance from camera lookfrom point to plane of perfect focus
    float theta = radians(sceneInfo.vfov);
    float h = tan(theta / 2.0);
    float viewportHeight = 2.0 * h * focus_dist;
    float viewportWidth = viewportHeight * (sceneInfo.iW / sceneInfo.iH);

    // Calculate the vectors across the horizontal and down the vertical viewport
    vec3 w = normalize(sceneInfo.center - sceneInfo.look);
    vec3 u = normalize(cross(sceneInfo.up, w));
    vec3 v = cross(w, u);
    vec3 viewportU = u * viewportWidth; // Vector across viewport horizontal edge
    vec3 viewportV = -v * viewportHeight; // Vector down viewport vertical edge

    // Calculate the horizontal and vertical delta vectors from pixel to pixel.
    pixelDeltaU = viewportU / sceneInfo.iW;
    pixelDeltaV = viewportV / sceneInfo.iH;
    vec3 viewportUpperLeft = sceneInfo.center - (w * focus_dist) - (viewportU / 2.0) - (viewportV / 2.0);

    // Calculate the location of the upper left pixel.
    pixel00Loc = viewportUpperLeft + ((pixelDeltaU + pixelDeltaV) * 0.5);
    // The following lines might be adjusted based on how 'objects' are handled in your GLSL code.
    // objects.innit();
     defocusRadius = focus_dist * tan(radians(defocus_angle / 2.0));
    du = u * defocusRadius;
    dv = v * defocusRadius;
}




void main() {
    initialize();
    ivec2 cord = ivec2(gl_GlobalInvocationID.xy);
  //  int s = int(floor(gl_GlobalInvocationID.x * sceneInfo.iW + gl_GlobalInvocationID.y));

    writePixel(vec3(1.0, 0.0, 0.0), cord);
}




