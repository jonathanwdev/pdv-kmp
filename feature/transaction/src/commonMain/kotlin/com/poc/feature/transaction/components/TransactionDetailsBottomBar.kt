package com.poc.feature.transaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.SettingsBackupRestore
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poc.core.designsystem.components.buttons.PocPdvButton
import com.poc.core.designsystem.components.buttons.PocPdvOutlineButton
import com.poc.core.designsystem.theme.PocPdvTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pocpdv.feature.transaction.generated.resources.Res
import pocpdv.feature.transaction.generated.resources.issue_refund

@Composable
fun TransactionDetailsBottomBar(
    modifier: Modifier = Modifier,
    onReprintClick: () -> Unit,
    onIssueRefoundClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PocPdvButton(
            onClick = { onReprintClick() },
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.AutoMirrored.Default.ReceiptLong,
            text = stringResource(Res.string.issue_refund)
        )
        PocPdvOutlineButton(
            onClick = { onIssueRefoundClick() },
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.issue_refund),
            icon = Icons.Default.SettingsBackupRestore
        )
    }
}

@Composable
@Preview
private fun TransactionDetailsBottomBarPreview() {
    PocPdvTheme {
        TransactionDetailsBottomBar(
            onReprintClick = {},
            onIssueRefoundClick = {}
        )
    }
}

