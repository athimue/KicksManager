package com.athimue.ui.composables.inventory

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = "INVENTORY",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 38.sp
            )
            Divider()
            Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
                Text(
                    text = "${uiState.inventory.size} items",
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${
                    uiState.inventory.map { inventoryItem -> inventoryItem.buyPrice }
                        .fold(0.0) { acc, buyPriceItem -> acc + buyPriceItem }
                } €", modifier = Modifier.padding(end = 10.dp))
            }
            Divider()
            if (uiState.inventory.isNotEmpty()) LazyColumn {
                items(uiState.inventory) { item ->
                    InventoryItemRow(item)
                    Divider()
                }
            }
            else Text(text = "No inventory")
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(inventoryItem.picture),
            contentDescription = "",
            modifier = Modifier.size(100.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = inventoryItem.name, fontWeight = FontWeight.ExtraBold
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    text = "Qty : ${inventoryItem.quantity}",
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Size : ${inventoryItem.size}",
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Price : ${inventoryItem.buyPrice} €"
                )
            }
        }
    }
}