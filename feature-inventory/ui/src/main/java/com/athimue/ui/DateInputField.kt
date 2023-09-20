package com.athimue.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DateInputField(
    title: String,
    value: Long,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Box {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = value.toString(),
                onValueChange = {},
                singleLine = true,
                label = { Text(title) },
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = onClick),
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    isDialogDisplayed: Boolean,
    closeDialog: (Boolean) -> Unit,
    onDateSelected: (Long) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    if (isDialogDisplayed) {
        DatePickerDialog(
            onDismissRequest = {
                closeDialog(false)
            },
            confirmButton = {
                TextButton(onClick = {
                    closeDialog(false)
                    onDateSelected(datePickerState.selectedDateMillis!!)
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    closeDialog(false)
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
