package com.athimue.ui.composables.inventory

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.athimue.core.ui.KicksIcons
import com.athimue.core.ui.EmptyState
import com.athimue.core.ui.InfoChip
import com.athimue.core.ui.KicksTopBar
import com.athimue.core.ui.SneakerCard
import com.athimue.core.ui.SwipeActionBackground
import com.athimue.core.ui.formatPrice
import com.athimue.feature_inventory.ui.R
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
    val listState = rememberLazyListState()
    // Le bouton « Ajouter » se réduit à une icône dès qu'on fait défiler la liste
    val isFabExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    val openAddForm: () -> Unit = {
        selectedSneakerId = null
        isInventoryFormModalOpen = true
    }

    Scaffold(
        topBar = { KicksTopBar(title = stringResource(R.string.inventory_title)) },
        floatingActionButton = {
            if (uiState.inventory.isNotEmpty()) {
                ExtendedFloatingActionButton(
                    onClick = openAddForm,
                    expanded = isFabExpanded,
                    icon = { Icon(painter = KicksIcons.Add, contentDescription = null) },
                    text = { Text(text = stringResource(R.string.inventory_add)) },
                )
            }
        },
    ) { innerPadding ->
        if (uiState.inventory.isEmpty()) {
            EmptyState(
                modifier = Modifier.padding(innerPadding),
                emoji = "👟",
                title = stringResource(R.string.inventory_empty_title),
                message = stringResource(R.string.inventory_empty_message),
                actionLabel = stringResource(R.string.inventory_add),
                onAction = openAddForm,
            )
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding() + 8.dp,
                    // place pour le bouton flottant
                    bottom = innerPadding.calculateBottomPadding() + 88.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item(key = "summary") {
                    SummaryHeader(inventory = uiState.inventory)
                }
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
                                context.getString(R.string.inventory_toast_deleted),
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                        onItemClick = { itemId ->
                            selectedSneakerId = itemId
                            isInventoryFormModalOpen = true
                        },
                    )
                }
            }
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
                        context.getString(R.string.inventory_toast_saved),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
            )
        }
        if (showSellFormModal) {
            SellFormDialog(
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
                            context.getString(R.string.inventory_toast_sold),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                },
            )
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
        }, positionalThreshold = { it * 0.35f })
    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
        backgroundContent = {
            SwipeActionBackground(direction = dismissState.dismissDirection)
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
    SneakerCard(
        name = inventoryItem.name,
        picture = inventoryItem.picture,
        onClick = { onClick(inventoryItem.id) },
        details = {
            InfoChip(text = stringResource(R.string.inventory_size, inventoryItem.size))
            if (inventoryItem.quantity > 1) {
                InfoChip(text = stringResource(R.string.inventory_qty, inventoryItem.quantity))
            }
        },
        trailing = {
            Text(
                text = formatPrice(inventoryItem.buyPrice),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(R.string.inventory_bought_for),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
    )
}
