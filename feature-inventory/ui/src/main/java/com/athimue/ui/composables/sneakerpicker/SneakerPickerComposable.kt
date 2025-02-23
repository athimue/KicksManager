package com.athimue.ui.composables.sneakerpicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
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
    viewModel.searchSneaker()

    if (isDialogDisplayed) {
        ModalBottomSheet(
            onDismissRequest = closeDialog,
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFFf3edfd))
                    .padding(10.dp),
            ) {
                ModalHeader(
                    title = "Sneaker search",
                    onCloseBtnClick = closeDialog,
                )
                OutlinedTextField(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = query,
                    onValueChange = {
                        query = it
                        viewModel.searchSneaker(it)
                    },
                    singleLine = true,
                )
                LazyColumn {
                    items(uiState.sneakers) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable { onSneakerSelected(it.name, it.picture) },
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(it.picture),
                                contentDescription = "",
                                modifier =
                                Modifier
                                    .size(100.dp)
                                    .padding(top = 4.dp, bottom = 4.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .padding(bottom = 3.dp),
                            )
                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                Text(text = it.name)
                                Text(text = it.brand)
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
