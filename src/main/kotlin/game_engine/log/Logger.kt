package game_engine.log

enum class LoggingType {
    Persistence,
    Stdio
}

private class PersistLogger(path: String) : ILogger {
    override fun log(msg: String) {
        TODO("Not yet implemented")
    }
}

private class StdOutLogger : ILogger {
    override fun log(msg: String) {
        println(msg)
    }
}

private enum class LoggingSeverity {
    Debug,
    Error,
    Info,
    Warning
}

object Logger {
    private fun getSerialisedCallerData() : String = Thread.currentThread().stackTrace[3].toString()
    private lateinit var logger: ILogger
    fun init(type: LoggingType = LoggingType.Stdio, path: String = "Kot2DEng.log") {
        if(this::logger.isInitialized)
        {
            logError("Logger already initialized")
            return
        }
        logger =  when (type) {
            LoggingType.Stdio -> StdOutLogger()
            LoggingType.Persistence -> PersistLogger(path)
        }
    }
    private fun assertLoggerInitialization()
    {
        if(!this::logger.isInitialized) {
            throw Exception("Logger must be initialized")
        }
    }
    fun logDebug(msg: String) {
        assertLoggerInitialization()
        logger.log("[${LoggingSeverity.Debug.name}] ${getSerialisedCallerData()} - $msg")
    }
    fun logError(msg: String) {
        assertLoggerInitialization()
        logger.log("[${LoggingSeverity.Error.name}] ${getSerialisedCallerData()} - $msg")
    }
    fun logInfo(msg: String) {
        assertLoggerInitialization()
        logger.log("[${LoggingSeverity.Info.name}] ${getSerialisedCallerData()} - $msg")
    }
    fun logWarning(msg: String) {
        assertLoggerInitialization()
        logger.log("[${LoggingSeverity.Warning.name}] ${getSerialisedCallerData()} - $msg")
    }
}
