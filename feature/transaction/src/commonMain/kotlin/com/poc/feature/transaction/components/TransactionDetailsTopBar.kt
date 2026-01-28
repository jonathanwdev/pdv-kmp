package com.poc.feature.transaction.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.poc.feature.transaction.screens.transactionDetails.TransactionDetailsAction
import org.jetbrains.compose.resources.stringResource
import pocpdv.feature.transaction.generated.resources.Res
import pocpdv.feature.transaction.generated.resources.back
import pocpdv.feature.transaction.generated.resources.transaction_details

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailsTopBar(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit

) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Res.string.transaction_details),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigateBack()
            }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = stringResource(Res.string.back),
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}