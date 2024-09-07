import game_engine.graphics.Window
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import game_engine.log.Logger
import game_engine.loop.GameLoop

suspend fun physicsProcess(delta: Double)
{
    Logger.logDebug("Physics Process Elapsed time $delta")
}

suspend fun process(delta: Double)
{
   Logger.logDebug("Process Elapsed time $delta")
}

fun main(): Unit = runBlocking { // this: CoroutineScope
    Logger.init()
    launch {
        GameLoop.physicsFunction = ::physicsProcess
        GameLoop.processFunction = ::process
        GameLoop.run()
    }
    delay(1000)
    GameLoop.stop()
    Logger.logInfo("MainThread finished")
}

