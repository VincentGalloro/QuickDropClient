package ui

import velvet.main.VGraphics
import velvet.structs.Vector
import velvet.velements.container.VContainer
import velvet.velements.impl.SquareElement
import velvet.velements.impl.TextElement

class Button(text: String) {

    val container = VContainer(SquareElement(outlineThickness = 2.0, rounding = 20.0))
    private val textContainer = VContainer(TextElement(text, fontResolution = 40))

    fun update(){
        if(container.disabled){ return; }

        textContainer.bounds = container.bounds
                .fixRatio(textContainer.vElement?.size ?: Vector.ONE, Vector(0.5))
                .scale(Vector(0.9), Vector(0.5))
    }

    fun render(g: VGraphics){
        if(container.disabled){ return; }

        container.render(g)
        textContainer.render(g)
    }
}