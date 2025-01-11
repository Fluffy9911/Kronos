#version 430 core

struct TextData {
    vec2 id; // x = texture_id, y = position in atlas (x.y), z = size in atlas (width.height)
    vec4 info;
};

struct DrawData {
 float id[2];
 float info[4];
};
//drawing!!
layout (std430) buffer ImgDat {
    DrawData dds[];
};
//atlas
layout (std430) buffer TexDat {
    TextData tds[];
};



uniform sampler2D textureAtlas; // The texture atlas containing all the textures

in vec3 f_fpos; // Fragment position in screen space
in vec2 vp;

in mat4 p;
out vec4 fragOutput; // Output fragment color

bool pointInRectangle(vec2 point, vec2 rectPos, vec2 rectSize) {
    // Check if the point is within the bounds of the rectangle
    return point.x >= rectPos.x && point.x <= rectPos.x + rectSize.x &&
           point.y >= rectPos.y && point.y <= rectPos.y + rectSize.y;
}



void main() {
    vec4 v = p * vec4(1, 1, 1, 1);
    vec2 pos = f_fpos.xy;
    vec3 color = vec3(pos.x * v.x, pos.y, 1.0);
    vec3 col = vec3(dds.length(),dds.length(),dds.length());
 vec2 fragCoord = gl_FragCoord.xy / vec2(1920,1080); // Replace with your window width and height
col = vec3(fragCoord,0);


    for (int i = 0; i < dds.length(); i++) {
        vec2 rectPos = vec2(dds[i].info[0],dds[i].info[1]);
       vec2 rs = vec2(dds[i].info[2],dds[i].info[3]);
        // Check if the current fragment position is inside the rectangle
        if (pointInRectangle(gl_FragCoord.xy, rectPos,rs)==true) {
           col = vec3(0,0,0);
 
            // Get the corresponding texture information from tds
            for (int j = 0; j < tds.length(); j++) {
                int id = int(tds[j].id.x);
                if (id == int(dds[i].id[0])) {
                    TextData texData = tds[j];
                    vec2 atlasPos = vec2(tds[j].info.xy);
                    vec2 atlasSize = vec2(tds[j].info.zw);

                    // Calculate the texture coordinates in the atlas
                    vec2 texCoord = (gl_FragCoord.xy - rectPos) / rs * atlasSize + atlasPos;

                    // Sample the texture atlas at the calculated coordinates
                    //fragOutput = texture(textureAtlas, texCoord);
                    //return;
                }
            }
            
        }
    }

    // If the fragment is not within any rectangle, set the output to the default color
    fragOutput = vec4(col, 1);
}
