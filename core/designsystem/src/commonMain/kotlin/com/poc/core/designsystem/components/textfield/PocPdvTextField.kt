package com.poc.core.designsystem.components.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PocPdvTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String? = null,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    label: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column {
        label?.let {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }

        TextField(
            readOnly = readOnly,
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = .4f),
                    shape = RoundedCornerShape(12.dp)
                ),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                placeholder?.let {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }

            },

            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            trailingIcon = trailingIcon,
            colors = colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                errorContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PocTextFieldPreview() {
    PocPdvTheme {
        PocPdvTextField(
            value = "",
            placeholder = "Hello world",
            onValueChange = {},
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }
        )
    }
}


@Composable
@Preview(
    showBackground = true
)
private fun PocTexWithTextFieldPreview() {
    PocPdvTheme {
        PocPdvTextField(
            value = "Hello world",
            onValueChange = {},
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }
        )
    }
}