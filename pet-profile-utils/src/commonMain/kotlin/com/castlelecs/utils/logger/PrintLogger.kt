package com.castlelecs.utils.logger

class PrintLogger internal constructor() : Logger {

    override fun log(level: LogLevel, message: String) {
        println("[${level.name}]: $message")
    }
}

fun printLogger() = PrintLogger()
