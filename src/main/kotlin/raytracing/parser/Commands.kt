package raytracing.parser

import glm_.quat.Quat
import glm_.vec3.Vec3
import kotlin.math.atan
import kotlin.math.tan
import raytracing.scene.Primitive
import raytracing.scene.Scene
import raytracing.shape.Box
import raytracing.shape.Ellipsoid
import raytracing.shape.Plane

class Commands(var scene: Scene) {
    val commandDimensions = { width: Float, height: Float ->
        scene.width = width.toInt()
        scene.height = height.toInt()
    }

    val commandBackground = { red: Float, green: Float, blue: Float ->
        scene.bgColor = Vec3(red, green, blue)
    }

    val commandSetCameraPosition = { x: Float, y: Float, z: Float ->
        scene.camera.position = Vec3(x, y, z)
    }

    val commandSetCameraRight = { x: Float, y: Float, z: Float ->
        scene.camera.right = Vec3(x, y, z)
    }

    val commandSetCameraUp = { x: Float, y: Float, z: Float ->
        scene.camera.up = Vec3(x, y, z)
    }

    val commandSetCameraForward = { x: Float, y: Float, z: Float ->
        scene.camera.forward = Vec3(x, y, z)
    }

    val commandSetFovX = { angle: Float ->
        scene.camera.fovX = angle
        val aspectRatio = scene.width.toFloat() / scene.height
        scene.camera.fovY = 2 * atan( tan(angle / 2) / aspectRatio )
    }

    val commandCreatePrimitive = {
        scene.primitives.add(Primitive())
    }

    val commandSetPlane = { nx: Float, ny: Float, nz: Float ->
        scene.primitives.last().shape = Plane(Vec3(nx, ny, nz))
    }

    val commandSetEllipsoid = { rx: Float, ry: Float, rz: Float ->
        scene.primitives.last().shape = Ellipsoid(Vec3(rx, ry, rz))
    }

    val commandSetBox = { sx: Float, sy: Float, sz: Float ->
        scene.primitives.last().shape = Box(Vec3(sx, sy, sz))
    }

    val commandSetPosition = { x: Float, y: Float, z: Float ->
        scene.primitives.last().position = Vec3(x, y, z)
    }

    val commandSetRotation = { x: Float, y: Float, z: Float, w: Float ->
        scene.primitives.last().rotation = Quat(w, x, y, z)
    }

    val commandSetColor = { r: Float, g: Float, b: Float ->
        scene.primitives.last().color = Vec3(r, g, b)
    }

    fun parseCommand(command: String, args: List<Float>) {
        when (command) {
            "DIMENSIONS" -> commandDimensions(args[0], args[1])
            "BG_COLOR" -> commandBackground(args[0], args[1], args[2])
            "CAMERA_POSITION" -> commandSetCameraPosition(args[0], args[1], args[2])
            "CAMERA_RIGHT" -> commandSetCameraRight(args[0], args[1], args[2])
            "CAMERA_UP" -> commandSetCameraUp(args[0], args[1], args[2])
            "CAMERA_FORWARD" -> commandSetCameraForward(args[0], args[1], args[2])
            "CAMERA_FOV_X" -> commandSetFovX(args[0])
            "NEW_PRIMITIVE" -> commandCreatePrimitive()
            "PLANE" -> commandSetPlane(args[0], args[1], args[2])
            "ELLIPSOID" -> commandSetEllipsoid(args[0], args[1], args[2])
            "BOX" -> commandSetBox(args[0], args[1], args[2])
            "POSITION" -> commandSetPosition(args[0], args[1], args[2])
            "ROTATION" -> commandSetRotation(args[0], args[1], args[2], args[3])
            "COLOR" -> commandSetColor(args[0], args[1], args[2])
            // TODO add more commands
        }
    }
}

