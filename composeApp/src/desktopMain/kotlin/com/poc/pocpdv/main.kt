package com.poc.pocpdv

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PocPdv",
    ) {
        App()
    }
}