package raytracing.shape

import glm_.vec3.Vec3
import raytracing.scene.Ray

class Ellipsoid(radius: Vec3): Shape {
    override fun intersectedWith(ray: Ray): Float? {
        return null //TODO
    }
}