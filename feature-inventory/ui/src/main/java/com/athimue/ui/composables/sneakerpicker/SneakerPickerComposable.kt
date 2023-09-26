package com.athimue.ui.composables.sneakerpicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.ui.composables.common.ModalHeader

@Composable
fun SneakerPicker(
    viewModel: SneakerPickerViewModel = hiltViewModel(),
    isDialogDisplayed: Boolean,
    closeDialog: () -> Unit,
    onSneakerSelected: (String, String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }
    viewModel.searchSneaker()

    if (isDialogDisplayed) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = closeDialog
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                ModalHeader(
                    title = "Sneaker search",
                    onCloseBtnClick = closeDialog
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSneakerSelected(it.name, it.picture) }
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(it.picture),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(top = 4.dp, bottom = 4.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .padding(bottom = 3.dp)
                            )
                            Column(modifier = Modifier.padding(start = 8.dp)) {
                                Text(text = it.name)
                                Text(text = it.brand)
                            }
                        }
                        Divider()
                    }
                }
            }
        }
    }
}
