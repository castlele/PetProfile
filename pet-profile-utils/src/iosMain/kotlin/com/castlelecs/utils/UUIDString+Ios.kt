package com.castlelecs.utils

import platform.Foundation.NSUUID
actual fun generateUUIDString(): String = NSUUID().UUIDString()
