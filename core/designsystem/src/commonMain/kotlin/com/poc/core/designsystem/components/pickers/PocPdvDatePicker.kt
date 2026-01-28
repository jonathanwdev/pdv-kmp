package com.poc.core.designsystem.components.pickers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.poc.core.designsystem.components.textfield.PocPdvTextField
import designsystem.resources.Res
import designsystem.resources.cancel
import designsystem.resources.ok
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocPdvDatePicker(
    modifier: Modifier = Modifier,
    label: String? = null,
    selectedDate: Long?,
    displayMode: DisplayMode = DisplayMode.Picker,
    onSelectedDateChange: (Long) -> Unit,
) {
    var showModal by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = displayMode
    )



    Box(
        modifier = Modifier.clickable() {
            showModal = true
        }
    ) {
        if (showModal) {
            DatePickerDialog(
                onDismissRequest = {
                    showModal = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onSelectedDateChange(it)
                        }
                        showModal = false
                    }) {
                        Text(text = stringResource(Res.string.ok))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showModal = false
                    }) {
                        Text(text = stringResource(Res.string.cancel))
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                )
            }
        }
        PocPdvTextField(
            label = label,
            value = selectedDate?.let { it.toString() } ?: "",
            onValueChange = { },
//        placeholder = stringResource(Res.string.receipt_id_placeholder),
            modifier = modifier,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun PocPdvDatePickerPreview() {    
    PocPdvDatePicker(
        selectedDate = null,
        onSelectedDateChange = {},
        modifier = Modifier
    )
}