package com.athimue.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.domain.models.InventoryItem
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryComposable(
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val dialogState = rememberModalBottomSheetState(showDialog)
    var query by remember { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var size by rememberSaveable { mutableStateOf("") }
    var buyPrice by rememberSaveable { mutableStateOf("") }
    var buyPlace by rememberSaveable { mutableStateOf("") }
    var buyDate by remember { mutableStateOf(Calendar.getInstance().timeInMillis) }
    var showDatePicker by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier, floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Column(Modifier.fillMaxSize()) {
            Text(
                text = "INVENTORY", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
            )
            uiState.inventory?.let {
                LazyColumn {
                    items(it) { item ->
                        InventoryItemRow(item)
                    }
                }
            } ?: Text(text = "No inventory")
            if (showDialog) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxSize(),
                    sheetState = dialogState,
                    onDismissRequest = { showDialog = false },
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = { showDialog = false }) {
                                Icon(imageVector = Icons.Rounded.Close, contentDescription = "")
                            }
                            Text(
                                "Add an item",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        InputField(
                            title = "Item name",
                            value = name,
                            onValueChange = { name = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )
                        InputField(
                            title = "Item size",
                            value = size,
                            onValueChange = { size = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                        InputField(
                            title = "Purchasing price",
                            value = buyPrice,
                            onValueChange = { buyPrice = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            )
                        )
                        DateInputField(
                            title = "Purchasing date",
                            value = buyDate,
                            onClick = { showDatePicker = true }
                        )
                        InputField(
                            title = "Purchasing place",
                            value = buyPlace,
                            onValueChange = { buyPlace = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = {
                                viewModel.addInventoryItem(
                                    name = "name",
                                    size = size,
                                    buyPrice = buyPrice.toDouble(),
                                    buyDate = buyDate.toString(),
                                    buyPlace = buyPlace
                                )
                            }) {
                            Text(text = "Add")
                        }
                        DatePicker(
                            isDialogDisplayed = showDatePicker,
                            closeDialog = { showDatePicker = it },
                            onDateSelected = { buyDate = it })
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnScope.InputField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            label = { Text(title) },
            keyboardOptions = keyboardOptions,
        )
    }
}

@Composable
fun InventoryItemRow(
    inventoryItem: InventoryItem
) {
    Row {
        Image(imageVector = Icons.Rounded.Share, contentDescription = "")
        Column {
            Text(text = inventoryItem.name)
            Row {
                Text(text = "Quantity")
                Text(text = "Size")
                Text(text = "Prix")
            }
        }
    }
}