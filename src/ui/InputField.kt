package ui

import velvet.main.VGraphics
import velvet.structs.Bounds
import velvet.structs.Vector
import velvet.velements.container.ActuatedVContainer
import velvet.velements.container.VContainer
import velvet.velements.impl.SquareElement
import velvet.velements.impl.TextElement
import velvet.velements.interact.UIEventHandler
import velvet.velements.ui.TextController
import java.awt.Color

class InputField(uiEventHandler: UIEventHandler, promptText: String) {

    val container = VContainer(SquareElement(outlineThickness = 2.0, rounding = 20.0))
    private val promptContainer = VContainer(TextElement(promptText, _color = Color(200,200,200)))
    private val inputContainer = VContainer(TextElement(promptText, fontResolution = 40))

    val text: String
        get() = (inputContainer.vElement as TextElement).text

    init {
        uiEventHandler.containers.add(container)

        val controller = TextController(inputContainer.vElement as TextElement)
        container.uiEventListener.onCharTyped = controller::onCharTyped
        container.uiEventListener.onKeyPressed = controller::onKeyPressed

        container.uiEventListener.onFocusStart = { (container.vElement as SquareElement).outlineColor = Color(150, 150, 150) }
        container.uiEventListener.onFocusEnd = { (container.vElement as SquareElement).outlineColor = Color.BLACK }
    }

    fun update(){
        if(container.disabled){ return; }
        promptContainer.bounds = container.bounds
                .scale(Vector(0.3,1.0), Vector(0.0,0.5))
                .fixRatio(promptContainer.vElement?.size ?: Vector.ONE, Vector(0.5))
                .scale(Vector(0.8), Vector(0.5))
        inputContainer.bounds = container.bounds
                .scale(Vector(0.7,1.0), Vector(1.0,0.5))
                .fixRatio(inputContainer.vElement?.size ?: Vector.ONE, Vector(0.0, 0.5))
                .scale(Vector(0.9), Vector(0.0, 0.5))
    }

    fun render(g: VGraphics){
        if(container.disabled){ return; }
        container.render(g)
        promptContainer.render(g)
        inputContainer.render(g)
    }
}