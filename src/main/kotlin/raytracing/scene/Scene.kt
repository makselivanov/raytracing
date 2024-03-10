package raytracing.scene

import glm_.vec3.Vec3
import java.util.Optional

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

    private fun raytrace(ray: Ray): Vec3 {
        val depthIndexed: List<Pair<Float?, Int>> = primitives.mapIndexed { index, primitive ->
            primitive.shape.intersectedWith(ray) to index
        }.filter { it.first != null }
        //TODO depth in camera
    }

    private fun generateRay(coord: PixelCoord): Ray {
        //TODO use camera and coord to generate Ray
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