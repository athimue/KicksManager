package com.athimue.ui.composables.sellform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.athimue.ui.composables.common.DatePicker
import com.athimue.ui.composables.common.DropDownField
import com.athimue.ui.composables.common.InputField
import com.athimue.ui.composables.common.ModalHeader
import com.athimue.ui.composables.common.PickerInputField
import com.athimue.ui.constants.SellShop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellFormDialog(
    modifier: Modifier = Modifier,
    onCloseBtnClick: () -> Unit,
    onActionBtnClick: (String, String, String) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var sellPrice by rememberSaveable { mutableStateOf("") }
    var sellDate by rememberSaveable { mutableStateOf("") }
    var sellPlace by rememberSaveable { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onCloseBtnClick,
        sheetState = sheetState
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            ModalHeader(
                title = "Item sold",
                onCloseBtnClick = onCloseBtnClick,
            )
            InputField(
                title = "Sell price",
                value = sellPrice,
                onValueChange = { sellPrice = it },
                keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                ),
            )
            PickerInputField(
                title = "Sell date",
                value = sellDate,
                onClick = { showDatePicker = true },
            )
            DropDownField(
                title = "Sell place",
                itemSelected = sellPlace,
                onItemSelected = { sellPlace = it },
                choices = SellShop.entries.map { it.shopName },
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { onActionBtnClick(sellPrice, sellDate, sellPlace) },
            ) {
                Text(text = "Add the sell")
            }
            DatePicker(
                isDialogDisplayed = showDatePicker,
                closeDialog = { showDatePicker = false },
                onDateSelected = { sellDate = it },
            )
        }
    }
}
