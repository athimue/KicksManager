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
import com.athimue.ui.composables.common.DatePicker
import com.athimue.ui.composables.common.DropDownField
import com.athimue.ui.composables.common.ModalHeader
import com.athimue.ui.composables.common.PickerInputField
import com.athimue.ui.composables.sneakerpicker.SneakerPicker
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryFormModal(
    inventoryFormModalUiModel: InventoryFormModalUiModel?,
    closeModal: () -> Unit,
    addInventory: (InventoryFormModalUiModel) -> Unit
) {
    val inventoryFormModalState = rememberModalBottomSheetState(true)
    var inventoryFormModalUiModel by remember { mutableStateOf(InventoryFormModalUiModel()) }

    val sizes = listOf(
        "38", "38.5", "39", "40", "40.5", "41", "42", "42.5", "42 2/3", "43", "44", "44.5", "45"
    )
    val shops = listOf(
        "ADIDAS / CONFIRMED", "BSTN", "BUZZZ", "COURIR", "FOOT DISTRICT", "GLF", "IMPACT", "KITH",
        "NIKE / SNKRS", "PATTA", "SNIPES", "SOLEBOX", "STARCOW", "SUPREME", "SVD",
    )
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        sheetState = inventoryFormModalState,
        onDismissRequest = {
            inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                showDatePicker = false, showSneakerPicker = false
            )
            closeModal()
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ModalHeader(title = "Add an item", onCloseBtnClick = {
                inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                    showDatePicker = false, showSneakerPicker = false
                )
                closeModal()
            })
            PickerInputField(title = "Item name",
                value = inventoryFormModalUiModel.name,
                onClick = {
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        showSneakerPicker = true
                    )
                })
            DropDownField(
                title = "Item size",
                itemSelected = inventoryFormModalUiModel.size,
                onItemSelected = {
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        size = it
                    )
                },
                choices = sizes
            )
            InputField(
                title = "Purchasing price",
                value = inventoryFormModalUiModel.buyPrice.toString(),
                onValueChange = {
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        buyPrice = it.toDouble()
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                )
            )
            PickerInputField(title = "Purchasing date",
                value = inventoryFormModalUiModel.buyDate,
                onClick = {
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        showDatePicker = true
                    )
                })
            DropDownField(
                title = "Purchasing place",
                itemSelected = inventoryFormModalUiModel.buyPlace,
                onItemSelected = {
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        buyPlace = it
                    )
                },
                choices = shops
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
                if (inventoryFormModalUiModel.isCompleted()) {
                    addInventory(inventoryFormModalUiModel)
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        name = "",
                        size = "",
                        picture = "",
                        buyPrice = 0.0,
                        buyDate = "",
                        buyPlace = ""
                    )
                    closeModal()
                }
            }) {
                Text(text = "Add")
            }
            DatePicker(isDialogDisplayed = inventoryFormModalUiModel.showDatePicker,
                closeDialog = {
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        showDatePicker = false
                    )
                },
                onDateSelected = {
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        buyDate = it
                    )
                })
            SneakerPicker(isDialogDisplayed = inventoryFormModalUiModel.showSneakerPicker,
                closeDialog = {
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        showSneakerPicker = false
                    )
                },
                onSneakerSelected = { nameSelected, pictureSelected ->
                    inventoryFormModalUiModel = inventoryFormModalUiModel.copy(
                        name = nameSelected,
                        picture = pictureSelected,
                        showSneakerPicker = false
                    )
                })
        }
    }
}

@Composable
fun InputField(
    title: String, value: String, onValueChange: (String) -> Unit, keyboardOptions: KeyboardOptions
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