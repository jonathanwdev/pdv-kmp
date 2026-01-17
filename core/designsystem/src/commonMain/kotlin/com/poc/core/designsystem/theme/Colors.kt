package com.poc.core.designsystem.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val surface = Color(0xFFF9F9F9)
val surfaceVariant = Color(0xFFF2F2F2)
val primary = Color(0xFF2ECC71)
val secondary = Color(0xFF34495E)
val tertiary = Color(0xFF1ABC9C)
val textSecondary = Color(0xFF4A4A4A)
val textTertiary = Color(0xFFA7AFB6)
val secondaryGreen = Color(0xFFd1fae5)


val error = Color(0xFFE74C3C)

val LightColorScheme = lightColorScheme(
    primary = primary,
    secondary = secondary,
    tertiary = tertiary,
    error = error,
    surface = surface,
    onSurface = Color.Black,
    surfaceVariant = surfaceVariant,
    onSurfaceVariant = textSecondary,
    inverseOnSurface = textTertiary
)




