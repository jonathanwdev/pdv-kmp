package com.poc.feature.exchange.screens.findSale

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.components.common.VirtualKeypad
import com.poc.core.designsystem.components.textfield.PocPdvTextField
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.presentation.utils.ObserveAsEvent
import com.poc.feature.exchange.components.ExchangeSteps
import com.poc.feature.exchange.components.ExchangeTopAppBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope
import pocpdv.feature.exchange.generated.resources.Res
import pocpdv.feature.exchange.generated.resources.barcode_scanner_description
import pocpdv.feature.exchange.generated.resources.exchange_title
import pocpdv.feature.exchange.generated.resources.find_transaction_button
import pocpdv.feature.exchange.generated.resources.receipt_id_label
import pocpdv.feature.exchange.generated.resources.receipt_id_placeholder
import pocpdv.feature.exchange.generated.resources.sale_not_found
import pocpdv.feature.exchange.generated.resources.search_transaction_description
import pocpdv.feature.exchange.generated.resources.search_transaction_title

@Composable
fun FindSaleRoot(
    koinScope: Scope,
    viewModel: FindSaleViewModel = koinViewModel<FindSaleViewModel>(scope = koinScope),
    onNavigateBackToHome: () -> Unit,
    onNavigateToSelectItems: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val notFoundMessage = stringResource(Res.string.sale_not_found)

    ObserveAsEvent(viewModel.event) { event ->
        when(event) {
            FindSaleEvent.SaleFound -> onNavigateToSelectItems()
            FindSaleEvent.SaleNotFound -> {
                snackbarHostState.showSnackbar(notFoundMessage)
            }
        }
    }

    FindSaleScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onAction = { action ->
            when (action) {
                FindSaleAction.NavigateBack -> onNavigateBackToHome()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindSaleScreen(
    state: FindSaleState,
    snackbarHostState: SnackbarHostState,
    onAction: (FindSaleAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            ExchangeTopAppBar(
                text = stringResource(Res.string.exchange_title),
                onBackClick = {
                    onAction(FindSaleAction.NavigateBack)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                ExchangeSteps(currentStep = 1)
                Spacer(Modifier.height(22.dp))
                Text(
                    text = stringResource(Res.string.search_transaction_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = stringResource(Res.string.search_transaction_description),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(Modifier.height(22.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = .3f)
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp).padding(bottom = 30.dp, top = 10.dp),
                    ) {
                        PocPdvTextField(
                            label = stringResource(Res.string.receipt_id_label),
                            value = state.receiptId,
                            onValueChange = { },
                            placeholder = stringResource(Res.string.receipt_id_placeholder),
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.QrCodeScanner,
                                    contentDescription = stringResource(Res.string.barcode_scanner_description),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VirtualKeypad(
                    modifier = Modifier.padding(bottom = 10.dp).weight(1f),
                    onNumberClick = { onAction(FindSaleAction.OnKeypadNumberClick(it)) },
                    onClearClick = { onAction(FindSaleAction.OnKeypadClearClick) },
                    onBackspaceClick = { onAction(FindSaleAction.OnKeypadBackspaceClick) }
                )
                PocPdvButton(
                    text = stringResource(Res.string.find_transaction_button),
                    icon = Icons.Default.Search,
                    enabled = state.receiptId.length > 5,
                    onClick = { onAction(FindSaleAction.OnFindTransactionClick) },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }
}

@Preview
@Composable
private fun FindSaleScreenPreview() {
    PocPdvTheme {
        FindSaleScreen(
            state = FindSaleState(),
            snackbarHostState = remember { SnackbarHostState() },
            onAction = {}
        )
    }
}


