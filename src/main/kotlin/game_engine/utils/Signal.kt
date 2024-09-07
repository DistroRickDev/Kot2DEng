package game_engine.utils

/// @brief: Interface to create observer pattern
interface Signal<T> {
    fun emit() : Unit
    fun subscribe(callback: (T)-> Unit) : Unit
}