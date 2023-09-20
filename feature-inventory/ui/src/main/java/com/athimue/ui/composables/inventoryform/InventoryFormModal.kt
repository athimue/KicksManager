package com.athimue.ui.composables.inventoryform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.athimue.ui.composables.sneakerpicker.SneakerPicker
import com.athimue.ui.composables.common.DatePicker
import com.athimue.ui.composables.common.ExtraInputField
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
    var buyDate by remember { mutableStateOf(Calendar.getInstance().timeInMillis) }
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
                    .padding(16.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        showDatePicker = false
                        showSneakerPicker = false
                        closeModal()
                    }) {
                        Icon(imageVector = Icons.Rounded.Close, contentDescription = "")
                    }
                    Text(
                        "Add an item",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                ExtraInputField(
                    title = "Item name",
                    value = name,
                    onClick = { showSneakerPicker = true }
                )
                InputField(
                    title = "Item size",
                    value = size,
                    onValueChange = { size = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
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
                ExtraInputField(
                    title = "Purchasing date",
                    value = buyDate,
                    onClick = { showDatePicker = true }
                )
                InputField(
                    title = "Purchasing place",
                    value = buyPlace,
                    onValueChange = { buyPlace = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        addInventory(
                            name,
                            picture,
                            size,
                            buyPrice.toDouble(),
                            buyDate.toString(),
                            buyPlace
                        )
                        closeModal()
                    }) {
                    Text(text = "Add")
                }
                DatePicker(
                    isDialogDisplayed = showDatePicker,
                    closeDialog = { showDatePicker = false },
                    onDateSelected = { buyDate = it })
                SneakerPicker(
                    isDialogDisplayed = showSneakerPicker,
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
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
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