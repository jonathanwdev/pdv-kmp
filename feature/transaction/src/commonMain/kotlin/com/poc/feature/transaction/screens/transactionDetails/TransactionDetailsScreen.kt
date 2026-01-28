package com.poc.feature.transaction.screens.transactionDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.components.common.Separator
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.transaction.components.Metadata
import com.poc.feature.transaction.components.SaleStatusChip
import com.poc.feature.transaction.components.SaleStatusChipEnum
import com.poc.feature.transaction.components.TransactionDetailListItem
import com.poc.feature.transaction.components.TransactionDetailsBottomBar
import com.poc.feature.transaction.components.TransactionDetailsTopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import pocpdv.feature.transaction.generated.resources.Res
import pocpdv.feature.transaction.generated.resources.cashier
import pocpdv.feature.transaction.generated.resources.date
import pocpdv.feature.transaction.generated.resources.discount
import pocpdv.feature.transaction.generated.resources.items_purchased
import pocpdv.feature.transaction.generated.resources.subtotal
import pocpdv.feature.transaction.generated.resources.tax
import pocpdv.feature.transaction.generated.resources.time
import pocpdv.feature.transaction.generated.resources.total_amount
import pocpdv.feature.transaction.generated.resources.transaction_id

@Composable
fun TransactionDetailsRoot(
    viewModel: TransactionDetailsViewModel = koinViewModel(),
    onNavigateBackToTransactions: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TransactionDetailsScreen(
        state = state,
        onAction ={ action ->
            when(action) {
                TransactionDetailsAction.NavigateBack -> onNavigateBackToTransactions()
                else -> Unit
            }
            viewModel.onAction(action)
        },

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailsScreen(
    state: TransactionDetailsState,
    onAction: (TransactionDetailsAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            TransactionDetailsTopBar(
                onNavigateBack = { onAction(TransactionDetailsAction.NavigateBack) }
            )
        },
        bottomBar = {
            TransactionDetailsBottomBar(
                onReprintClick = { onAction(TransactionDetailsAction.ReprintReceipt) },
                onIssueRefoundClick = { onAction(TransactionDetailsAction.IssueRefund) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text(
                                text = stringResource(Res.string.transaction_id).uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.inverseOnSurface
                            )
                            Text(
                                text = state.transactionId,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        SaleStatusChip(
                            status = SaleStatusChipEnum.COMPLETED
                        )
                    }
                    Separator(Modifier.padding(vertical = 16.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Metadata(
                            icon = Icons.Outlined.CalendarToday,
                            label = stringResource(Res.string.date),
                            value = state.date,
                            modifier = Modifier.weight(1f)
                        )
                        Metadata(
                            icon = Icons.Outlined.Schedule,
                            label = stringResource(Res.string.time),
                            value = state.time,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    Metadata(
                        icon = Icons.Outlined.Person,
                        label = stringResource(Res.string.cashier),
                        value = state.cashierName
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.items_purchased).uppercase(),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                letterSpacing = 1.sp
            )
            Spacer(Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 280.dp)
                ) {
                    itemsIndexed(
                        items = state.items
                    ) { index, item ->
                        TransactionDetailListItem(item = item)
                        if (index < state.items.lastIndex) {
                            Separator(Modifier.padding(horizontal = 16.dp))
                        }
                    }

                }
            }
            Spacer(Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SummaryRow(stringResource(Res.string.subtotal), "$${state.subtotal}")
                    SummaryRow(stringResource(Res.string.tax), "$${state.tax}")
                    SummaryRow(
                        label = "${stringResource(Res.string.discount)} (${state.discountLabel})",
                        value = "-$${state.discount}",
                        valueColor = MaterialTheme.colorScheme.error
                    )
                    Separator()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(Res.string.total_amount),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$${state.total}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }

    }
}


@Composable
private fun SummaryRow(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inverseOnSurface
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = valueColor
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PocPdvTheme {
        TransactionDetailsScreen(
            state = TransactionDetailsState(),
            onAction = {}
        )
    }
}
