package com.poc.feature.sale.screens.saleDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.components.avatars.ProductAvatar
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.components.buttons.PocPdvOutlineButton
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.sale.components.SalePriceDescriptionBox
import com.poc.feature.sale.components.SaleTopBar
import com.poc.feature.sale.components.TotalBox
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.edit_sale
import pocpdv.feature.sale.generated.resources.proceed_payment
import pocpdv.feature.sale.generated.resources.sale_details

@Composable
fun SaleDetailsRoot(
    koinScope: Scope,
    onNavigateBack: () -> Unit,
    onNavigateBackToHome: () -> Unit,
    onNavigateToPayment: () -> Unit,
    viewModel: SaleDetailsViewModel = koinViewModel<SaleDetailsViewModel>(scope = koinScope),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SaleDetailsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SaleDetailsAction.OnCancelClick -> onNavigateBackToHome()
                SaleDetailsAction.OnEditClick -> onNavigateBack()
                SaleDetailsAction.OnGoBackClick -> onNavigateBack()
                SaleDetailsAction.OnProceedClick -> onNavigateToPayment()
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
fun SaleDetailsScreen(
    state: SaleDetailsState,
    onAction: (SaleDetailsAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            SaleTopBar(
                onCancelMenuClick = {
                    onAction(SaleDetailsAction.OnCancelClick)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(SaleDetailsAction.OnGoBackClick)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = null
                        )
                    }

                },
                title = stringResource(Res.string.sale_details)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 15.dp)
        ) {
            TotalBox(
                total = state.totalAmountFormatted
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 12.dp)
            ) {
                items(
                    items = state.items,
                    key = { it.productSku }
                ) { item ->
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16 .dp)
                    ) {
                        ProductAvatar(
                            avatarUrl = item.imageUrl,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(43.dp)
                        )
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = item.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "${item.quantity}x ${item.totalPriceFormatted}",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Text(
                            text = item.totalPriceFormatted,
                            maxLines = 1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            SalePriceDescriptionBox(
                total = state.totalAmountFormatted,
                subtotal = state.subtotalAmountFormatted,
                tax = 7,
                totalTax = state.totalTaxFormatted
            ) {
                PocPdvButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.proceed_payment),
                    onClick = {
                        onAction(SaleDetailsAction.OnProceedClick)
                    }
                )
                Spacer(Modifier.height(10.dp))
                PocPdvOutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.edit_sale),
                    onClick = {
                        onAction(SaleDetailsAction.OnEditClick)
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
        SaleDetailsScreen(
            state = SaleDetailsState(),
            onAction = {}
        )
    }
}