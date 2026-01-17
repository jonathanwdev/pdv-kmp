package com.poc.core.designsystem.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

fun Modifier.drawShadowLine(): Modifier {
    return this.drawBehind {
        drawRect(
            color = Color.Black.copy(alpha = 0.1f),
            topLeft = Offset(0f, 0f),
            size = androidx.compose.ui.geometry.Size(size.width, 1.2.dp.toPx())
        )
    }.graphicsLayer {

    }

}