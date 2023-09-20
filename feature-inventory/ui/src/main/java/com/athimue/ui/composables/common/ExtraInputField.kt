package com.athimue.ui.composables.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun <T> ExtraInputField(
    title: String,
    value: T,
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