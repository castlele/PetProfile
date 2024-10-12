package com.castlelecs.utils.logger

interface Logger {
    fun log(level: LogLevel, message: String)
}

fun Logger.debug(message: String) {
    log(LogLevel.DEBUG, message)
}

fun Logger.info(message: String) {
    log(LogLevel.INFO, message)
}

fun Logger.warn(message: String) {
    log(LogLevel.WARN, message)
}

fun Logger.error(message: String) {
    log(LogLevel.ERROR, message)
}
