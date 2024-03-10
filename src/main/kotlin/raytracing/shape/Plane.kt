package raytracing.shape

import glm_.vec3.Vec3
import raytracing.scene.Ray

class Plane(val normal: Vec3): Shape {
    override fun intersectedWith(ray: Ray): Float? {
        TODO("Not yet implemented")
    }
}