package com.athimue.ui.composables.inventoryform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.athimue.ui.composables.sneakerpicker.SneakerPicker
import com.athimue.ui.composables.common.DatePicker
import com.athimue.ui.composables.common.PickerInputField
import com.athimue.ui.composables.common.ModalHeader
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryFormModal(
    formModalState: SheetState,
    showFormModal: Boolean,
    closeModal: () -> Unit,
    addInventory: (String, String, String, Double, String, String) -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var size by rememberSaveable { mutableStateOf("") }
    var picture by rememberSaveable { mutableStateOf("") }
    var buyPrice by rememberSaveable { mutableStateOf("") }
    var buyPlace by rememberSaveable { mutableStateOf("") }
    var buyDate by rememberSaveable { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showSneakerPicker by remember { mutableStateOf(false) }

    if (showFormModal) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxSize(),
            sheetState = formModalState,
            onDismissRequest = {
                showDatePicker = false
                showSneakerPicker = false
                closeModal()
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ModalHeader(
                    title = "Add an item",
                    onClick = {
                        showDatePicker = false
                        showSneakerPicker = false
                        closeModal()
                    }
                )
                PickerInputField(
                    title = "Item name",
                    value = name,
                    onClick = { showSneakerPicker = true })
                DropDownField(
                    title = "Item size",
                    itemSelected = size,
                    onItemSelected = { size = it },
                    choices =
                    listOf(
                        "38",
                        "38.5",
                        "39",
                        "40",
                        "40.5",
                        "41",
                        "42",
                        "42.5",
                        "42 2/3",
                        "43",
                        "44",
                        "44.5",
                        "45"
                    )
                )
                InputField(
                    title = "Purchasing price",
                    value = buyPrice,
                    onValueChange = { buyPrice = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
                PickerInputField(
                    title = "Purchasing date",
                    value = buyDate,
                    onClick = { showDatePicker = true })
                DropDownField(
                    title = "Purchasing place",
                    itemSelected = buyPlace,
                    onItemSelected = { buyPlace = it },
                    choices =
                    listOf(
                        "Adidas / Confirmed",
                        "BSTN",
                        "Buzzz",
                        "Courir",
                        "Foot District",
                        "GLF",
                        "Impact",
                        "KITH",
                        "Nike / SNKRS",
                        "Patta",
                        "Snipes",
                        "Solebox",
                        "Starcow",
                        "Supreme",
                        "SVD",
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
                    addInventory(
                        name, picture, size, buyPrice.toDouble(), buyDate.toString(), buyPlace
                    )
                    closeModal()
                }) {
                    Text(text = "Add")
                }
                DatePicker(isDialogDisplayed = showDatePicker,
                    closeDialog = { showDatePicker = false },
                    onDateSelected = { buyDate = it })
                SneakerPicker(isDialogDisplayed = showSneakerPicker,
                    closeDialog = { showSneakerPicker = false },
                    onSneakerSelected = { nameSelected, pictureSelected ->
                        name = nameSelected
                        picture = pictureSelected
                        showSneakerPicker = false
                    })
            }
        }
    }
}

@Composable
fun ColumnScope.InputField(
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

@Composable
fun DropDownField(
    title: String,
    choices: List<String>,
    itemSelected: String,
    onItemSelected: (String) -> Unit
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var dropDownTextField by remember { mutableStateOf(Size.Zero) }
    val icon =
        if (isDropDownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = title, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace
        )
        Box {
            OutlinedTextField(value = itemSelected,
                onValueChange = onItemSelected,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        dropDownTextField = coordinates.size.toSize()
                    },
                label = { Text(title) },
                trailingIcon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = ""
                    )
                })
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = { isDropDownExpanded = !isDropDownExpanded }),
            )
        }
        DropdownMenu(
            expanded = isDropDownExpanded,
            onDismissRequest = { isDropDownExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { dropDownTextField.width.toDp() })
        ) {
            choices.forEach { label ->
                DropdownMenuItem(onClick = {
                    onItemSelected(label)
                    isDropDownExpanded = false
                }, text = { Text(text = label) })
            }
        }
    }
}