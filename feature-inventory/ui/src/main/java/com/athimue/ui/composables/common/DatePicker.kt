package com.athimue.ui.composables.common

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.util.Locale

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
            },
        ) {
            DatePicker(
                state = datePickerState,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerPreview() {
    DatePicker(
        isDialogDisplayed = true,
        closeDialog = {},
        onDateSelected = {}
    )
}
