package com.athimue.ui.composables.inventory

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
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
import com.athimue.domain.models.InventoryItem
import com.athimue.ui.composables.common.SummaryHeader
import com.athimue.ui.composables.inventoryform.InventoryFormModal
import kotlinx.coroutines.delay
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
            SummaryHeader(inventory = uiState.inventory)
            if (uiState.inventory.isNotEmpty()) LazyColumn {
                items(
                    items = uiState.inventory,
                    key = { item -> item.id }
                ) { item ->
                    InventoryItem(
                        inventoryItem = item,
                        onRemove = { viewModel.deleteInventoryItem(it) }
                    )
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.InventoryItem(
    inventoryItem: InventoryItem,
    onRemove: (Long) -> Unit
) {
    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(inventoryItem)
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                show = false
                true
            } else false
        }, positionalThreshold = { 1.dp.toPx() }
    )
    AnimatedVisibility(
        show, exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier
                .padding(vertical = 1.dp)
                .animateItemPlacement(),
            directions = setOf(DismissDirection.EndToStart),
            background = {
                DismissBackground(dismissState)
            },
            dismissContent = {
                InventoryItemCard(inventoryItem)
            }
        )
    }

    LaunchedEffect(show) {
        if (!show) {
            delay(200)
            onRemove(currentItem.id)
        }
    }
}

@Composable
private fun InventoryItemCard(
    inventoryItem: InventoryItem
) {
    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Color.White),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.targetValue) {
        DismissValue.DismissedToStart -> Color(0xFFFF1744)
        else -> Color.Transparent
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "delete",
            modifier = Modifier.padding(end = 10.dp)
        )
    }
}