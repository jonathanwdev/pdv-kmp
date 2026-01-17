package com.poc.core.common

enum class DesktopOs {
    WINDOWS,
    MACOS,
    LINUX
}

val currentOs: DesktopOs
    get() {
        val osName = System.getProperty("os.name").lowercase()
        return when {
            osName.contains("win") -> DesktopOs.WINDOWS
            osName.contains("mac") -> DesktopOs.MACOS
            else -> DesktopOs.LINUX
        }
    }