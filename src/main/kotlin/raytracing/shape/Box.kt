package raytracing.shape

import glm_.glm.max
import glm_.glm.min
import glm_.vec3.Vec3
import raytracing.scene.Ray

class Box(val box: Vec3): Shape {
    override fun intersectedWith(ray: Ray): Float? {
        val txPlus = (box.x - ray.start.x) / ray.direction.x
        val txMinus = (-box.x - ray.start.x) / ray.direction.x
        val tyPlus = (box.y - ray.start.y) / ray.direction.y
        val tyMinus = (-box.y - ray.start.y) / ray.direction.y
        val tzPlus = (box.z - ray.start.z) / ray.direction.z
        val tzMinus = (-box.z - ray.start.z) / ray.direction.z
        val tMinus = max(txMinus, tyMinus, tzMinus)
        val tPlus = min(txPlus, tyPlus, tzPlus)
        if (tMinus > tPlus)
            return null
        if (tMinus > 0)
            return tMinus
        if (tPlus > 0)
            return tPlus
        return null
    }
}