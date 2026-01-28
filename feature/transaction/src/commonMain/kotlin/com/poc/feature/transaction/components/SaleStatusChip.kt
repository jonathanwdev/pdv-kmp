package com.poc.feature.transaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class SaleStatusChipEnum {
    COMPLETED,
    REFUNDED
}

@Composable
fun SaleStatusChip(
    modifier: Modifier = Modifier,
    status: SaleStatusChipEnum
) {
    val isSaleCompleted = status == SaleStatusChipEnum.COMPLETED
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(
                if (!isSaleCompleted) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.1f
                )
            )
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = status.name.uppercase(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (!isSaleCompleted) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.tertiary
        )
    }
}