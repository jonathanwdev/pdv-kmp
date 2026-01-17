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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.MobileFriendly
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poc.core.designsystem.components.buttons.CardButton
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.modifiers.drawShadowLine
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.sale.components.SaleTopBar
import com.poc.feature.sale.components.TotalBox
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
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
    onNavigateBack: () -> Unit,
    onNavigateToSummary: () -> Unit,
    viewModel: PaymentViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PaymentScreen(
        onNavigateBack = onNavigateBack,
        onNavigateToSummary = onNavigateToSummary,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun PaymentScreen(
    onNavigateBack: () -> Unit,
    onNavigateToSummary: () -> Unit,
    state: PaymentState,
    onAction: (PaymentAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            SaleTopBar(
                onCancelMenuClick = {},
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
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
                total = "$35.00"
            )
            Spacer(Modifier.height(20.dp))
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item {
                    CardButton(
                        modifier = Modifier.size(120.dp),
                        onClick = {},
                        isSelected = true,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.CreditCard,
                                contentDescription = stringResource(Res.string.payment_type_debit_card)
                            )
                        },
                        label = stringResource(Res.string.payment_type_debit_card),
                    )
                }
                item {
                    CardButton(
                        modifier = Modifier.size(120.dp),
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.CreditCard,
                                contentDescription = stringResource(Res.string.payment_type_credit_card)
                            )
                        },
                        label = stringResource(Res.string.payment_type_credit_card),
                    )
                }
                item {
                    CardButton(
                        modifier = Modifier.size(120.dp),
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AttachMoney,
                                contentDescription = stringResource(Res.string.payment_type_cash)
                            )
                        },
                        label = stringResource(Res.string.payment_type_cash),
                    )
                }
                item {
                    CardButton(
                        modifier = Modifier.size(120.dp),
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.MobileFriendly,
                                contentDescription = stringResource(Res.string.payment_type_mobile)
                            )
                        },
                        label = stringResource(Res.string.payment_type_mobile),
                    )
                }
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
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
                    .padding(vertical = 16.dp, horizontal = 12.dp)
                ,
                contentAlignment = Alignment.TopCenter,
            ) {
                PocPdvButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.charge_btn_with_price, "$33"),
                    onClick = onNavigateToSummary
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
            onNavigateBack = {},
            onNavigateToSummary = {},
            onAction = {}
        )
    }
}