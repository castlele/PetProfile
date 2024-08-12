package com.castlelecs.utils.logger

import android.util.Log

actual fun consoleLogger(system: String): Logger = ConsoleLogger(system)

private class ConsoleLogger(private val system: String) : Logger {

    override fun log(level: LogLevel, message: String) {
        when (level) {
            LogLevel.DEBUG -> Log.d(system, message)
            LogLevel.INFO -> Log.i(system, message)
            LogLevel.WARN -> Log.w(system, message)
            LogLevel.ERROR -> Log.e(system, message)
        }
    }
}
