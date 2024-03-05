package raytracing.scene

import glm_.vec3.Vec3

class Camera {
    var position: Vec3 = Vec3()
    var right: Vec3 = Vec3()
    var up: Vec3 = Vec3()
    var forward: Vec3 = Vec3()
    var fovX: Float = 0F
    var fovY: Float = 0F
}