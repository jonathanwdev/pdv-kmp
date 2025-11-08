package com.poc.feature.auth.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.poc.core.designsystem.components.buttons.CardButton
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.presentation.utils.currentDeviceConfiguration
import com.poc.feature.auth.presentation.components.HomeTopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pocpdv.feature.auth.generated.resources.Res
import pocpdv.feature.auth.generated.resources.history
import pocpdv.feature.auth.generated.resources.products
import pocpdv.feature.auth.generated.resources.sale
import pocpdv.feature.auth.generated.resources.welcome_description
import pocpdv.feature.auth.generated.resources.welcome_title


@Composable
fun HomeRoot(
    onNavigateToLastSales: () -> Unit,
    onNavigateToProducts: () -> Unit,
    onNavigateToSale: () -> Unit,
) {
    HomeScreen(
        onNavigateToLastSales = onNavigateToLastSales,
        onNavigateToProducts = onNavigateToProducts,
        onNavigateToSale = onNavigateToSale,
    )
}


@Composable
fun HomeScreen(
    onNavigateToLastSales: () -> Unit,
    onNavigateToProducts: () -> Unit,
    onNavigateToSale: () -> Unit,
) {
    val isDesktop = !currentDeviceConfiguration().isMobile


    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            HomeTopBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 12.dp),
        ) {
            Column(
                horizontalAlignment = if(isDesktop) Alignment.CenterHorizontally else Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(Res.string.welcome_title),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = stringResource(Res.string.welcome_description),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                horizontalArrangement = if(isDesktop) Arrangement.SpaceEvenly else Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                CardButton(
                    onClick = onNavigateToSale,
                    label = stringResource(Res.string.sale),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = stringResource(Res.string.sale)
                        )
                    }
                )
                CardButton(
                    onClick = onNavigateToProducts,
                    label = stringResource(Res.string.products),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ProductionQuantityLimits,
                            contentDescription = stringResource(Res.string.products)
                        )
                    }
                )
                CardButton(
                    onClick = onNavigateToLastSales,
                    label = stringResource(Res.string.history),
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.PointOfSale,
                            contentDescription = stringResource(Res.string.history)
                        )
                    }
                )

            }

        }
    }
}

@Composable
@Preview
private fun HomeScreenPreview() {
    PocPdvTheme {
        HomeScreen(
            onNavigateToLastSales = {},
            onNavigateToProducts = {},
            onNavigateToSale = {},
        )
    }
}


@Composable
@Preview(
    widthDp = 600,
    heightDp = 400
)
private fun HomeScreenDesktopPreview() {
    PocPdvTheme {
        HomeScreen(
            onNavigateToLastSales = {},
            onNavigateToProducts = {},
            onNavigateToSale = {},
        )
    }
}