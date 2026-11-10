package com.athimue.ui.composables.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.athimue.core.ui.KicksIcons
import com.athimue.feature_inventory.ui.R

@Composable
fun ModalHeader(
    title: String,
    onCloseBtnClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.weight(1f),
        )
        IconButton(onClick = onCloseBtnClick) {
            Icon(
                painter = KicksIcons.Close,
                contentDescription = stringResource(R.string.inventory_close),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModalHeaderPreview() {
    ModalHeader(
        title = "Title",
        onCloseBtnClick = {}
    )
}
