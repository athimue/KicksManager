package com.athimue.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.domain.models.InventoryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryComposable(
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val dialogState = rememberModalBottomSheetState(showDialog)
    var query by remember { mutableStateOf("") }
    var text by rememberSaveable { mutableStateOf("") }

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
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Add an item")
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            singleLine = true,
                            label = { Text("Name of the item") },
                            placeholder = { Text("Yeezy...") },
                        )
                        Button(
                            onClick = { showDialog = false },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Close Dialog")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InventoryItemRow(
    inventoryItem: InventoryItem
) {
    Row() {
        Image(imageVector = Icons.Rounded.Share, contentDescription = "")
        Column() {
            Text(text = inventoryItem.name)
            Row() {
                Text(text = "Quantity")
                Text(text = "Size")
                Text(text = "Prix")
            }
        }
    }
}