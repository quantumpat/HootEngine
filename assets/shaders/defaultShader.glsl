#type vertex
#version 410
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;

uniform mat4 uProjection;
uniform mat4 uView;

out vec4 fColor;

void main() {
    fColor = aColor;
    gl_Position = uProjection * uView * vec4(aPos, 1.0);
}

#type fragment
#version 410

uniform float uTime;

in vec4 fColor;

out vec4 color;

void main() {
    //float average = (fColor.r + fColor.g + fColor.b) / 3;
    float noise = fract(sin(dot(fColor.xy, vec2(12.9898, 78.233))) * 43758.5453);

    color = fColor * noise;
}