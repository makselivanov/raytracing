import raytracing.parser.SceneParser

fun main(args: Array<String>) {
    val inputDescriptionPath = args[0]
    val outputPicturePath = args[1]
    val scene = SceneParser.parse(inputDescriptionPath)
    val renderedScene = scene.render()
    renderedScene.save(outputPicturePath)
}