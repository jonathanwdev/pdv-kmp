package com.poc.core.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CardButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    icon: @Composable (() -> Unit)? = null,
    label: String,
) {
    Button(
        onClick =  onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.size(110.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            icon?.let {
                icon()
            }
            Text(label)
        }
    }
}

@Composable
@Preview
private fun CardButtonPreview() {
    PocPdvTheme {
        CardButton(
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
