package raytracing.scene

import glm_.glm
import glm_.vec3.Vec3
import glm_.vec3.operators.times
import java.util.Optional
import kotlin.math.tan

const val colorDepth = 255
const val channelSize = 3
fun Float.toChannelColor(): Byte {
    return (this * colorDepth).toInt().toByte()
}

class Scene {
    var width: Int = 0
    var height: Int = 0
    var bgColor: Vec3 = Vec3(0)
    var camera: Camera = Camera()
    var primitives: MutableList<Primitive> = mutableListOf()

    private fun intersect(primitive: Primitive, ray: Ray): Float? {
        val conjRotation = glm.conjugate(primitive.rotation)
        val newDir = glm.rotate(conjRotation, ray.direction)
        val newPos = ray.start - primitive.position
        val shiftedRay = Ray(newPos, newDir)
        return primitive.shape.intersectedWith(shiftedRay)
    }

    private fun raytrace(ray: Ray): Vec3 {
        val depthIndexed: List<Pair<Float, Int>> = primitives.mapIndexed { index, primitive ->
            intersect(primitive, ray) to index
        }.filter { it.first != null }.map { it.first!! to it.second }
        val minDepth = depthIndexed.minByOrNull { it.first } ?: return bgColor
        return primitives[minDepth.second].color
    }

    private fun generateRay(coord: PixelCoord): Ray {
        val px = coord.x + 0.5
        val py = coord.y + 0.5
        val fx = (2 * px / width - 1) * tan(camera.fovX / 2)
        val fy = -(2 * py / width - 1) * tan(camera.fovY / 2)
        val localDir = Vec3(fx, fy,1)
        val direction = localDir.x * camera.right + localDir.y * camera.up + localDir.z * camera.forward
        val directionNormal = direction.normalizeAssign()
        return Ray(camera.position, directionNormal)
    }

    fun render(): RenderedScene {
        val renderedScene = RenderedScene(width, height, channelSize)
        for (y in 0..<height) {
            for (x in 0..<width) {
                val coord = PixelCoord(x, y)
                val ray = generateRay(coord)
                val color = raytrace(ray)
                renderedScene.setColor(coord, color)
            }
        }
        return renderedScene
    }
}