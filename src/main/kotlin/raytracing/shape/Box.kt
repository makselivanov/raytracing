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
        val txMax = max(txPlus, txMinus)
        val txMin = min(txPlus, txMinus)
        val tyMax = max(tyPlus, tyMinus)
        val tyMin = min(tyPlus, tyMinus)
        val tzMax = max(tzPlus, tzMinus)
        val tzMin = min(tzPlus, tzMinus)
        val tMin = max(txMin, tyMin, tzMin)
        val tMax = min(txMax, tyMax, tzMax)
        if (tMin > tMax)
            return null
        if (tMin > 0)
            return tMin
        if (tMax > 0)
            return tMax
        return null
    }
}