package com.poc.feature.sale.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.cancel_sale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaleTopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon:  @Composable (() -> Unit) = {},
    onCancelMenuClick: () -> Unit,
) {
    var isMenuVisible by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = navigationIcon,
        actions = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.clickable {
                    isMenuVisible = !isMenuVisible
                }
            )
            DropdownMenu(
                expanded = isMenuVisible,
                onDismissRequest = {
                    isMenuVisible = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(Res.string.cancel_sale))
                    },
                    onClick = {
                        isMenuVisible = false
                        onCancelMenuClick()
                    }
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SaleTopBarPreview() {
    PocPdvTheme() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SaleTopBar(
                title = "New Sale",
                onCancelMenuClick = {},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = null
                    )
                }
            )
        }
    }
}
