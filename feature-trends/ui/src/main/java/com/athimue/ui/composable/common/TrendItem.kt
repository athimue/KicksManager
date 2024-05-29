package com.athimue.ui.composable.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ColumnScope.TrendItem(
    picture: String,
    name: String,
    sku: String,
) {
    Image(
        painter = rememberAsyncImagePainter(picture),
        contentDescription = null,
        modifier = Modifier.size(120.dp),
    )
    Text(
        text =
            if (name.length > 10) {
                name.substring(
                    0,
                    8,
                ) + ".."
            } else {
                name
            },
    )
    Text(text = sku)
}
