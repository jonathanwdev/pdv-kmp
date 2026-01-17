package com.poc.feature.sale.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.total_due

@Composable
fun TotalBox(
    modifier: Modifier = Modifier,
    total: String,
    isLight: Boolean = false
) {
    Box(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .background(
                color = if(isLight) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 19.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.total_due),
                color = if(isLight) MaterialTheme.colorScheme.onSurface else  MaterialTheme.colorScheme.surface
            )
            Text(
                text = total,
                color = if(isLight) MaterialTheme.colorScheme.primary else  MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Black
            )
        }
    }
}