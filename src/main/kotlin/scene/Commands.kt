package scene

class Commands(var scene: Scene) {
    val commandDimensions = { width: Float, height: Float ->
        scene.width = width.toInt()
        scene.height = height.toInt()
    }

    val commandBackground = { red: Float, green: Float, blue: Float ->
        scene.bg_color = Color(red, green, blue)
    }

    fun parseCommand(command: String, args: List<Float>) {
        when (command) {
            "DIMENSIONS" -> commandDimensions(args[0], args[1])
            "BG_COLOR" -> commandBackground(args[0], args[1], args[2])
            "CAMERA_POSITION" -> commandSetCameraPosition(args[0], args[1], args[2])
            "CAMERA_RIGHT" -> commandSetCameraRight(args[0], args[1], args[2])
            "CAMERA_UP" -> commmandSetCameraUp(args[0], args[1], args[2])
            "CAMERA_FORWARD" -> commandSetCameraForward(args[0], args[1], args[2])
            "CAMERA_FOV_X" -> commandSetFovX(args[0])
            "NEW_PRIMITIVE" -> commandCreatePrimitive()
            "PLANE" -> commandSetPlane(args[0], args[1], args[2])
            "ELLIPSOID" -> commandSetEllipsoid(args[0], args[1], args[2])
            "BOX" -> commandSetBox(args[0], args[1], args[2])
            "POSITION" -> commandSetPosition(args[0], args[1], args[2])
            "ROTATION" -> commandSetRotation(args[0], args[1], args[2])
            "COLOR" -> commandSetColor(args[0], args[1], args[2])
            // TODO add more commands
        }
    }
}

