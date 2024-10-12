package com.castlelecs.utils.logger

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ptr
import platform.darwin.OS_LOG_DEFAULT
import platform.darwin.OS_LOG_TYPE_DEBUG
import platform.darwin.OS_LOG_TYPE_DEFAULT
import platform.darwin.OS_LOG_TYPE_ERROR
import platform.darwin.OS_LOG_TYPE_INFO
import platform.darwin.__dso_handle
import platform.darwin._os_log_internal
import platform.darwin.os_log_t

actual fun consoleLogger(system: String): Logger = ConsoleLogger(system)

@OptIn(ExperimentalForeignApi::class)
private class ConsoleLogger(
    private val system: String,
    private val logT: os_log_t = OS_LOG_DEFAULT,
) : Logger {

    override fun log(level: LogLevel, message: String) {
        _os_log_internal(
            __dso_handle.ptr,
            logT,
            level.toOSLogLevel(),
            "From system: %s",
            message,
        )
    }

    private fun LogLevel.toOSLogLevel(): UByte =
        when (this) {
            LogLevel.DEBUG -> OS_LOG_TYPE_DEBUG
            LogLevel.INFO -> OS_LOG_TYPE_INFO
            LogLevel.WARN -> OS_LOG_TYPE_DEFAULT
            LogLevel.ERROR -> OS_LOG_TYPE_ERROR
        }
}
