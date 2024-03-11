package raytracing.scene

import glm_.quat.Quat
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import raytracing.shape.Shape

class Primitive {
    var position: Vec3 = Vec3()
    var rotation: Quat = Quat(1, 0, 0, 0)
    var color: Vec3 = Vec3()
    lateinit var shape: Shape
}