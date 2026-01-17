package com.poc.feature.sale.screens.bag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.poc.core.designsystem.components.buttons.SumButton
import com.poc.core.designsystem.components.buttons.SumButtonVariant
import com.poc.core.designsystem.components.textfield.PocPdvTextField
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.sale.components.SalePriceDescriptionBox
import com.poc.feature.sale.components.SaleTopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.charge_btn_with_price
import pocpdv.feature.sale.generated.resources.new_sale
import pocpdv.feature.sale.generated.resources.scan_product

@Composable
fun BagRoot(
    viewModel: BagViewModel = koinViewModel<BagViewModel>(),
    onNavigateToSaleDetails: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BagScreen(
        state = state,
        onNavigateToSaleDetails = onNavigateToSaleDetails,
        onAction = viewModel::onAction
    )
}

@Composable
fun BagScreen(
    state: BagState,
    onNavigateToSaleDetails: () -> Unit,
    onAction: (BagAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            SaleTopBar(
                onCancelMenuClick = {},
                title = stringResource(Res.string.new_sale)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            PocPdvTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(top = 22.dp, bottom = 16.dp),
                value = "",
                placeholder = stringResource(Res.string.scan_product),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                },
                onValueChange = {},
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 12.dp)
            ) {
                items(5) {
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 1.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(vertical = 14.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16 .dp)
                        ) {
                            ProductAvatar(
                                avatarUrl = "",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .size(60.dp)
                            )
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Organic Coffee dwwdw dwjdjwdw dwdwd",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "SKU: 123456",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = MaterialTheme.colorScheme.inverseOnSurface,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "$33.00",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                SumButton(
                                    variant = SumButtonVariant.SUB,
                                    onClick = {}
                                )
                                Text(
                                    text = "2",
                                    fontWeight = FontWeight.SemiBold
                                )
                                SumButton(
                                    variant = SumButtonVariant.ADD,
                                    onClick = {}
                                )
                            }
                        }
                    }
                }
            }
            SalePriceDescriptionBox(
                subtotal = 100,
                tax = 7
            ) {
                PocPdvButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.charge_btn_with_price, "33"),
                    onClick = onNavigateToSaleDetails
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PocPdvTheme {
        BagScreen(
            state = BagState(),
            onNavigateToSaleDetails = {},
            onAction = {}
        )
    }
}