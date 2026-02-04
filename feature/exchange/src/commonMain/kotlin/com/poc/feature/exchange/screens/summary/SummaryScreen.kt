package com.poc.feature.exchange.screens.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.components.common.Separator
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.presentation.utils.ObserveAsEvent
import com.poc.feature.exchange.components.ExchangeSteps
import com.poc.feature.exchange.components.ExchangeTopAppBar
import com.poc.feature.exchange.components.ReturnItemCard
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope
import pocpdv.feature.exchange.generated.resources.Res
import pocpdv.feature.exchange.generated.resources.balance_returned
import pocpdv.feature.exchange.generated.resources.complete_exchange_button
import pocpdv.feature.exchange.generated.resources.exchange_summary_title
import pocpdv.feature.exchange.generated.resources.returned_items_section_title
import pocpdv.feature.exchange.generated.resources.total_Sale_label
import pocpdv.feature.exchange.generated.resources.transaction_id_label

@Composable
fun SummaryRoot(
    koinScope: Scope,
    viewModel: SummaryViewModel = koinViewModel<SummaryViewModel>(scope = koinScope),
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvent(viewModel.event) { event ->
        when (event) {
            SummaryEvent.OnExchangeSuccess -> onNavigateToHome()
            SummaryEvent.OnExchangeError -> Unit
        }
    }

    SummaryScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SummaryAction.OnBackClick -> onNavigateBack()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    state: SummaryState,
    onAction: (SummaryAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            ExchangeTopAppBar(
                text = stringResource(Res.string.exchange_summary_title),
                onBackClick = {
                    onAction(SummaryAction.OnBackClick)
                }
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        spotColor = Color.Black.copy(alpha = 0.05f),
                        ambientColor = Color.Black.copy(alpha = 0.05f)
                    )
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
                color = MaterialTheme.colorScheme.surfaceVariant,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .padding(bottom = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PocPdvButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.complete_exchange_button),
                        icon = Icons.Default.CheckCircle,
                        onClick = { onAction(SummaryAction.OnCompleteExchangeClick) }
                    )

                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
        ) {
            ExchangeSteps(currentStep = 3)
            LazyColumn(
                modifier = Modifier.weight(1f),
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.returned_items_section_title),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                letterSpacing = 0.5.sp
                            )
                            Text(
                                text = "-${state.totalValueOfReturnFormatted}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                                        CircleShape
                                    )
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
                items(state.returnedItems) { item ->
                    ReturnItemCard(
                        isSummary = true,
                        item = item,
                        onAddItemClick = {},
                        onRemoveItemClick = {}
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
            Spacer(Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.total_Sale_label),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                            Text(
                                text = state.totalValueOfSaleFormatted,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(26.dp))
                        Separator()
                        Spacer(Modifier.height(26.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(Res.string.balance_returned),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.7f),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = state.totalValueOfReturnFormatted,
                                style = MaterialTheme.typography.displaySmall,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                letterSpacing = (-0.02).em
                            )
                        }

                    }
                }

            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "${stringResource(Res.string.transaction_id_label)}: ${state.transactionId}\n${state.formattedDate}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                lineHeight = 16.sp,
                letterSpacing = 1.sp
            )
            Spacer(Modifier.height(20.dp))
        }

    }
}


@Preview
@Composable
private fun SummaryScreenPreview() {
    PocPdvTheme {
        SummaryScreen(
            state = SummaryState(),
            onAction = {}
        )
    }
}
