package com.athimue.ui.composables.inventory

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.ui.composables.common.SummaryHeader
import com.athimue.ui.composables.inventoryform.InventoryFormModal
import com.athimue.ui.composables.sellform.SellFormDialog

@Suppress("ktlint:standard:function-naming")
@Composable
fun InventoryComposable(
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val uiState by viewModel._uiState.collectAsState()
    var isInventoryFormModalOpen by remember { mutableStateOf(false) }
    var showSellFormModal by remember { mutableStateOf(false) }
    var selectedSneakerId by remember { mutableStateOf<Long?>(null) }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    selectedSneakerId = null
                    isInventoryFormModalOpen = true
                },
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "",
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { content ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(content),
        ) {
            Text(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = "INVENTORY",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 38.sp,
            )
            SummaryHeader(inventory = uiState.inventory)
            if (uiState.inventory.isNotEmpty()) {
                LazyColumn {
                    items(items = uiState.inventory, key = { item -> item.id }) { item ->
                        InventoryItem(
                            inventoryItem = item,
                            onStartToEndSwipe = { itemId ->
                                selectedSneakerId = itemId
                                showSellFormModal = true
                            },
                            onEndToStartSwipe = { itemId ->
                                viewModel.deleteInventoryItem(itemId)
                                Toast.makeText(
                                    context,
                                    "Sneaker deleted !",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            },
                            onItemClick = { itemId ->
                                selectedSneakerId = itemId
                                isInventoryFormModalOpen = true
                            },
                        )
                        HorizontalDivider()
                    }
                }
            } else {
                Text(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    text = "No inventory",
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                )
            }

            if (isInventoryFormModalOpen) {
                InventoryFormModal(
                    selectedSneakerId = selectedSneakerId,
                    closeModal = {
                        selectedSneakerId = null
                        isInventoryFormModalOpen = false
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
                        Toast.makeText(
                            context,
                            "Sneaker added !",
                            Toast.LENGTH_SHORT,
                        ).show()
                    },
                )
            }
            if (showSellFormModal) {
                SellFormDialog(
                    modifier = Modifier,
                    onCloseBtnClick = { showSellFormModal = false },
                    onActionBtnClick = { sellPrice, sellDate, sellPlace ->
                        selectedSneakerId?.let {
                            viewModel.addSell(
                                it,
                                sellPrice,
                                sellDate,
                                sellPlace,
                            )
                            showSellFormModal = false
                            Toast.makeText(
                                context,
                                "Sneaker sold !",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    },
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun LazyItemScope.InventoryItem(
    inventoryItem: InventoryUiModel,
    onStartToEndSwipe: (Long) -> Unit,
    onEndToStartSwipe: (Long) -> Unit,
    onItemClick: (Long) -> Unit,
) {
    val currentItem by rememberUpdatedState(inventoryItem)
    val dismissState =
        rememberSwipeToDismissBoxState(confirmValueChange = {
            when (it) {
                SwipeToDismissBoxValue.EndToStart -> {
                    onEndToStartSwipe(currentItem.id)
                    false
                }

                SwipeToDismissBoxValue.StartToEnd -> {
                    onStartToEndSwipe(currentItem.id)
                    false
                }

                else -> false
            }
        }, positionalThreshold = { 0.35f })
    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier
            .padding(vertical = 1.dp)
            .animateItem(fadeInSpec = null, fadeOutSpec = null),
        backgroundContent = {
            DismissBackground(dismissState)
        },
    ) {
        InventoryItemCard(
            inventoryItem = inventoryItem,
            onClick = onItemClick,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun InventoryItemCard(
    inventoryItem: InventoryUiModel,
    onClick: (Long) -> Unit,
) {
    Row(
        modifier =
        Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Color.White)
            .clickable { onClick(inventoryItem.id) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = rememberAsyncImagePainter(inventoryItem.picture),
            contentDescription = "",
            modifier = Modifier.size(100.dp),
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = inventoryItem.name,
                fontWeight = FontWeight.ExtraBold,
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
                    text = "Price : ${inventoryItem.buyPrice} €",
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    val direction = dismissState.dismissDirection
    val color =
        when (direction) {
            SwipeToDismissBoxValue.EndToStart -> Color(0xFFFF1744)
            SwipeToDismissBoxValue.StartToEnd -> Color(0xFF32CD32)
            else -> {
                Color(0xFFFFFFFF)
            }
        }
    Row(
        modifier =
        Modifier
            .fillMaxSize()
            .background(color),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (direction == SwipeToDismissBoxValue.StartToEnd) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "sell",
                modifier = Modifier.padding(start = 16.dp),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (direction == SwipeToDismissBoxValue.EndToStart) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                modifier = Modifier.padding(end = 16.dp),
            )
        }
    }
}
