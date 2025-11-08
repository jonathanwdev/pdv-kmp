package com.poc.core.designsystem.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PocTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String? = null,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            placeholder?.let{
                Text(
                    text = placeholder
                )
            }

        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun PocTextFieldPreview() {
    PocPdvTheme {
        PocTextField(
            value = "",
            placeholder = "Hello world",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
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
        PocTextField(
            value = "Hello world",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        )
    }
}