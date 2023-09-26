package com.athimue.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyFigureCard(title: String, subTitle: String, color: Long) {
    Card(
        modifier = Modifier
            .padding(15.dp)
            .size(90.dp),
        colors = CardDefaults.cardColors(containerColor = Color(color)),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = title, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = subTitle, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}