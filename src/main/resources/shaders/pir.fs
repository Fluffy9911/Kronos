#version 430 core

bool pointInRectangle(vec2 point, vec2 rectPos, vec2 rectSize) {
    // Check if the point is within the bounds of the rectangle
    return point.x >= rectPos.x && point.x <= rectPos.x + rectSize.x &&
           point.y >= rectPos.y && point.y <= rectPos.y + rectSize.y;
}
out vec4 frag;
void main(){

vec3 col = vec3(1,1,1);

if(pointInRectangle(gl_FragCoord.xy,vec2(10,10),vec2(400,200))){
    col *= vec3(0,0,0);
}

frag = vec4(col,1);

}