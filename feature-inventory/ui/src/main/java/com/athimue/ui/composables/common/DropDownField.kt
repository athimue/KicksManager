package com.athimue.ui.composables.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownField(
    title: String, choices: List<String>, itemSelected: String, onItemSelected: (String) -> Unit
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var dropDownTextField by remember { mutableStateOf(Size.Zero) }
    val icon =
        if (isDropDownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = title, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace
        )
        Box {
            OutlinedTextField(value = itemSelected,
                onValueChange = onItemSelected,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        dropDownTextField = coordinates.size.toSize()
                    },
                label = { Text(title) },
                trailingIcon = {
                    Icon(
                        imageVector = icon, contentDescription = ""
                    )
                })
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = { isDropDownExpanded = !isDropDownExpanded }),
            )
        }
        DropdownMenu(
            expanded = isDropDownExpanded,
            onDismissRequest = { isDropDownExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { dropDownTextField.width.toDp() })
        ) {
            choices.forEach { label ->
                DropdownMenuItem(onClick = {
                    onItemSelected(label)
                    isDropDownExpanded = false
                }, text = { Text(text = label) })
            }
        }
    }
}