package com.poc.core.designsystem.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FilterChip(
    modifier: Modifier = Modifier,
    onSelect: () -> Unit,
    isSelected: Boolean,
    text: String,
) {
    Button(
        onClick = onSelect,
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isSelected) MaterialTheme.colorScheme.tertiary else Color(0xFFF3F4F6),
            contentColor = if(isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
        modifier = modifier.height(36.dp)
    ) {
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            tint = if(isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
@Preview
private fun FilterChipSelectedPreview() {
    PocPdvTheme {
        FilterChip(
            onSelect = {},
            isSelected = true,
            text = "Today"
        )
    }
}

@Composable
@Preview
private fun FilterChipUnselectedPreview() {
    PocPdvTheme {
        FilterChip(
            onSelect = {},
            isSelected = false,
            text = "Today"
        )
    }
}