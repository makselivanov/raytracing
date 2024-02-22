package parser

import java.io.File
import scene.Commands
import scene.Scene


class Parser(val inputPath: String) {
    fun parse(): Scene {
        var scene = Scene()
        val supportedCommands = Commands(scene)
        File(inputPath).forEachLine { line ->
            val words = line.split(" ")
            val command = words[0]
            val args = words.subList(1, words.size).map { it.toFloat() } // FIXME if crash because of this, change this

            supportedCommands.parseCommand(command, args)
        }
        return scene
    }
}