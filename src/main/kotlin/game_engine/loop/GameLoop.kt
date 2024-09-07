package game_engine.loop

import kotlinx.coroutines.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object GameLoop {
    private val gameLoopScope : CoroutineScope = CoroutineScope(CoroutineName("GameLoop Scope") + Dispatchers.Default)
    var physicsFunction : suspend (Double) -> Unit = {}
    var processFunction : suspend (Double) -> Unit = {}

    fun run() = gameLoopScope.launch {
            Clock.subscribe {
                time -> launch { physicsFunction(time)}
            }
            Clock.subscribe {
                time -> launch {processFunction(time)}
            }
            Clock.init()
    }

    fun stop()
    {
        gameLoopScope.cancel()
        Clock.stop()
    }
}