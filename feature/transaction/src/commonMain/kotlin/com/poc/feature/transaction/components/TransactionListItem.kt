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
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.core.designsystem.components.avatars.ProductAvatar
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.transaction.screens.transactionDetails.TransactionDetailItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionItemHeader(
    modifier: Modifier = Modifier,
    formattedDate: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = .1f))
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


data class TransactionItem(
    val id: String,
    val title: String,
    val amount: String,
    val time: String,
    val method: String,
    val status: String,
)

data class TransactionSection(val date: String, val transactions: List<TransactionItem>)


val sampleTransactions = listOf(
    TransactionSection(
        date = "Tuesday, Oct 24, 2023",
        transactions = listOf(
            TransactionItem(
                id = "8842",
                title = "Sale #8842",
                amount = "$124.50",
                time = "14:30",
                method = "Credit Card",
                status = "Completed",

                ),
            TransactionItem(
                id = "8839",
                title = "Return #8839",
                amount = "-$45.00",
                time = "09:45",
                method = "Debit Card",
                status = "Refunded",
            ),
            TransactionItem(
                id = "8841",
                title = "Sale #8841",
                amount = "$12.00",
                time = "12:15",
                method = "Apple Pay",
                status = "Completed",

                ),
        )
    ),
    TransactionSection(
        date = "Monday, Oct 23, 2023",
        transactions = listOf(
            TransactionItem(
                id = "8835",
                title = "Retail Sale #8835",
                amount = "$235.99",
                time = "18:20",
                method = "Visa **** 1234",
                status = "Completed",
            ),
            TransactionItem(
                id = "8832",
                title = "Dining #8832",
                amount = "$67.20",
                time = "13:05",
                method = "Cash",
                status = "Completed"
            )
        )
    )
)

@Composable
fun TransactionInfoItem(
    modifier: Modifier = Modifier,
    transaction: TransactionItem,
    onTransactionClick: () -> Unit
) {
    val (iconBoxColor, transactionAmountColor) = when (transaction.status) {
        "Refunded" -> Pair(
            MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = .3f),
            MaterialTheme.colorScheme.error,
        )

        else -> Pair(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSurface,
        )
    }

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
                .background(iconBoxColor),
            contentAlignment = Alignment.Center
        ) {
            if (transaction.status != "Refunded") {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ReceiptLong,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface
                )
            } else {
                Icon(
                    Icons.AutoMirrored.Filled.Reply,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.inverseOnSurface
                )
            }

        }

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = transaction.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = transaction.amount,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = transactionAmountColor
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${transaction.time} • ${transaction.method}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                SaleStatusChip(
                    status = if (transaction.status == "Refunded") SaleStatusChipEnum.REFUNDED else SaleStatusChipEnum.COMPLETED
                )
            }
        }
    }
}

