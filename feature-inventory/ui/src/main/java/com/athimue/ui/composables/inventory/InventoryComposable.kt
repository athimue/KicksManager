package com.athimue.ui.composables.inventory

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.athimue.domain.model.InventorySort
import com.athimue.feature_inventory.ui.R
import com.athimue.ui.composables.common.SummaryHeader
import com.athimue.ui.composables.inventoryform.InventoryFormModal
import com.athimue.ui.composables.sellform.SellFormDialog
import kotlinx.coroutines.launch

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
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    // Le bouton « Ajouter » se réduit à une icône dès qu'on fait défiler la liste
    val isFabExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    val openAddForm: () -> Unit = {
        selectedSneakerId = null
        isInventoryFormModalOpen = true
    }

    Scaffold(
        topBar = { KicksTopBar(title = stringResource(R.string.inventory_title)) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if (uiState.totalPairs > 0) {
                ExtendedFloatingActionButton(
                    onClick = openAddForm,
                    expanded = isFabExpanded,
                    icon = { Icon(painter = KicksIcons.Add, contentDescription = null) },
                    text = { Text(text = stringResource(R.string.inventory_add)) },
                )
            }
        },
    ) { innerPadding ->
        if (uiState.totalPairs == 0) {
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
                    SummaryHeader(totalPairs = uiState.totalPairs, totalValue = uiState.totalValue)
                }
                item(key = "search") {
                    SearchAndSort(
                        query = uiState.query,
                        sort = uiState.sort,
                        onQueryChange = viewModel::onQueryChange,
                        onSortChange = viewModel::onSortChange,
                    )
                }
                if (uiState.inventory.isEmpty()) {
                    item(key = "no_result") {
                        Text(
                            text = stringResource(R.string.inventory_no_result),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(vertical = 24.dp, horizontal = 4.dp),
                        )
                    }
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
                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                val result = snackbarHostState.showSnackbar(
                                    message = context.getString(R.string.inventory_toast_deleted),
                                    actionLabel = context.getString(R.string.inventory_undo),
                                )
                                if (result == SnackbarResult.ActionPerformed) viewModel.undoDelete()
                            }
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
                onActionBtnClick = { sellPrice, sellDate, sellPlace, fees ->
                    selectedSneakerId?.let {
                        viewModel.addSell(
                            it,
                            sellPrice,
                            sellDate,
                            sellPlace,
                            fees,
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

@Suppress("ktlint:standard:function-naming")
@Composable
private fun SearchAndSort(
    query: String,
    sort: InventorySort,
    onQueryChange: (String) -> Unit,
    onSortChange: (InventorySort) -> Unit,
) {
    // Un seul enfant par élément de LazyColumn : on regroupe dans une Column
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
            placeholder = { Text(text = stringResource(R.string.inventory_search_hint)) },
            leadingIcon = { Icon(painter = KicksIcons.Search, contentDescription = null) },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { onQueryChange("") }) {
                        Icon(
                            painter = KicksIcons.Close,
                            contentDescription = stringResource(R.string.inventory_clear_search),
                        )
                    }
                }
            },
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            InventorySort.entries.forEach { option ->
                FilterChip(
                    selected = option == sort,
                    onClick = { onSortChange(option) },
                    label = { Text(text = stringResource(option.labelRes())) },
                )
            }
        }
    }
}

private fun InventorySort.labelRes(): Int =
    when (this) {
        InventorySort.RECENT -> R.string.inventory_sort_recent
        InventorySort.PRICE_ASC -> R.string.inventory_sort_price_asc
        InventorySort.PRICE_DESC -> R.string.inventory_sort_price_desc
        InventorySort.NAME -> R.string.inventory_sort_name
        InventorySort.SIZE -> R.string.inventory_sort_size
    }
