package raytracing.scene

import glm_.vec3.Vec3
import raytracing.scene.Camera

class Scene {
    var width: Int = 0
    var height: Int = 0
    var bg_color: Vec3 = Vec3(0)
    var camera: Camera = Camera()
    var primitives: MutableList<Primitive> = mutableListOf()

    fun render() {
        //TODO
    }

    fun save(outputPicturePath: String) {
        //TODO
    }
}