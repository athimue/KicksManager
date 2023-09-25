package com.athimue.ui.composables.sellform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.athimue.ui.composables.common.DatePicker
import com.athimue.ui.composables.common.DropDownField
import com.athimue.ui.composables.common.ModalHeader
import com.athimue.ui.composables.common.PickerInputField
import com.athimue.ui.composables.inventoryform.InputField

@Composable
fun SellFormModal(
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
                DatePicker(
                    isDialogDisplayed = showDatePicker,
                    closeDialog = { showDatePicker = false },
                    onDateSelected = { sellDate = it })
            }
        }
    }
}