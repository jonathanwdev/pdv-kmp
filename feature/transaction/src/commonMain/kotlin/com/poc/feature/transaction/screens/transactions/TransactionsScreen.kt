package com.poc.feature.transaction.screens.transactions

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.poc.feature.transaction.components.sampleTransactions
import com.poc.feature.transaction.models.TransactionsFilter
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import pocpdv.feature.transaction.generated.resources.Res
import pocpdv.feature.transaction.generated.resources.transactions


@Composable
fun TransactionsRoot(
    onNavigateToTransactionDetails: () -> Unit,
    onNavigateBackToHome: () -> Unit,
    viewModel: TransactionsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TransactionsScreen(
        onNavigateToTransactionDetails = onNavigateToTransactionDetails,
        onBackClick = onNavigateBackToHome,
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TransactionsScreen(
    state: TransactionsState,
    onAction: (TransactionsAction) -> Unit,
    onNavigateToTransactionDetails: () -> Unit ,
    onBackClick: () -> Unit,
) {
    PocPdvTheme {
        Scaffold(
            topBar = {
                TransactionsHistoryTopBar(
                    onBackClick = onBackClick,
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
                                onSelect = { },
                                isSelected = filter == TransactionsFilter.Today
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
                    sampleTransactions.forEach { section ->
                        stickyHeader {
                            TransactionItemHeader(
                                formattedDate = section.date
                            )
                        }
                        items(section.transactions) { transaction ->
                            TransactionInfoItem(
                                transaction = transaction,
                                onTransactionClick = onNavigateToTransactionDetails
                            )
                            if (transaction != section.transactions.last()) {
                                Separator()
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                SummaryTransactionCard()
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
            onBackClick = {},
            onNavigateToTransactionDetails = {}
        )
    }
}