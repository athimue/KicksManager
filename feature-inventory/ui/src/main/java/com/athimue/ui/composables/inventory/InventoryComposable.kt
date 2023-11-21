package com.athimue.ui.composables.inventory

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.ui.composables.common.SummaryHeader
import com.athimue.ui.composables.inventoryform.InventoryFormModal
import com.athimue.ui.composables.sellform.SellFormModal
import java.util.*

@Composable
fun InventoryComposable(
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showInventoryFormModal by remember { mutableStateOf(false) }
    var showSellFormModal by remember { mutableStateOf(false) }
    var selectedSneakerId by remember { mutableLongStateOf(0L) }

    Scaffold(
        modifier = Modifier, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    selectedSneakerId = 0L
                    showInventoryFormModal = true
                }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = ""
                )
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
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
            SummaryHeader(inventory = uiState.inventory)
            if (uiState.inventory.isNotEmpty()) LazyColumn {
                items(items = uiState.inventory, key = { item -> item.id }) { item ->
                    InventoryItem(inventoryItem = item,
                        onStartToEndSwipe = { itemId ->
                            selectedSneakerId = itemId
                            showSellFormModal = true
                        },
                        onEndToStartSwipe = { itemId -> viewModel.deleteInventoryItem(itemId) },
                        onItemClick = { itemId ->
                            selectedSneakerId = itemId
                            showInventoryFormModal = true
                        })
                    Divider()
                }
            }
            else Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = "No inventory",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )

            if (showInventoryFormModal) {
                InventoryFormModal(
                    selectedSneakerId = selectedSneakerId,
                    closeModal = {
                        selectedSneakerId = 0
                        showInventoryFormModal = false
                    },
                    addInventory = { inventoryFormModalUiModel ->
                        viewModel.addOrUpdateInventoryItem(
                            inventoryFormModalUiModel.id,
                            inventoryFormModalUiModel.name,
                            inventoryFormModalUiModel.picture,
                            inventoryFormModalUiModel.size,
                            inventoryFormModalUiModel.buyPrice,
                            inventoryFormModalUiModel.buyDate,
                            inventoryFormModalUiModel.buyPlace,
                        )
                    })
            }
            if (showSellFormModal) {
                SellFormModal(onCloseBtnClick = { showSellFormModal = false },
                    onActionBtnClick = { sellPrice, sellDate, sellPlace ->
                        viewModel.addSell(
                            selectedSneakerId, sellPrice, sellDate, sellPlace
                        )
                        showSellFormModal = false
                    })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.InventoryItem(
    inventoryItem: InventoryUiModel,
    onStartToEndSwipe: (Long) -> Unit,
    onEndToStartSwipe: (Long) -> Unit,
    onItemClick: (Long) -> Unit
) {
    val currentItem by rememberUpdatedState(inventoryItem)
    val dismissState = rememberDismissState(confirmValueChange = {
        when (it) {
            DismissValue.DismissedToStart -> {
                onEndToStartSwipe(currentItem.id)
                false
            }

            DismissValue.DismissedToEnd -> {
                onStartToEndSwipe(currentItem.id)
                false
            }

            else -> false
        }
    }, positionalThreshold = { 0.35f })
    SwipeToDismiss(state = dismissState,
        modifier = Modifier
            .padding(vertical = 1.dp)
            .animateItemPlacement(),
        background = {
            DismissBackground(dismissState)
        },
        dismissContent = {
            InventoryItemCard(
                inventoryItem = inventoryItem, onClick = onItemClick
            )
        })
}

@Composable
private fun InventoryItemCard(
    inventoryItem: InventoryUiModel, onClick: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Color.White)
            .clickable { onClick(inventoryItem.id) }, verticalAlignment = Alignment.CenterVertically
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return
    val color = when (direction) {
        DismissDirection.EndToStart -> Color(0xFFFF1744)
        DismissDirection.StartToEnd -> Color(0xFF32CD32)
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (direction == DismissDirection.StartToEnd) Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "sell",
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (direction == DismissDirection.EndToStart) Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "delete",
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}