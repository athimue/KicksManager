package com.athimue.ui.composables.inventoryform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.athimue.core.ui.SneakerImage
import com.athimue.feature_inventory.ui.R
import com.athimue.ui.composables.common.DatePicker
import com.athimue.ui.composables.common.DropDownField
import com.athimue.ui.composables.common.InputField
import com.athimue.ui.composables.common.ModalHeader
import com.athimue.ui.composables.common.PickerInputField
import com.athimue.ui.composables.sneakerpicker.SneakerPicker
import com.athimue.ui.constants.BuyShop
import com.athimue.ui.constants.ShoeSize

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryFormModal(
    viewModel: InventoryFormModalViewModel = hiltViewModel(),
    selectedSneakerId: Long?,
    closeModal: () -> Unit,
    addInventory: (InventoryFormModalUiModel) -> Unit,
) {
    val inventoryFormModalState = rememberModalBottomSheetState(true)
    val uiState by viewModel.uiState.collectAsState()

    // Chargé une seule fois à l'ouverture (et non à chaque recomposition)
    LaunchedEffect(selectedSneakerId) {
        selectedSneakerId?.let {
            viewModel.loadInventoryItem(it)
        } ?: viewModel.resetUiModel()
    }

    // Texte saisi pour le prix, séparé du Double pour pouvoir vider le champ ou taper « 89,90 »
    var buyPriceText by remember(uiState.isLoading, uiState.id) {
        mutableStateOf(if (uiState.buyPrice == 0.0) "" else uiState.buyPrice.toString())
    }

    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        sheetState = inventoryFormModalState,
        onDismissRequest = {
            closeModal()
        },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .verticalScroll(rememberScrollState()),
        ) {
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                ModalHeader(
                    title = stringResource(
                        if (selectedSneakerId != null) R.string.inventory_form_update_title else R.string.inventory_form_add_title,
                    ),
                    onCloseBtnClick = {
                        viewModel.closeDatePicker()
                        viewModel.closeSneakerPicker()
                        closeModal()
                    },
                )
                if (uiState.picture.isNotEmpty()) {
                    SneakerImage(
                        url = uiState.picture,
                        size = 160.dp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 8.dp),
                    )
                }
                PickerInputField(
                    title = stringResource(R.string.inventory_form_name),
                    value = uiState.name,
                    onClick = viewModel::showSneakerPicker,
                )
                DropDownField(
                    title = stringResource(R.string.inventory_form_size),
                    itemSelected = uiState.size,
                    onItemSelected = viewModel::setSize,
                    choices = ShoeSize.entries.map { it.size },
                )
                InputField(
                    title = stringResource(R.string.inventory_form_buy_price),
                    value = buyPriceText,
                    onValueChange = { text ->
                        buyPriceText = text
                        viewModel.setBuyPrice(text)
                    },
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                        ),
                )
                PickerInputField(
                    title = stringResource(R.string.inventory_form_buy_date),
                    value = uiState.buyDate,
                    onClick = viewModel::showDatePicker,
                )
                DropDownField(
                    title = stringResource(R.string.inventory_form_buy_place),
                    itemSelected = uiState.buyPlace,
                    onItemSelected = viewModel::setBuyPlace,
                    choices = BuyShop.entries.map { it.shopName },
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .height(52.dp),
                    enabled = uiState.isCompleted(),
                    onClick = {
                        addInventory(uiState)
                        closeModal()
                    },
                ) {
                    Text(text = stringResource(R.string.inventory_form_save))
                }
                DatePicker(
                    isDialogDisplayed = uiState.showDatePicker,
                    closeDialog = {
                        viewModel.closeDatePicker()
                    },
                    onDateSelected = viewModel::setBuyDate,
                )
                SneakerPicker(
                    isDialogDisplayed = uiState.showSneakerPicker,
                    closeDialog = {
                        viewModel.closeSneakerPicker()
                    },
                    onSneakerSelected = { name, picture ->
                        viewModel.setNameAndPicture(name, picture)
                        viewModel.closeSneakerPicker()
                    },
                )
            }
        }
    }
}
