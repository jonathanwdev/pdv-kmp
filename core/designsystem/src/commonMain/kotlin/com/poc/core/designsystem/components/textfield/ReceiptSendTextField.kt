package com.poc.core.designsystem.components.textfield

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.designsystem.theme.secondaryGreen
import designsystem.resources.Res
import designsystem.resources.send
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ReceiptSendTextField(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: String,
    onValueChange: (String) -> Unit,
    value: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        leadingIcon = leadingIcon,
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = Color(0xFFE5E7EB),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = MaterialTheme.colorScheme.tertiary
        ),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface),
        trailingIcon = {
            Button(
                onClick = {  },
                modifier = Modifier
                    .height(36.dp)
                    .padding(end = 6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = secondaryGreen,
                    contentColor = Color(0xFF065f46)
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
            ) {
                Text(
                    text = stringResource(Res.string.send),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    )
}

@Composable
@Preview
private fun ReceiptSendTextFieldPreview() {
    PocPdvTheme {
        ReceiptSendTextField(
            value = "",
            onValueChange = {},
            placeholder = "Enter email address",
        )
    }
}