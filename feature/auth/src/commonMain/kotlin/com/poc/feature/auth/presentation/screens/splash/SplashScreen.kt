package com.poc.feature.auth.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashRoot(
    onNavigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000)
        onNavigateToHome()
    }

    SplashView()
}


@Composable
fun SplashView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier.size(70.dp)
        )
        Text(
            text = "FastPos",
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surface,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
@Preview
private fun SplashRootPreview() {
    SplashRoot(
        onNavigateToHome = {}
    )
}