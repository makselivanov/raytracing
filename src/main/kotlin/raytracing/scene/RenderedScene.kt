package raytracing.scene

import glm_.vec3.Vec3
import java.io.FileOutputStream

class RenderedScene(val width: Int, val height: Int, val channelSize: Int) {
    var image: ByteArray = byteArrayOf()

    fun setColor(coord: PixelCoord, color: Vec3) {
        image[coord.y * width * channelSize + coord.x * channelSize] = color[0].toChannelColor()
        image[coord.y * width * channelSize + coord.x * channelSize + 1] = color[1].toChannelColor()
        image[coord.y * width * channelSize + coord.x * channelSize + 2] = color[2].toChannelColor()
    }

    fun save(outputPicturePath: String) {
        val fos = FileOutputStream(outputPicturePath)
        fos.use {
            val header = "P6\n$width $height\n255\n".toByteArray()
            with (it) {
                write(header)
                for (y in 0..<height) {
                    val sliceSize = width * channelSize
                    val slice = image.sliceArray(y * sliceSize ..<(y + 1) * sliceSize)
                    write(slice)
                }
            }
        }
    }

    init {
        image = ByteArray(width * height * channelSize)
    }
}