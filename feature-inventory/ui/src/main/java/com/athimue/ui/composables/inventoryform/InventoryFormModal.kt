package com.athimue.ui.composables.inventoryform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.ui.composables.common.DatePicker
import com.athimue.ui.composables.common.DropDownField
import com.athimue.ui.composables.common.ModalHeader
import com.athimue.ui.composables.common.PickerInputField
import com.athimue.ui.composables.inventory.InventoryViewModel
import com.athimue.ui.composables.sneakerpicker.SneakerPicker
import com.athimue.ui.constants.ShoeSize
import com.athimue.ui.constants.Shop
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
    viewModel.loadInventoryItem(selectedSneakerId)

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
                        viewModel.closePickers()
                        closeModal()
                    })
                PickerInputField(title = "Item name",
                    value = uiState.name,
                    onClick = {
                        viewModel.closePickers()
                    })
                DropDownField(
                    title = "Item size",
                    itemSelected = uiState.size,
                    onItemSelected = {
                        viewModel.setSize(it)
                    },
                    choices = ShoeSize.values().map { it.size }
                )
                InputField(
                    title = "Purchasing price",
                    value = uiState.buyPrice.toString(),
                    onValueChange = {
                        viewModel.setBuyPrice(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                PickerInputField(title = "Purchasing date",
                    value = uiState.buyDate,
                    onClick = {
                        viewModel.showDatePicker()
                    })
                DropDownField(
                    title = "Purchasing place",
                    itemSelected = uiState.buyPlace,
                    onItemSelected = {
                        viewModel.setBuyPlace(it)
                    },
                    choices = Shop.values().map { it.shopName }
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
                        viewModel.closePickers()
                    },
                    onDateSelected = {

                    })
                SneakerPicker(
                    isDialogDisplayed = uiState.showSneakerPicker,
                    closeDialog = {
                        viewModel.closePickers()
                    },
                    onSneakerSelected = { nameSelected, pictureSelected ->
                        viewModel.setNameAndPicture(nameSelected, pictureSelected)
                        viewModel.closePickers()
                    })
            }
        }
    }
}

@Composable
fun InputField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = title, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            label = { Text(title) },
            keyboardOptions = keyboardOptions,
        )
    }
}