package com.poc.core.database.utils

import com.poc.core.common.DesktopOs
import com.poc.core.common.currentOs
import java.io.File


val appDataDirectory: File
    get() {
        val userHome = System.getProperty("user.home")
        return when(currentOs) {
            DesktopOs.WINDOWS -> File(System.getenv("APPDATA"), "PocPdv")
            DesktopOs.MACOS -> File(userHome, "Library/Application Support/PocPdv")
            DesktopOs.LINUX -> File(userHome, ".local/share/PocPdv")
        }
    }