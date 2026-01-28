package com.poc.feature.sale.screens.bag

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.charge_btn_with_price
import pocpdv.feature.sale.generated.resources.new_sale
import pocpdv.feature.sale.generated.resources.scan_product

@Composable
fun BagRoot(
    koinScope: Scope,
    onNavigateToSaleDetails: () -> Unit,
    onNavigateBackToHome: () -> Unit,
    viewModel: BagViewModel = koinViewModel<BagViewModel>(scope = koinScope)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BagScreen(
        state = state,
        onAction = { action ->
            when (action) {
                BagAction.OnChargeClick -> {
                    onNavigateToSaleDetails()
                }
                BagAction.OnCancelClick -> {
                    onNavigateBackToHome()
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun BagScreen(
    state: BagState,
    onAction: (BagAction) -> Unit,
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("drawable/empty.json").decodeToString()
        )
    }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Compottie.IterateForever
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            SaleTopBar(
                onCancelMenuClick = {
                    onAction(BagAction.OnCancelClick)
                },
                title = "${stringResource(Res.string.new_sale)} #${state.saleId}"
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
                value = state.productSku,
                placeholder = stringResource(Res.string.scan_product),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onAction(BagAction.OnSearchProduct)
                        }
                    )
                },
                onValueChange = {
                    onAction(BagAction.OnChangeSkuInput(it))
                },
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 12.dp)
            ) {
                when {
                    state.items.isEmpty() -> {
                        item {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = rememberLottiePainter(
                                        composition = composition,
                                        progress = { progress },
                                    ),
                                    contentDescription = "Lottie animation"
                                )
                            }
                        }
                    }

                    else -> {
                        items(
                            items = state.items,
                        ) { item ->
                            Card(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 1.dp
                                ),
                                modifier = Modifier.animateItem(
                                    fadeInSpec = tween(500),
                                    placementSpec = spring(stiffness = Spring.DampingRatioNoBouncy),
                                    fadeOutSpec = spring(stiffness = Spring.StiffnessLow)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.surface)
                                        .padding(vertical = 14.dp, horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    ProductAvatar(
                                        avatarUrl = item.imageUrl,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .size(60.dp)
                                    )
                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = item.name,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "SKU: ${item.productSku}",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            color = MaterialTheme.colorScheme.inverseOnSurface,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = item.totalPriceFormatted,
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
                                            onClick = {
                                                onAction(BagAction.OnRemoveItems(item.productSku))
                                            }
                                        )
                                        Text(
                                            text = item.quantity.toString(),
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        SumButton(
                                            variant = SumButtonVariant.ADD,
                                            onClick = {
                                                onAction(BagAction.OnAddMoreItems(item.productSku))
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

            }
            SalePriceDescriptionBox(
                subtotal = state.subtotalAmountFormatted,
                total = state.totalAmountFormatted,
                tax = state.taxPercentage,
                totalTax = state.totalTaxAmountFormatted
            ) {
                PocPdvButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.items.isNotEmpty(),
                    text = stringResource(Res.string.charge_btn_with_price, state.totalAmountFormatted),
                    onClick = {
                        onAction(BagAction.OnChargeClick)
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
        BagScreen(
            state = BagState(),
            onAction = {}
        )
    }
}