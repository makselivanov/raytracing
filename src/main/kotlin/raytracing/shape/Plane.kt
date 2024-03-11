package raytracing.shape

import glm_.vec3.Vec3
import raytracing.scene.Ray

class Plane(val normal: Vec3): Shape {
    override fun intersectedWith(ray: Ray): Float? {
        val depth = - ray.start.dot(normal) / ray.direction.dot(normal)
        return if (depth < 0) null else depth
    }
}