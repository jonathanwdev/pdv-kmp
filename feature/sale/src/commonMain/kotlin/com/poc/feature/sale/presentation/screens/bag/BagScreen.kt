package com.poc.feature.sale.presentation.screens.bag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FindInPage
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SignalWifiStatusbarConnectedNoInternet4
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poc.core.designsystem.components.PocTextField
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BagRoot(
    viewModel: BagViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BagScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BagScreen(
    state: BagState,
    onAction: (BagAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                        Text(
                            text = "Venda #123456",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.SignalWifiStatusbarConnectedNoInternet4,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Spacer(Modifier.height(5.dp))
                PocTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    placeholder = "Write the product code",
                    onValueChange = {},
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.FindInPage,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                )
                LazyColumn {
                    items(10) { index ->
                        Row(
                            
                        ) {
                            Image(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier.size(50.dp).clip(RoundedCornerShape(12.dp))
                            )
                            Text(
                                text = "Item"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PocPdvTheme {
        BagScreen(
            state = BagState(),
            onAction = {}
        )
    }
}