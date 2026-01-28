package com.poc.feature.exchange.screens.findSale

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.components.common.VirtualKeypad
import com.poc.core.designsystem.components.pickers.PocPdvDatePicker
import com.poc.core.designsystem.components.textfield.PocPdvTextField
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.exchange.components.ExchangeSteps
import com.poc.feature.exchange.components.ExchangeTopAppBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import pocpdv.feature.exchange.generated.resources.Res
import pocpdv.feature.exchange.generated.resources.barcode_scanner_description
import pocpdv.feature.exchange.generated.resources.exchange_title
import pocpdv.feature.exchange.generated.resources.find_transaction_button
import pocpdv.feature.exchange.generated.resources.receipt_id_label
import pocpdv.feature.exchange.generated.resources.receipt_id_placeholder
import pocpdv.feature.exchange.generated.resources.sale_date_label
import pocpdv.feature.exchange.generated.resources.search_transaction_description
import pocpdv.feature.exchange.generated.resources.search_transaction_title

@Composable
fun FindSaleRoot(
    viewModel: FindSaleViewModel = koinViewModel<FindSaleViewModel>(),
    onNavigateBackToHome: () -> Unit,
    onNavigateToSelectItems: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FindSaleScreen(
        state = state,
        onAction = { action ->
            when(action) {
                FindSaleAction.NavigateBack -> onNavigateBackToHome()
                FindSaleAction.OnFindTransactionClick -> onNavigateToSelectItems()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindSaleScreen(
    state: FindSaleState,
    onAction: (FindSaleAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
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
            ) {
                ExchangeSteps(currentStep = state.currentStep)
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
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        PocPdvTextField(
                            label = stringResource(Res.string.receipt_id_label),
                            value = state.receiptId,
                            onValueChange = { onAction(FindSaleAction.OnReceiptIdChanged(it)) },
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
                        PocPdvDatePicker(
                            label = stringResource(Res.string.sale_date_label),
                            selectedDate = state.saleDate,
                            onSelectedDateChange = { },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }
                }
            }
            Spacer(Modifier.height(22.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VirtualKeypad(
                    modifier = Modifier.weight(1f),
                    onNumberClick = { onAction(FindSaleAction.OnKeypadNumberClick(it)) },
                    onClearClick = { onAction(FindSaleAction.OnKeypadClearClick) },
                    onBackspaceClick = { onAction(FindSaleAction.OnKeypadBackspaceClick) }
                )
                PocPdvButton(
                    text = stringResource(Res.string.find_transaction_button),
                    icon = Icons.Default.Search,
                    onClick = { onAction(FindSaleAction.OnFindTransactionClick)},
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
            state = FindSaleState(currentStep = 1),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun FindSaleScreenStep2Preview() {
    PocPdvTheme {
        FindSaleScreen(
            state = FindSaleState(currentStep = 2),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun FindSaleScreenStep3Preview() {
    PocPdvTheme {
        FindSaleScreen(
            state = FindSaleState(currentStep = 3),
            onAction = {}
        )
    }
}
