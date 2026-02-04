package com.poc.feature.transaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.core.designsystem.components.avatars.ProductAvatar
import com.poc.feature.transaction.models.TransactionItemUI
import com.poc.feature.transaction.models.TransactionProductItemUI

@Composable
fun TransactionItemHeader(
    modifier: Modifier = Modifier,
    formattedDate: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFE9EBF5))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = formattedDate.uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f)
        )
    }
}


@Composable
fun TransactionInfoItem(
    modifier: Modifier = Modifier,
    transaction: TransactionItemUI,
    onTransactionClick: (Long) -> Unit
) {
    when (transaction) {
        is TransactionItemUI.Sale -> {
            TransactionInfoItemSale(
                modifier = modifier,
                transaction = transaction,
                onTransactionClick = {
                    onTransactionClick(transaction.id)
                }
            )
        }
        is TransactionItemUI.Exchange -> {
            TransactionInfoItemRefund(
                modifier = modifier,
                transaction = transaction,
                onTransactionClick = {
                    onTransactionClick(transaction.id)
                }
            )
        }
    }

}

@Composable
private fun TransactionInfoItemRefund(
    modifier: Modifier = Modifier,
    transaction: TransactionItemUI.Exchange,
    onTransactionClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTransactionClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = .3f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ReceiptLong,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surface
            )

        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Return #${transaction.referenceId}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "-${transaction.transactionValueFormatted}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${transaction.transactionTimeFormatted} • ${transaction.transactionMethod.name}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                SaleStatusChip(
                    status = SaleStatusChipEnum.REFUNDED
                )
            }
        }
    }
}


@Composable
private fun TransactionInfoItemSale(
    modifier: Modifier = Modifier,
    transaction: TransactionItemUI.Sale,
    onTransactionClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTransactionClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ReceiptLong,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.surface
            )

        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Sale #${transaction.referenceId}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = transaction.transactionValueFormatted,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${transaction.transactionTimeFormatted} • ${transaction.transactionMethod.name}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                SaleStatusChip(
                    status = SaleStatusChipEnum.COMPLETED
                )
            }
        }
    }
}


@Composable
fun TransactionDetailListItem(
    item: TransactionProductItemUI
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProductAvatar(
            modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp)),
            avatarUrl = item.imageUrl,
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = "Qty: ${item.quantity} × ${item.priceFormatted}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
        Text(
            text = item.priceCalcFormatted,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

