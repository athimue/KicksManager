package com.athimue.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.domain.models.InventoryItem
import com.athimue.domain.models.Sneaker
import java.util.concurrent.ConcurrentLinkedDeque

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryComposable(
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
            ) {
                Icon(Icons.Rounded.Add, "Add item")
            }
        }
    ) {
        Column {
            Text(text = "INVENTORY")
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = {},
                active = true,
                onActiveChange = {}
            ) {}
            uiState.inventory?.let {
                LazyColumn {
                    items(it) { item ->
                        InventoryItemRow(item)
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