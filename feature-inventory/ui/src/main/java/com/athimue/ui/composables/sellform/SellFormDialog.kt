package com.athimue.ui.composables.sellform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import com.athimue.core.ui.KicksIcons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.athimue.feature_inventory.ui.R
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
    // « 89,90 » est accepté comme « 89.90 »
    val normalizedPrice = sellPrice.trim().replace(',', '.')
    val isValid = normalizedPrice.toDoubleOrNull() != null && sellDate.isNotBlank() && sellPlace.isNotBlank()

    ModalBottomSheet(
        onDismissRequest = onCloseBtnClick,
        sheetState = sheetState
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
        ) {
            ModalHeader(
                title = stringResource(R.string.inventory_sell_title),
                onCloseBtnClick = onCloseBtnClick,
            )
            InputField(
                title = stringResource(R.string.inventory_sell_price),
                value = sellPrice,
                onValueChange = { sellPrice = it },
                keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                ),
            )
            PickerInputField(
                title = stringResource(R.string.inventory_sell_date),
                value = sellDate,
                onClick = { showDatePicker = true },
                trailingIcon = { Icon(painter = KicksIcons.Calendar, contentDescription = null) },
            )
            DropDownField(
                title = stringResource(R.string.inventory_sell_place),
                itemSelected = sellPlace,
                onItemSelected = { sellPlace = it },
                choices = SellShop.entries.map { it.shopName },
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .height(52.dp),
                enabled = isValid,
                onClick = { onActionBtnClick(normalizedPrice, sellDate, sellPlace) },
            ) {
                Text(text = stringResource(R.string.inventory_sell_confirm))
            }
            DatePicker(
                isDialogDisplayed = showDatePicker,
                closeDialog = { showDatePicker = false },
                onDateSelected = { sellDate = it },
            )
        }
    }
}
