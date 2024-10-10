package com.castlelecs.utils.logger

fun compositeLogger(system: String): Logger =
    CompositeLogger(
        system = system,
        loggers = listOf(printLogger(), consoleLogger(system)),
    )

private class CompositeLogger(
    private val system: String,
    private val loggers: List<Logger>,
) : Logger {

    override fun log(level: LogLevel, message: String) {
        loggers.forEach { it.log(level, message) }
    }
}
