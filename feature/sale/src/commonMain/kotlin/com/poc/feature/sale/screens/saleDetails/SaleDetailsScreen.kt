package com.poc.feature.sale.screens.saleDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.components.avatars.ProductAvatar
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.components.buttons.PocPdvOutlineButton
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.presentation.utils.currentDeviceConfiguration
import com.poc.feature.sale.components.SalePriceDescriptionBox
import com.poc.feature.sale.components.SaleTopBar
import com.poc.feature.sale.components.TotalBox
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.edit_sale
import pocpdv.feature.sale.generated.resources.proceed_payment
import pocpdv.feature.sale.generated.resources.sale_details

@Composable
fun SaleDetailsRoot(
    koinScope: Scope,
    onNavigateBack: () -> Unit,
    onNavigateBackToHome: () -> Unit,
    onNavigateToPayment: () -> Unit,
    viewModel: SaleDetailsViewModel = koinViewModel<SaleDetailsViewModel>(scope = koinScope),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SaleDetailsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SaleDetailsAction.OnCancelClick -> onNavigateBackToHome()
                SaleDetailsAction.OnEditClick -> onNavigateBack()
                SaleDetailsAction.OnGoBackClick -> onNavigateBack()
                SaleDetailsAction.OnProceedClick -> onNavigateToPayment()
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
fun SaleDetailsScreen(
    state: SaleDetailsState,
    onAction: (SaleDetailsAction) -> Unit,
) {
    val deviceConfiguration = currentDeviceConfiguration()
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            SaleTopBar(
                onCancelMenuClick = {
                    onAction(SaleDetailsAction.OnCancelClick)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(SaleDetailsAction.OnGoBackClick)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChevronLeft,
                            contentDescription = null
                        )
                    }

                },
                title = stringResource(Res.string.sale_details)
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 15.dp)
                .fillMaxWidth()
        ) {
            when{
                deviceConfiguration.isMobile -> {
                    MobileLayout(
                        state = state,
                        onAction = onAction
                    )
                }
                else -> {
                    DesktopLayout(
                        state = state,
                        onAction = onAction
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun Preview() {
    PocPdvTheme {
        SaleDetailsScreen(
            state = SaleDetailsState(),
            onAction = {}
        )
    }
}