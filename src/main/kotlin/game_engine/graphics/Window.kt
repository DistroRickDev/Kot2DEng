package game_engine.graphics

import java.awt.Dimension
import java.awt.Frame

object Window {
    private lateinit var frame : Frame
    fun create(title: String = "GameTitle", width: Int = 640, height: Int = 480, isVisible: Boolean = true)
    {
        if(!this::frame.isInitialized)
        {
            frame = Frame()
            frame.name = title
            frame.size = Dimension(width, height)
            frame.isVisible = isVisible
        }
    }
    fun destroy() {
        frame.dispose()
    }
}