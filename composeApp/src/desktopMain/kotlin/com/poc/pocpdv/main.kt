package com.poc.pocpdv

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.poc.pocpdv.di.initKoin

fun main() {
    initKoin {
        printLogger()
    }
    application {
        Window(
            state = rememberWindowState(
                width = 1200.dp,
                height = 840.dp
            ),
            onCloseRequest = ::exitApplication,
            title = "PocPdv",
        ) {
            App()
        }
    }

}