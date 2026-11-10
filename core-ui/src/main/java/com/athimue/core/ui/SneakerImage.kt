package com.athimue.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter

/**
 * Photo de sneaker sur fond blanc arrondi : les photos produit ont presque toujours un fond blanc,
 * on garde donc ce fond aussi en thème sombre pour éviter l'effet « carré blanc ».
 */
@Composable
fun SneakerImage(
    url: String,
    modifier: Modifier = Modifier,
    size: Dp = 88.dp,
    contentDescription: String? = null,
) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(size)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.White)
            .padding(6.dp),
    )
}
