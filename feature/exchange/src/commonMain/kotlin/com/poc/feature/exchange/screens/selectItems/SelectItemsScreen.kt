package com.poc.feature.exchange.screens.selectItems

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.presentation.utils.ObserveAsEvent
import com.poc.core.presentation.utils.currentDeviceConfiguration
import com.poc.feature.exchange.components.ExchangeSteps
import com.poc.feature.exchange.components.ExchangeTopAppBar
import com.poc.feature.exchange.components.ReturnItemCard
import com.poc.feature.exchange.components.SelectItemsBottomBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope
import pocpdv.feature.exchange.generated.resources.Res
import pocpdv.feature.exchange.generated.resources.original_transaction_items
import pocpdv.feature.exchange.generated.resources.return_window_note
import pocpdv.feature.exchange.generated.resources.select_returns_title
import pocpdv.feature.exchange.generated.resources.transaction_id_label

@Composable
fun SelectItemsRoot(
    koinScope: Scope,
    viewModel: SelectItemsViewModel = koinViewModel<SelectItemsViewModel>(scope = koinScope),
    onNavigateBack: () -> Unit,
    onNavigateToSummary: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvent(viewModel.event) { event ->
        when (event) {
            SelectItemsEvent.OnSelectItemsSuccess -> onNavigateToSummary()
        }
    }

    SelectItemsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SelectItemsAction.OnNavigateBack -> onNavigateBack()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SelectItemsScreen(
    state: SelectItemsState,
    onAction: (SelectItemsAction) -> Unit,
) {
    val listToFooter by remember(state.selectedSaleProducts) { derivedStateOf { state.selectedSaleProducts.filter { it.quantitySelected > 0 } } }
    val isMobile = currentDeviceConfiguration().isMobile

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            ExchangeTopAppBar(
                text = stringResource(Res.string.select_returns_title),
                onBackClick = { onAction(SelectItemsAction.OnNavigateBack) }
            )
        },
        bottomBar = {
            SelectItemsBottomBar(
                selectedItems = listToFooter,
                totalValueFormatted = state.totalPriceFormatted,
                enabledButton = state.enableSkip,
                onConfirmClick = {
                    onAction(SelectItemsAction.OnSelectReplacementItems)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 0.dp)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(if (isMobile) 1f else 0.7f)

            ) {
                ExchangeSteps(currentStep = 2)
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    stickyHeader {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 4.dp)
                                .padding(bottom = 16.dp)
                                .padding(top = 16.dp)
                        ) {
                            Text(
                                text = stringResource(Res.string.original_transaction_items),
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f),
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "${stringResource(Res.string.transaction_id_label)}: ${state.transactionId}",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                    items(state.selectedSaleProducts) { item ->
                        ReturnItemCard(
                            item = item,
                            onAddItemClick = { sku ->
                                onAction(SelectItemsAction.OnAddItemClick(sku))
                            },
                            onRemoveItemClick = { sku ->
                                onAction(SelectItemsAction.OnRemoveItemClick(sku))
                            }
                        )
                        Spacer(Modifier.height(12.dp))
                    }

                }
                Text(
                    text = stringResource(Res.string.return_window_note),
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                Spacer(Modifier.height(40.dp))
            }

        }

    }
}


@Preview
@Composable
private fun SelectItemsScreenPreview() {
    PocPdvTheme {
        SelectItemsScreen(
            state = SelectItemsState(),
            onAction = {}
        )
    }
}
