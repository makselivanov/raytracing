package raytracing.scene

import java.io.FileOutputStream

class RenderedScene(val width: Int, val height: Int, val channelSize: Int) {
    var image: ByteArray = byteArrayOf()

    fun save(outputPicturePath: String) {
        val fos = FileOutputStream(outputPicturePath)
        fos.use {
            val header = "P6\n$width $height\n255\n".toByteArray()
            with (it) {
                write(header)
                for (y in 0 until height) {
                    val sliceSize = width * channelSize
                    val slice = image.sliceArray(IntRange(y * sliceSize, (y + 1) * sliceSize))
                    write(slice)
                }
            }
        }
    }

    init {
        image = ByteArray(width * height * channelSize)
    }
}