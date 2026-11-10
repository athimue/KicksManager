package com.athimue.ui.composables.sneakerpicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.athimue.core.ui.KicksIcons
import com.athimue.core.ui.SneakerImage
import com.athimue.feature_inventory.ui.R
import com.athimue.ui.composables.common.ModalHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerPicker(
    viewModel: SneakerPickerViewModel = hiltViewModel(),
    isDialogDisplayed: Boolean,
    closeDialog: () -> Unit,
    onSneakerSelected: (String, String) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val uiState by viewModel._uiState.collectAsState()
    var query by remember { mutableStateOf("") }

    // Première recherche une seule fois (et non à chaque recomposition)
    LaunchedEffect(Unit) {
        viewModel.searchSneaker()
    }

    if (isDialogDisplayed) {
        ModalBottomSheet(
            onDismissRequest = closeDialog,
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                ModalHeader(
                    title = stringResource(R.string.inventory_picker_title),
                    onCloseBtnClick = closeDialog,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    value = query,
                    onValueChange = {
                        query = it
                        viewModel.searchSneaker(it)
                    },
                    singleLine = true,
                    shape = MaterialTheme.shapes.extraLarge,
                    placeholder = { Text(text = stringResource(R.string.inventory_picker_hint)) },
                    leadingIcon = { Icon(painter = KicksIcons.Search, contentDescription = null) },
                )
                LazyColumn(
                    modifier = Modifier.padding(top = 8.dp),
                ) {
                    items(uiState.sneakers) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSneakerSelected(it.name, it.picture) }
                                .padding(horizontal = 8.dp, vertical = 8.dp),
                        ) {
                            SneakerImage(url = it.picture, size = 64.dp)
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Text(
                                    text = it.brand,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