val sampleDetailItemMock = listOf(
    TransactionDetailItem(
        name = "Wireless Mouse",
        quantity = 1,
        price = 25.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDl1NpI9dZtbAKaKdjh1653AsLjy0VcQR2rZLU3AdKr44SEf54DGzEsoKyygZ66CjT_mfR2MmBYcF5Six_Qw7DfzYWvsOwUzJk5q7rWNCzVJSa3cjgNpYHF3U581rYEw5Qkmy_gkYpibl7rk7rV94dUMh2n9wn5radc0igqYbHHmnQ0m1qwm447eJL-3aqCsmxsb9vC2NGvdjLRsqzfhFvJrmDXQitcypu7TFKLIWuLn3iGp70J2APgn8K2U3M1LkSlmqZ7pnvMC4w"
    ),
    TransactionDetailItem(
        name = "USB-C Cable",
        quantity = 2,
        price = 15.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBxNPLlokbtormx5793UpazxqtN7_8kT3WfnCQmwXm6DheQ6zY6Al24dIXbtH6Q-Peu7NiRNqZ9-FS1s8so5MOOVSNzusSiWsnccxRQJSmjdN7vfThIganEJLKDjW6w8fM2bLfGEzKvaBOcT-T5Ndaxv68oFHyqSMqVXseJtV9cygU2gZHBOMTjl6p6GZzcSdxBAUjTpnr6OxmMWrOOC1aljuMi8PHEVwHpvtOoGA9Typ_hV5REAXVsu1KD8bVeUChxtzsO4gVN9GQ"
    ),
    TransactionDetailItem(
        name = "Wireless Mouse",
        quantity = 1,
        price = 25.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDl1NpI9dZtbAKaKdjh1653AsLjy0VcQR2rZLU3AdKr44SEf54DGzEsoKyygZ66CjT_mfR2MmBYcF5Six_Qw7DfzYWvsOwUzJk5q7rWNCzVJSa3cjgNpYHF3U581rYEw5Qkmy_gkYpibl7rk7rV94dUMh2n9wn5radc0igqYbHHmnQ0m1qwm447eJL-3aqCsmxsb9vC2NGvdjLRsqzfhFvJrmDXQitcypu7TFKLIWuLn3iGp70J2APgn8K2U3M1LkSlmqZ7pnvMC4w"
    ),
    TransactionDetailItem(
        name = "USB-C Cable",
        quantity = 2,
        price = 15.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBxNPLlokbtormx5793UpazxqtN7_8kT3WfnCQmwXm6DheQ6zY6Al24dIXbtH6Q-Peu7NiRNqZ9-FS1s8so5MOOVSNzusSiWsnccxRQJSmjdN7vfThIganEJLKDjW6w8fM2bLfGEzKvaBOcT-T5Ndaxv68oFHyqSMqVXseJtV9cygU2gZHBOMTjl6p6GZzcSdxBAUjTpnr6OxmMWrOOC1aljuMi8PHEVwHpvtOoGA9Typ_hV5REAXVsu1KD8bVeUChxtzsO4gVN9GQ"
    ),
    TransactionDetailItem(
        name = "Wireless Mouse",
        quantity = 1,
        price = 25.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDl1NpI9dZtbAKaKdjh1653AsLjy0VcQR2rZLU3AdKr44SEf54DGzEsoKyygZ66CjT_mfR2MmBYcF5Six_Qw7DfzYWvsOwUzJk5q7rWNCzVJSa3cjgNpYHF3U581rYEw5Qkmy_gkYpibl7rk7rV94dUMh2n9wn5radc0igqYbHHmnQ0m1qwm447eJL-3aqCsmxsb9vC2NGvdjLRsqzfhFvJrmDXQitcypu7TFKLIWuLn3iGp70J2APgn8K2U3M1LkSlmqZ7pnvMC4w"
    ),
    TransactionDetailItem(
        name = "USB-C Cable",
        quantity = 2,
        price = 15.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBxNPLlokbtormx5793UpazxqtN7_8kT3WfnCQmwXm6DheQ6zY6Al24dIXbtH6Q-Peu7NiRNqZ9-FS1s8so5MOOVSNzusSiWsnccxRQJSmjdN7vfThIganEJLKDjW6w8fM2bLfGEzKvaBOcT-T5Ndaxv68oFHyqSMqVXseJtV9cygU2gZHBOMTjl6p6GZzcSdxBAUjTpnr6OxmMWrOOC1aljuMi8PHEVwHpvtOoGA9Typ_hV5REAXVsu1KD8bVeUChxtzsO4gVN9GQ"
    ),
    TransactionDetailItem(
        name = "Wireless Mouse",
        quantity = 1,
        price = 25.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDl1NpI9dZtbAKaKdjh1653AsLjy0VcQR2rZLU3AdKr44SEf54DGzEsoKyygZ66CjT_mfR2MmBYcF5Six_Qw7DfzYWvsOwUzJk5q7rWNCzVJSa3cjgNpYHF3U581rYEw5Qkmy_gkYpibl7rk7rV94dUMh2n9wn5radc0igqYbHHmnQ0m1qwm447eJL-3aqCsmxsb9vC2NGvdjLRsqzfhFvJrmDXQitcypu7TFKLIWuLn3iGp70J2APgn8K2U3M1LkSlmqZ7pnvMC4w"
    ),
    TransactionDetailItem(
        name = "USB-C Cable",
        quantity = 2,
        price = 15.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBxNPLlokbtormx5793UpazxqtN7_8kT3WfnCQmwXm6DheQ6zY6Al24dIXbtH6Q-Peu7NiRNqZ9-FS1s8so5MOOVSNzusSiWsnccxRQJSmjdN7vfThIganEJLKDjW6w8fM2bLfGEzKvaBOcT-T5Ndaxv68oFHyqSMqVXseJtV9cygU2gZHBOMTjl6p6GZzcSdxBAUjTpnr6OxmMWrOOC1aljuMi8PHEVwHpvtOoGA9Typ_hV5REAXVsu1KD8bVeUChxtzsO4gVN9GQ"
    ),
    TransactionDetailItem(
        name = "Wireless Mouse",
        quantity = 1,
        price = 25.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDl1NpI9dZtbAKaKdjh1653AsLjy0VcQR2rZLU3AdKr44SEf54DGzEsoKyygZ66CjT_mfR2MmBYcF5Six_Qw7DfzYWvsOwUzJk5q7rWNCzVJSa3cjgNpYHF3U581rYEw5Qkmy_gkYpibl7rk7rV94dUMh2n9wn5radc0igqYbHHmnQ0m1qwm447eJL-3aqCsmxsb9vC2NGvdjLRsqzfhFvJrmDXQitcypu7TFKLIWuLn3iGp70J2APgn8K2U3M1LkSlmqZ7pnvMC4w"
    ),
    TransactionDetailItem(
        name = "USB-C Cable",
        quantity = 2,
        price = 15.0,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBxNPLlokbtormx5793UpazxqtN7_8kT3WfnCQmwXm6DheQ6zY6Al24dIXbtH6Q-Peu7NiRNqZ9-FS1s8so5MOOVSNzusSiWsnccxRQJSmjdN7vfThIganEJLKDjW6w8fM2bLfGEzKvaBOcT-T5Ndaxv68oFHyqSMqVXseJtV9cygU2gZHBOMTjl6p6GZzcSdxBAUjTpnr6OxmMWrOOC1aljuMi8PHEVwHpvtOoGA9Typ_hV5REAXVsu1KD8bVeUChxtzsO4gVN9GQ"
    ),
)

@Composable
fun TransactionDetailListItem(item: TransactionDetailItem) {
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
                text = "Qty: ${item.quantity} × $${item.price}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
        Text(
            text = "$${item.quantity * item.price}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun TransactionInfoItemPreview() {
    PocPdvTheme {
        TransactionInfoItem(
            transaction = sampleTransactions[0].transactions[0],
            onTransactionClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun TransactionInfoItemRefoundPreview() {
    PocPdvTheme {
        TransactionInfoItem(
            transaction = sampleTransactions[0].transactions[1],
            onTransactionClick = {}
        )
    }
}



@Composable
@Preview(showBackground = true)
private fun TransactionDetailItemPreview() {
    PocPdvTheme {
        TransactionDetailListItem(
            item = sampleDetailItemMock[0]
        )
    }
}
