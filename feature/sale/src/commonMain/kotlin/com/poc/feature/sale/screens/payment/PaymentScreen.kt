package com.poc.feature.sale.screens.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.poc.core.designsystem.components.buttons.CardButton
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.modifiers.drawShadowLine
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.feature.sale.components.SaleTopBar
import com.poc.feature.sale.components.TotalBox
import com.poc.feature.sale.models.PaymentListItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.charge_btn_with_price
import pocpdv.feature.sale.generated.resources.payment_type_cash
import pocpdv.feature.sale.generated.resources.payment_type_credit_card
import pocpdv.feature.sale.generated.resources.payment_type_debit_card
import pocpdv.feature.sale.generated.resources.payment_type_mobile
import pocpdv.feature.sale.generated.resources.sale_payment
import pocpdv.feature.sale.generated.resources.secure_payment

@Composable
fun PaymentRoot(
    koinScope: Scope,
    onNavigateBack: () -> Unit,
    onNavigateToSummary: () -> Unit,
    onNavigateBackToHome: () -> Unit,
    viewModel: PaymentViewModel = koinViewModel<PaymentViewModel>(scope = koinScope)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PaymentScreen(
        state = state,
        onAction = { action ->
            when (action) {
                PaymentAction.OnCancelClick -> onNavigateBackToHome()
                PaymentAction.OnChargeClick -> onNavigateToSummary()
                PaymentAction.OnGoBackClick -> onNavigateBack()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun PaymentScreen(
    state: PaymentState,
    onAction: (PaymentAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            SaleTopBar(
                onCancelMenuClick = {
                    onAction(PaymentAction.OnCancelClick)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(PaymentAction.OnGoBackClick)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = null
                        )
                    }

                },
                title = stringResource(Res.string.sale_payment)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(top = 15.dp),
        ) {
            TotalBox(
                isLight = true,
                total = state.totalValueFormatted
            )
            Spacer(Modifier.height(20.dp))
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    items = listOf(
                        PaymentListItem(
                            methodEnum = PaymentMethodEnum.DEBIT_CARD,
                            label = Res.string.payment_type_debit_card,
                            icon = Icons.Default.CreditCard,
                        ),
                        PaymentListItem(
                            methodEnum = PaymentMethodEnum.CREDIT_CARD,
                            label = Res.string.payment_type_credit_card,
                            icon = Icons.Default.CreditCard,
                        ),
                        PaymentListItem(
                            methodEnum = PaymentMethodEnum.MOBILE,
                            label = Res.string.payment_type_mobile,
                            icon = Icons.Default.CreditCard,
                        ),
                        PaymentListItem(
                            methodEnum = PaymentMethodEnum.CASH,
                            label = Res.string.payment_type_cash,
                            icon = Icons.Default.CreditCard,
                        )
                    )
                ) { item ->
                    CardButton(
                        modifier = Modifier.size(120.dp),
                        onClick = {
                            onAction(PaymentAction.OnPaymentMethodClick(item.methodEnum))
                        },
                        isSelected = item.methodEnum == state.paymentMethodSelected,
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = stringResource(item.label)
                            )
                        },
                        label = stringResource(item.label),
                    )
                }

                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            5.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            modifier = Modifier.size(17.dp),
                            contentDescription = stringResource(Res.string.payment_type_mobile)
                        )
                        Text(
                            text = stringResource(Res.string.secure_payment),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawShadowLine()
                    .padding(vertical = 16.dp, horizontal = 12.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                PocPdvButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.paymentMethodSelected != null,
                    text = stringResource(Res.string.charge_btn_with_price, state.totalValueFormatted),
                    onClick = {
                        onAction(PaymentAction.OnChargeClick)

                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PocPdvTheme {
        PaymentScreen(
            state = PaymentState(),
            onAction = {}
        )
    }
}