package raytracing.shape

import glm_.vec3.Vec3
import kotlin.math.sqrt
import raytracing.scene.Ray

class Ellipsoid(val radius: Vec3): Shape {
    override fun intersectedWith(ray: Ray): Float? {
        val sX = ray.start.x / radius.x
        val sY = ray.start.y / radius.y
        val sZ = ray.start.z / radius.z
        val dX = ray.direction.x / radius.x
        val dY = ray.direction.y / radius.y
        val dZ = ray.direction.z / radius.z
        val newStart = Vec3(sX, sY, sZ)
        val newDir = Vec3(dX, dY, dZ)
        val a = newDir.dot(newDir)
        val b = 2 * newStart.dot(newDir)
        val c = newStart.dot(newStart) - 1
        val D = b * b - 4 * a * c
        val tPlus = (-b + sqrt(D)) / (2 * a)
        val tMinus = (-b - sqrt(D)) / (2 * a)
        if (tMinus > 0)
            return tMinus
        if (tPlus > 0)
            return tPlus
        return null
    }
}