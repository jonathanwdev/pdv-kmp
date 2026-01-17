package com.poc.feature.sale.screens.saleDetails

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
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
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.edit_sale
import pocpdv.feature.sale.generated.resources.proceed_payment
import pocpdv.feature.sale.generated.resources.sale_details

@Composable
fun SaleDetailsRoot(
    viewModel: SaleDetailsViewModel = koinViewModel<SaleDetailsViewModel>(),
    onNavigateBack: () -> Unit,
    onNavigateToPayment: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SaleDetailsScreen(
        state = state,
        onAction = viewModel::onAction,
        onNavigateBack = onNavigateBack,
        onNavigateToPayment = onNavigateToPayment
    )
}

@Composable
fun SaleDetailsScreen(
    state: SaleDetailsState,
    onNavigateBack: () -> Unit,
    onNavigateToPayment: () -> Unit,
    onAction: (SaleDetailsAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
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
                title = stringResource(Res.string.sale_details)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(top = 15.dp)
        ) {
            TotalBox(
                total = "$35.00"
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 12.dp)
            ) {
                items(50) {
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
                            avatarUrl = "",
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(43.dp)
                        )
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Organic Coffee ",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "2 x $31.00",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Text(
                            text = "$62.21",
                            maxLines = 1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            SalePriceDescriptionBox(
                subtotal = 100,
                tax = 7
            ) {
                PocPdvButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.proceed_payment),
                    onClick = {
                        onNavigateToPayment()
                    }
                )
                Spacer(Modifier.height(10.dp))
                PocPdvOutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.edit_sale),
                    onClick = {
                        onNavigateBack()
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
            onNavigateBack = {},
            onNavigateToPayment = {},
            onAction = {}
        )
    }
}