package com.athimue.ui.composables.inventory

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.domain.models.InventoryItem
import com.athimue.ui.composables.common.DropDownField
import com.athimue.ui.composables.common.ModalHeader
import com.athimue.ui.composables.common.PickerInputField
import com.athimue.ui.composables.common.SummaryHeader
import com.athimue.ui.composables.inventoryform.InputField
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
    var showSellFormModal by remember { mutableStateOf(false) }
    var currentItemId by remember { mutableStateOf(0L) }

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
                items(items = uiState.inventory, key = { item -> item.id }) { item ->
                    InventoryItem(inventoryItem = item,
                        onStartToEndSwipe = {
                            currentItemId = it
                            showSellFormModal = true
                        },
                        onEndToStartSwipe = { viewModel.deleteInventoryItem(it) })
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
            InventoryFormModal(formModalState = formModalState,
                showFormModal = showFormModal,
                closeModal = { showFormModal = false },
                addInventory = { name, picture, size, price, date, place ->
                    viewModel.addInventoryItem(
                        name, picture, size, price, date, place
                    )
                })
            SellForm(
                isDialogDisplayed = showSellFormModal,
                onCloseBtnClick = { showSellFormModal = false },
                onActionBtnClick = { sellPrice, sellDate, sellPlace ->
                    viewModel.addSell(
                        currentItemId, sellPrice, sellDate, sellPlace
                    )
                    showSellFormModal = false
                }
            )
        }
    }
}

@Composable
private fun SellForm(
    isDialogDisplayed: Boolean,
    onCloseBtnClick: () -> Unit,
    onActionBtnClick: (String, String, String) -> Unit,
) {
    var sellPrice by rememberSaveable { mutableStateOf("") }
    var sellDate by rememberSaveable { mutableStateOf("") }
    var sellPlace by rememberSaveable { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    if (isDialogDisplayed) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = onCloseBtnClick
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                ModalHeader(
                    title = "Item sold",
                    onCloseBtnClick = onCloseBtnClick
                )
                InputField(
                    title = "Sell price",
                    value = sellPrice,
                    onValueChange = { sellPrice = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                PickerInputField(
                    title = "Sell date",
                    value = sellDate,
                    onClick = { showDatePicker = true })
                DropDownField(
                    title = "Sell place",
                    itemSelected = sellPlace,
                    onItemSelected = { sellPlace = it },
                    choices = listOf("Vinted", "WeTheNew", "GOAT", "Face2Face")
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { onActionBtnClick(sellPrice, sellDate, sellPlace) }
                ) {
                    Text(text = "Add the sell")
                }
                com.athimue.ui.composables.common.DatePicker(
                    isDialogDisplayed = showDatePicker,
                    closeDialog = { showDatePicker = false },
                    onDateSelected = { sellDate = it })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.InventoryItem(
    inventoryItem: InventoryItem,
    onStartToEndSwipe: (Long) -> Unit,
    onEndToStartSwipe: (Long) -> Unit
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
            InventoryItemCard(inventoryItem)
        })
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
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (direction == DismissDirection.EndToStart) Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "delete",
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}