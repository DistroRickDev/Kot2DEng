package game_engine.loop
import game_engine.utils.Signal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

// @brief: Singleton class containing GameLoop tick
private object Ticker {
    val scope : CoroutineScope = CoroutineScope(Dispatchers.Default)
    var ticker : Flow<Unit> = flow {
        while (scope.isActive) {
            emit(Unit)
            delay(16)
        }
    }
}

object Clock : Signal<Double> {
    suspend fun init() {
       Ticker.ticker.collect { emit() }
    }

    fun stop() {
        Ticker.scope.cancel()
    }

    override fun emit() {
        if(subscriptions.isNotEmpty())
        {
            subscriptions.forEach {
                    subscription -> subscription(time)
            }
        }
    }

    override fun subscribe(callback: (Double) -> Unit) {
        subscriptions.add(callback)
    }
    private var subscriptions: MutableList<(Double)->Unit> = mutableListOf()
    private var time : Double = 1.0/60.0
}