package com.athimue.ui.composables.inventoryform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.ui.composables.common.DatePicker
import com.athimue.ui.composables.common.DropDownField
import com.athimue.ui.composables.common.InputField
import com.athimue.ui.composables.common.ModalHeader
import com.athimue.ui.composables.common.PickerInputField
import com.athimue.ui.composables.sneakerpicker.SneakerPicker
import com.athimue.ui.constants.ShoeSize
import com.athimue.ui.constants.BuyShop
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryFormModal(
    viewModel: InventoryFormModalViewModel = hiltViewModel(),
    selectedSneakerId: Long,
    closeModal: () -> Unit,
    addInventory: (InventoryFormModalUiModel) -> Unit
) {
    val inventoryFormModalState = rememberModalBottomSheetState(true)
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(selectedSneakerId) {
        viewModel.loadInventoryItem(selectedSneakerId)
    }

    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        sheetState = inventoryFormModalState,
        onDismissRequest = {
            closeModal()
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                ModalHeader(
                    title = if (selectedSneakerId.toInt() != 0)
                        "Update an item" else "Add an item",
                    onCloseBtnClick = {
                        viewModel.closeDatePicker()
                        viewModel.closeSneakerPicker()
                        closeModal()
                    })
                PickerInputField(
                    title = "Item name",
                    value = uiState.name,
                    onClick = viewModel::showSneakerPicker
                )
                DropDownField(
                    title = "Item size",
                    itemSelected = uiState.size,
                    onItemSelected = viewModel::setSize,
                    choices = ShoeSize.values().map { it.size }
                )
                InputField(
                    title = "Purchasing price",
                    value = uiState.buyPrice.toString(),
                    onValueChange = viewModel::setBuyPrice,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                PickerInputField(
                    title = "Purchasing date",
                    value = uiState.buyDate,
                    onClick = viewModel::showDatePicker
                )
                DropDownField(
                    title = "Purchasing place",
                    itemSelected = uiState.buyPlace,
                    onItemSelected = viewModel::setBuyPlace,
                    choices = BuyShop.values().map { it.shopName }
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        if (uiState.isCompleted()) {
                            addInventory(uiState)
                            closeModal()
                        }
                    }) {
                    Text(text = "Add")
                }
                DatePicker(
                    isDialogDisplayed = uiState.showDatePicker,
                    closeDialog = {
                        viewModel.closeDatePicker()
                    },
                    onDateSelected = viewModel::setBuyDate
                )
                SneakerPicker(
                    isDialogDisplayed = uiState.showSneakerPicker,
                    closeDialog = {
                        viewModel.closeSneakerPicker()
                    },
                    onSneakerSelected = { name, picture ->
                        viewModel.setNameAndPicture(name, picture)
                        viewModel.closeSneakerPicker()
                    }
                )
            }
        }
    }
}
