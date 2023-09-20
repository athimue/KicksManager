package com.athimue.ui.composables.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.domain.models.InventoryItem
import com.athimue.ui.composables.inventoryform.InventoryFormModal
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryComposable(
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showFormModal by remember { mutableStateOf(false) }
    val formModalState = rememberModalBottomSheetState(showFormModal)

    Scaffold(
        modifier = Modifier, floatingActionButton = {
            FloatingActionButton(onClick = { showFormModal = true }) {
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
            InventoryFormModal(formModalState = formModalState,
                showFormModal = showFormModal,
                closeModal = { showFormModal = false },
                addInventory = { name, picture, size, price, date, place ->
                    viewModel.addInventoryItem(
                        name, picture, size, price, date, place
                    )
                })
        }
    }
}

@Composable
fun InventoryItemRow(
    inventoryItem: InventoryItem
) {
    Row(
        modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Rounded.Share,
            contentDescription = "",
            modifier = Modifier.padding(8.dp)
        )
        Column {
            Text(text = inventoryItem.name)
            Row {
                Text(text = inventoryItem.quantity.toString())
                Text(text = inventoryItem.size)
                Text(text = inventoryItem.buyPrice.toString())
            }
        }
    }
}