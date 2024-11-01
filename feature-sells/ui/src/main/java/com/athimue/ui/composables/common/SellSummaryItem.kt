package com.athimue.ui.composables.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun SellSummaryItem(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    subject: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = icon,
            contentDescription = "",
            colorFilter = ColorFilter.tint(iconColor),
        )
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title)
            Text(text = subject)
        }
    }
}

@Preview
@Composable
fun SellSummaryItemPreview() {
    SellSummaryItem(
        icon = Icons.Rounded.ThumbUp,
        iconColor = Color.Blue,
        title = "10",
        subject = "Sells",
    )
}