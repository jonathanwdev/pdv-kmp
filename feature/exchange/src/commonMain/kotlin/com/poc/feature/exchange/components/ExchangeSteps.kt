package com.poc.feature.exchange.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pocpdv.feature.exchange.generated.resources.Res
import pocpdv.feature.exchange.generated.resources.find_sale_step
import pocpdv.feature.exchange.generated.resources.select_items_step
import pocpdv.feature.exchange.generated.resources.summary_step

@Composable
fun ExchangeSteps(
    modifier: Modifier = Modifier,
    currentStep: Int,
) {
    val steps = listOf(
        stringResource(Res.string.find_sale_step),
        stringResource(Res.string.select_items_step),
        stringResource(Res.string.summary_step)
    )

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            steps.forEachIndexed { index, _ ->
                val isCurrent = index + 1 == currentStep
                val isCompleted = index + 1 < currentStep
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .clip(CircleShape)
                        .background(if (isCurrent || isCompleted) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = .6f))
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            steps.forEachIndexed { index, stepText ->
                val isCurrent = index + 1 == currentStep
                val isCompleted = index + 1 < currentStep
                Text(
                    text = stepText.uppercase(),
                    modifier = Modifier.padding(vertical = 8.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = if (isCurrent || isCompleted) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }
    }
}

@Preview
@Composable
private fun ExchangeStepsPreview() {
    PocPdvTheme {
        ExchangeSteps(
            currentStep = 1
        )
    }
}