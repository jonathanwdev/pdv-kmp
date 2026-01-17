package com.poc.feature.home.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.core.designsystem.components.buttons.CardButton
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.designsystem.utils.DesktopTabletPreview
import com.poc.core.presentation.utils.currentDeviceConfiguration
import com.poc.feature.home.components.HomeBottomBar
import com.poc.feature.home.components.HomeTopBar
import com.poc.feature.home.models.MenuDestination
import com.poc.feature.home.models.menuItems
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun HomeRoot(
    onNavigateToSale: () -> Unit,
    onNavigateToExchange: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onNavigateToTransactions: () -> Unit,
) {
    HomeScreen(
        onNavigateToSale = onNavigateToSale,
        onNavigateToExchange = onNavigateToExchange,
        onNavigateToCatalog = onNavigateToCatalog,
        onNavigateToTransactions = onNavigateToTransactions
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSale: () -> Unit,
    onNavigateToExchange: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onNavigateToTransactions: () -> Unit,
) {
    val isWideScreen = currentDeviceConfiguration().isWideScreen
    Scaffold(
        topBar = {
            HomeTopBar(
                modifier = Modifier.padding(horizontal = 14.dp)
            )
        },
        bottomBar = {
            HomeBottomBar()
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Adaptive( if(isWideScreen) 400.dp else 160.dp),
            contentPadding = PaddingValues(14.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            items(items = menuItems) {
                CardButton(
                    modifier = Modifier
                        .heightIn(min = if(isWideScreen) 300.dp else 160.dp)
                        .widthIn(min = if(isWideScreen) 350.dp else 160.dp),
                    onClick = {
                        when (it.destination) {
                            MenuDestination.SALE -> onNavigateToSale()
                            MenuDestination.CATALOG -> onNavigateToCatalog()
                            MenuDestination.EXCHANGE -> onNavigateToExchange()
                            MenuDestination.TRANSACTIONS -> onNavigateToTransactions()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.surface
                        )
                    },
                    description = it.description?.let { desc -> stringResource(desc) },
                    label = stringResource(it.label)
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
            onNavigateToSale = {},
            onNavigateToExchange = {},
            onNavigateToCatalog = {},
            onNavigateToTransactions = {}
        )
    }
}


@Composable
@DesktopTabletPreview
private fun HomeTabletDesktopScreenPreview() {
    PocPdvTheme {
        HomeScreen(
            onNavigateToSale = {},
            onNavigateToExchange = {},
            onNavigateToCatalog = {},
            onNavigateToTransactions = {}
        )
    }
}