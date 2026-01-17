package com.poc.pocpdv

import androidx.compose.runtime.Composable
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.pocpdv.navigation.RootNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    PocPdvTheme {
        RootNavigation()
    }
}