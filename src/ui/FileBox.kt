package ui

import velvet.main.VGraphics
import velvet.structs.Bounds
import velvet.structs.Vector
import velvet.velements.container.ActuatedVContainer
import velvet.velements.container.VContainer
import velvet.velements.impl.SquareElement
import velvet.velements.impl.TextElement

class FileBox(filename: String) {

    val container = ActuatedVContainer(SquareElement())
    private val filenameContainer = VContainer(TextElement(filename, fontResolution = 40))

    fun update(){
        container.update()

        filenameContainer.bounds = container.bounds
                .subBounds(Bounds(Vector(0.1,0.7), Vector(0.9,0.9)))
                .fixRatio(filenameContainer.vElement?.size ?: Vector.ONE, Vector(0.5))
    }

    fun render(g: VGraphics){
        container.render(g)
        filenameContainer.render(g)
    }
}