package ui

import velvet.main.VGraphics
import velvet.structs.Bounds
import velvet.structs.Vector
import velvet.velements.interact.UIEventHandler

class FileView(private val uiEventHandler: UIEventHandler) {

    private val fileBoxes: MutableList<FileBox> = ArrayList()
    private var hoverIndex: Int? = null

    fun addFileBox(fileBox: FileBox){
        val index = fileBoxes.size
        fileBoxes.add(fileBox)

        uiEventHandler.containers.add(fileBox.container)
        fileBox.container.uiEventListener.onHoverStart = { hoverIndex = index }
        fileBox.container.uiEventListener.onFocusEnd = { hoverIndex = null }
    }

    fun clearFileBoxes(){
        fileBoxes.forEach { uiEventHandler.containers.remove(it.container) }
        fileBoxes.clear()
    }

    fun update(){
        for (i in 0 until fileBoxes.size){
            var size = Vector(200)
            if(hoverIndex?.equals(i) == true){
                size = Vector(230)
            }
            fileBoxes[i].container.layoutActuator.targetBounds =
                    Bounds(Vector(200) + Vector(250,0)*i).resize(size, Vector(0.5))
        }

        fileBoxes.forEach { it.update() }
    }

    fun render(g: VGraphics){
        fileBoxes.forEach { it.render(g) }
    }
}