package com.athimue.ui.composables.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    isDialogDisplayed: Boolean,
    closeDialog: () -> Unit,
    onDateSelected: (String) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    if (isDialogDisplayed) {
        DatePickerDialog(
            onDismissRequest = closeDialog,
            confirmButton = {
                TextButton(onClick = {
                    closeDialog()
                    datePickerState.selectedDateMillis?.let {
                        val simpleDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val currentDate = simpleDate.format(it)
                        onDateSelected(currentDate)
                    }
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
