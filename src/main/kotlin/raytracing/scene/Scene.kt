package raytracing.scene

import glm_.vec3.Vec3
import raytracing.scene.Camera

const val colorDepth = 255
const val channelSize = 3
fun Float.toChannelColor(): Byte {
    return (this * colorDepth).toInt().toByte()
}

class Scene {
    var width: Int = 0
    var height: Int = 0
    var bg_color: Vec3 = Vec3(0)
    var camera: Camera = Camera()
    var primitives: MutableList<Primitive> = mutableListOf()

    fun render(): RenderedScene {
        val renderedScene = RenderedScene(width, height, channelSize)
        var channelIndex = 0
        for (index in renderedScene.image.indices) {
            renderedScene.image[index] = bg_color[channelIndex].toChannelColor()
            channelIndex += 1
            if (channelIndex == renderedScene.channelSize) {
                channelIndex = 0
            }
        }
        return renderedScene
    }
}