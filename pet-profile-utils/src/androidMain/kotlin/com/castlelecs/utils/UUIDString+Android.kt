package com.castlelecs.utils

import java.util.UUID

actual fun generateUUIDString(): String = UUID.randomUUID().toString()
