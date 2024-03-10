package raytracing.parser

import java.io.File
import raytracing.scene.Commands
import raytracing.scene.Scene


object SceneParser {
    fun parse(descriptionPath: String): Scene {
        val scene = Scene()
        val supportedCommands = Commands(scene)
        File(descriptionPath).forEachLine { line ->
            val words = line.split(" ")
            val command = words[0]
            val args = words.subList(1, words.size).map { it.toFloat() }
            supportedCommands.parseCommand(command, args)
        }
        return scene
    }
}