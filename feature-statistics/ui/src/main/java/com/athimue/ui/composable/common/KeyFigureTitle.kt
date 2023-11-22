package com.athimue.ui.composable.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun KeyFigureTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 25.sp,
        fontWeight = FontWeight.ExtraBold,
    )
}