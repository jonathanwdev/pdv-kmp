package com.poc.feature.exchange.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.poc.core.designsystem.components.avatars.ProductAvatar
import com.poc.core.designsystem.components.buttons.SumButton
import com.poc.core.designsystem.components.buttons.SumButtonVariant
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.exchange.models.ExchangeItemUI
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pocpdv.feature.exchange.generated.resources.Res
import pocpdv.feature.exchange.generated.resources.exchanged


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReturnItemCard(
    isSummary: Boolean = false,
    item: ExchangeItemUI,
    onAddItemClick: (Int) -> Unit,
    onRemoveItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val isDisabled = item.maxQuantity == 0

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .clip(RoundedCornerShape(12.dp))
            .graphicsLayer(alpha = if (isDisabled) 0.85f else 1f),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductAvatar(
                avatarUrl = item.imageUrl,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    ),
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if(!isSummary) {
                        if (!isDisabled) {
                            SumButton(
                                variant = SumButtonVariant.SUB,
                                onClick = {
                                    onRemoveItemClick(item.productSku)
                                },
                            )
                            Text(
                                text = item.quantitySelected.toString(),
                                fontWeight = FontWeight.SemiBold
                            )
                            SumButton(
                                variant = SumButtonVariant.ADD,
                                onClick = {
                                    onAddItemClick(item.productSku)
                                },
                            )
                        } else {
                            Text(
                                text = stringResource(Res.string.exchanged),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }else {
                        Row{

                            Text(
                                text = "Qty: x${item.quantitySelected}",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                }


            }
            Text(
                text = item.totalPriceFormatted,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Composable
@Preview
private fun ReturnItemCardPreview() {
    PocPdvTheme {
        ReturnItemCard(
            onAddItemClick = {},
            onRemoveItemClick = {},
            item = ExchangeItemUI(
                itemId = 1L,
                productSku = 123,
                name = "Product Name",
                maxQuantity = 5,
                quantitySelected = 1,
                imageUrl = "",
                totalPrice = 100.0,
                totalPriceFormatted = "R$ 100.00",
                unitPrice = 100.0,
                unitPriceFormatted = "R$ 100.00",
                tax = 0.0,
                taxFormatted = "R$ 0.00",
            )
        )

    }
}

@Composable
@Preview
private fun ReturnItemCardDisabledPreview() {
    PocPdvTheme {
        ReturnItemCard(
            onAddItemClick = {},
            onRemoveItemClick = {},
            item = ExchangeItemUI(
                itemId = 1L,
                productSku = 123,
                name = "Product Name",
                maxQuantity = 0,
                quantitySelected = 0,
                imageUrl = "",
                totalPrice = 100.0,
                totalPriceFormatted = "R$ 100.00",
                unitPrice = 100.0,
                unitPriceFormatted = "R$ 100.00",
                tax = 0.0,
                taxFormatted = "R$ 0.00",
            )
        )

    }
}

@Composable
@Preview
private fun ReturnItemCardSummaryPreview() {
    PocPdvTheme {
        ReturnItemCard(
            isSummary = true,
            onAddItemClick = {},
            onRemoveItemClick = {},
            item = ExchangeItemUI(
                itemId = 1L,
                productSku = 123,
                name = "Product Name",
                maxQuantity = 1,
                quantitySelected = 0,
                imageUrl = "",
                totalPrice = 100.0,
                totalPriceFormatted = "R$ 100.00",
                unitPrice = 100.0,
                unitPriceFormatted = "R$ 100.00",
                tax = 0.0,
                taxFormatted = "R$ 0.00",
            )
        )

    }
}