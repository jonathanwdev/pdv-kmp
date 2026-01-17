package com.poc.core.designsystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun     CardButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    description: String? = null,
    isSelected: Boolean = false,
    label: String,
) {
    OutlinedButton(
        onClick =  onClick,
        enabled = enabled,
        border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if(isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary,
            containerColor = if(isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            icon?.let {
                icon()
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = label,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                description?.let {
                    Text(
                        text = description,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Normal,
                            lineHeight = 16.sp
                        )
                    )
                }
            }

        }
    }
}

@Composable
@Preview
private fun CardButtonPreview() {
    PocPdvTheme {
        CardButton(
            modifier = Modifier.size(120.dp),
            onClick = {},
            label = "Sale"
        )
    }
}

@Composable
@Preview
private fun CardButtonWithIconPreview() {
    PocPdvTheme {
        CardButton(
            modifier = Modifier.size(120.dp),
            onClick = {},
            label = "Sale",
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
@Preview
private fun CardButtonDescriptionPreview() {
    PocPdvTheme {
        CardButton(
            modifier = Modifier.size(160.dp),
            onClick = {},
            label = "Transaction Hitory",
            description = "Start a new sale",
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
@Preview
private fun CardButtonSelectedPreview() {
    PocPdvTheme {
        CardButton(
            modifier = Modifier.size(160.dp),
            onClick = {},
            isSelected = true,
            label = "Transaction Hitory",
            description = "Start a new sale",
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null
                )
            }
        )
    }
}
