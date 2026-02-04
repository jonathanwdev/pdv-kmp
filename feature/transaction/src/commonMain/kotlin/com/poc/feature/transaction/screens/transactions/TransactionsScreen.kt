package com.poc.feature.transaction.screens.transactions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.components.buttons.FilterChip
import com.poc.core.designsystem.components.common.Separator
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.transaction.components.SummaryTransactionCard
import com.poc.feature.transaction.components.TransactionInfoItem
import com.poc.feature.transaction.components.TransactionItemHeader
import com.poc.feature.transaction.components.TransactionsHistoryTopBar
import com.poc.feature.transaction.models.TransactionsFilter
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import pocpdv.feature.transaction.generated.resources.Res
import pocpdv.feature.transaction.generated.resources.transactions


@Composable
fun TransactionsRoot(
    onNavigateToTransactionDetails: (Long) -> Unit,
    onNavigateBackToHome: () -> Unit,
    viewModel: TransactionsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TransactionsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                TransactionsAction.OnBackClick -> onNavigateBackToHome()
                is TransactionsAction.OnTransactionClick -> onNavigateToTransactionDetails(action.transactionId)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TransactionsScreen(
    state: TransactionsState,
    onAction: (TransactionsAction) -> Unit,
) {
    val composition by rememberLottieComposition() {
        LottieCompositionSpec.JsonString(
            designsystem.resources.Res.readBytes("drawable/empty.json")
                .decodeToString()
        )
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Compottie.IterateForever
    )

    PocPdvTheme {
        Scaffold(
            topBar = {
                TransactionsHistoryTopBar(
                    onBackClick = {
                        onAction(TransactionsAction.OnBackClick)
                    },
                )
            },
            containerColor = MaterialTheme.colorScheme.surface
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.transactions),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TransactionsFilter.entries.forEach { filter ->
                            FilterChip(
                                text = filter.name,
                                onSelect = {
                                    onAction(TransactionsAction.OnFilterClick(filter))
                                },
                                isSelected = filter == state.filterSelected
                            )
                        }
                    }
                    Separator()
                }
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.surface),
                ) {
                    when {
                        state.filteredTransactions.isEmpty() -> {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = rememberLottiePainter(
                                            composition = composition,
                                            progress = { progress },
                                        ),
                                        contentDescription = "Lottie animation"
                                    )
                                }
                            }
                        }

                        else -> {
                            state.filteredTransactions.forEach { section ->
                                val (formattedDateKey, transactionListForSection) = section.entries.first()
                                stickyHeader {
                                    TransactionItemHeader(
                                        formattedDate = formattedDateKey
                                    )
                                }
                                items(
                                    items = transactionListForSection,
                                ) { transaction ->
                                    TransactionInfoItem(
                                        transaction = transaction,
                                        onTransactionClick = { id ->
                                            onAction(TransactionsAction.OnTransactionClick(id))
                                        }
                                    )
                                    if (transaction != transactionListForSection.last()) {
                                        Separator()
                                    }
                                }
                            }
                        }
                    }


                }
                Spacer(modifier = Modifier.height(16.dp))
                SummaryTransactionCard(
                    dailyGoalFormatted = state.dailyGoalFormatted,
                    dailyPercentageAchieved = state.dailyPercentageAchieved,
                    totalValueFormatted = state.totalValueFormatted
                )
            }

        }
    }
}


@Preview
@Composable
private fun TransactionsScreenPreview() {
    PocPdvTheme {
        TransactionsScreen(
            state = TransactionsState(),
            onAction = {},
        )
    }
}