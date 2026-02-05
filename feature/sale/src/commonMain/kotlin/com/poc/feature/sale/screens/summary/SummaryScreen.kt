package com.poc.feature.sale.screens.summary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.components.buttons.PrinterButton
import com.poc.core.designsystem.components.common.Separator
import com.poc.core.designsystem.components.textfield.ReceiptSendTextField
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.presentation.utils.ObserveAsEvent
import com.poc.core.presentation.utils.currentDeviceConfiguration
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope
import pocpdv.feature.sale.generated.resources.Res
import pocpdv.feature.sale.generated.resources.date_n_time
import pocpdv.feature.sale.generated.resources.enter_email_address
import pocpdv.feature.sale.generated.resources.payment_successful
import pocpdv.feature.sale.generated.resources.receipt_options
import pocpdv.feature.sale.generated.resources.return_to_home
import pocpdv.feature.sale.generated.resources.thank_you_for_your_purchase
import pocpdv.feature.sale.generated.resources.total_amount_paid
import pocpdv.feature.sale.generated.resources.transaction_id


@Composable
fun SummaryRoot(
    koinScope: Scope,
    onNavigateBackToHome: () -> Unit,
    viewModel: SummaryViewModel = koinViewModel<SummaryViewModel>(scope = koinScope)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvent(viewModel.event) { event ->
        when (event) {
            SummaryEvent.OnFinish -> {
                onNavigateBackToHome()
            }
        }
    }

    SummaryScreen(
        state = state,
        onAction = viewModel::onAction,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    state: SummaryState,
    onAction: (SummaryAction) -> Unit,
) {
    val currentDeviceConfiguration = currentDeviceConfiguration()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(if (currentDeviceConfiguration.isMobile) 1f else 0.6f)
                    .weight(1f)
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .shadow(2.dp, clip = true, shape = CircleShape)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.payment_successful),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(Res.string.thank_you_for_your_purchase),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = .1f))
                ) {
                    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.total_amount_paid),
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = state.totalValueFormatted,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Separator()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.date_n_time),
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = state.dateTimeFormatted,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                        Separator()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.transaction_id),
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = state.transactionId,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.receipt_options),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(16.dp))
                    ReceiptSendTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.userEmail,
                        onValueChange = {
                            onAction(SummaryAction.OnEmailChange(it))
                        },
                        onSendClick = {
                            onAction(SummaryAction.OnSendClick)
                        },
                        enabled = !state.isSent,
                        placeholder = stringResource(Res.string.enter_email_address)
                    )
                    Spacer(Modifier.height(16.dp))
                    PrinterButton {}
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(if (currentDeviceConfiguration.isMobile) 1f else 0.6f)
                    .padding(bottom = 8.dp)
            ) {
                PocPdvButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.return_to_home),
                    onClick = {
                        onAction(SummaryAction.OnReturnToHomeClick)
                    }
                )

            }
            Spacer(Modifier.height(20.dp))

        }
    }
}


@Preview
@Composable
private fun SummaryScreenPreview() {
    PocPdvTheme {
        SummaryScreen(
            state = SummaryState(),
            onAction = {},
        )
    }
}
