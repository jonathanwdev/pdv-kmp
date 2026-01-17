package com.poc.feature.sync.screens.sync

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poc.core.designsystem.theme.PocPdvTheme
import com.poc.core.presentation.utils.ObserveAsEvent
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SyncRoot(
    syncViewModel: SyncViewModel = koinViewModel<SyncViewModel>(),
    onSyncDone: () -> Unit
) {
    val uiState by syncViewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvent(syncViewModel.action) { action ->
        when(action) {
            SyncScreenAction.OnSyncDone -> {
                onSyncDone()
            }
        }
    }

    SyncScreen(
        uiState = uiState,
    )
}



@Composable
fun SyncScreen(
    uiState: SyncScreenState,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = uiState.progress,
        animationSpec = tween(durationMillis = 700),
        label = "progressAnimation"
    )

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        AnimatedContent(
            targetState = uiState.isDone,
            label = "messageAnimation"
        ) { progressFinished ->
            if(!progressFinished) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        progress = animatedProgress,
                        color = MaterialTheme.colorScheme.tertiary,
                        strokeWidth = 10.dp,
                        modifier = Modifier.size(130.dp).animateContentSize()
                    )
                    Text(
                        text = "${(animatedProgress * 100).toInt()}%",
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircleOutline,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }


        }
        AnimatedContent(
            targetState = uiState.message,
            label = "messageAnimation"
        ) { message ->
            Text(
                text = stringResource(message),
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }

    }
}


@Composable
@Preview
private fun SynchronizingScreenPreview() {
    PocPdvTheme {
        SyncScreen(
            uiState = SyncScreenState()
        )
    }
}



@Composable
@Preview
private fun SyncDoneScreenPreview() {
    PocPdvTheme {
        PocPdvTheme {
            SyncScreen(
                uiState = SyncScreenState(
                    progress = 1f,
                    isDone = true
                )
            )
        }
    }
}



