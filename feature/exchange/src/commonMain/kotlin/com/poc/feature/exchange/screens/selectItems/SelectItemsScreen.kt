package com.poc.feature.exchange.screens.selectItems

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poc.core.designsystem.components.avatars.ProductAvatar
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.feature.exchange.components.ExchangeSteps
import com.poc.feature.exchange.components.ExchangeTopAppBar
import com.poc.feature.exchange.components.ReturnItemCard
import com.poc.feature.exchange.components.SelectItemsBottomBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import pocpdv.feature.exchange.generated.resources.Res
import pocpdv.feature.exchange.generated.resources.back_button_description
import pocpdv.feature.exchange.generated.resources.original_transaction_items
import pocpdv.feature.exchange.generated.resources.return_items_step_description
import pocpdv.feature.exchange.generated.resources.return_window_note
import pocpdv.feature.exchange.generated.resources.select_replacement_items
import pocpdv.feature.exchange.generated.resources.select_returns_title
import pocpdv.feature.exchange.generated.resources.total_returned_items
import pocpdv.feature.exchange.generated.resources.transaction_id_label
import pocpdv.feature.exchange.generated.resources.value_of_returns

@Composable
fun SelectItemsRoot(
    viewModel: SelectItemsViewModel = koinViewModel<SelectItemsViewModel>(),
    onNavigateBack: () -> Unit,
    onNavigateToSummary: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SelectItemsScreen(
        state = state,
        onAction = { action ->
            when(action) {
                SelectItemsAction.NavigateBack -> onNavigateBack()
                SelectItemsAction.SelectReplacementItems -> onNavigateToSummary()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SelectItemsScreen(
    state: SelectItemsState,
    onAction: (SelectItemsAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            ExchangeTopAppBar(
                text = stringResource(Res.string.select_returns_title),
                onBackClick = { onAction(SelectItemsAction.NavigateBack) }
            )
        },
        bottomBar = {
            SelectItemsBottomBar(
                selectedItems = state.selectedItems,
                onConfirmClick = {
                    onAction(SelectItemsAction.SelectReplacementItems)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 0.dp)
                .padding(top = 16.dp),
        ) {
            ExchangeSteps(currentStep = state.currentStep)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp)
                            .padding(bottom = 16.dp)
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.original_transaction_items),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f),
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "${stringResource(Res.string.transaction_id_label)}: ${state.transactionId}",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .6f),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
                items(state.returnItems) { item ->
                    ReturnItemCard(
                        item = item,
                        isChecked = true,
                        onToggleSelection = {  }
                    )
                    Spacer(Modifier.height(12.dp))
                }

            }
            Text(
                text = stringResource(Res.string.return_window_note),
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Spacer(Modifier.height(40.dp))
        }

    }
}



@Preview
@Composable
private fun SelectItemsScreenPreview() {
    PocPdvTheme {
        SelectItemsScreen(
            state = SelectItemsState(currentStep = 2),
            onAction = {}
        )
    }
}
