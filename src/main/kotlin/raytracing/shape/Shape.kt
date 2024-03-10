package raytracing.shape

import raytracing.scene.Ray

interface Shape {
    fun intersectedWith(ray: Ray): Float?
}