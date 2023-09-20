package com.athimue.ui.composables.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    isDialogDisplayed: Boolean,
    closeDialog: () -> Unit,
    onDateSelected: (Long) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    if (isDialogDisplayed) {
        DatePickerDialog(
            onDismissRequest = closeDialog,
            confirmButton = {
                TextButton(onClick = {
                    closeDialog()
                    onDateSelected(datePickerState.selectedDateMillis!!)
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    closeDialog()
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}
