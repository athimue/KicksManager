package com.athimue.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.athimue.core_ui.R

/** Fond affiché derrière une carte qu'on fait glisser : vert = vendre, rouge = supprimer. */
@Composable
fun SwipeActionBackground(
    direction: SwipeToDismissBoxValue,
    modifier: Modifier = Modifier,
) {
    val containerColor = when (direction) {
        SwipeToDismissBoxValue.StartToEnd -> KicksColors.successContainer
        SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.errorContainer
        else -> Color.Transparent
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.large)
            .background(containerColor)
            .padding(horizontal = 24.dp),
    ) {
        when (direction) {
            SwipeToDismissBoxValue.StartToEnd -> Icon(
                painter = KicksIcons.Payments,
                contentDescription = stringResource(R.string.core_ui_sell),
                tint = KicksColors.onSuccessContainer,
                modifier = Modifier.align(Alignment.CenterStart),
            )

            SwipeToDismissBoxValue.EndToStart -> Icon(
                painter = KicksIcons.Delete,
                contentDescription = stringResource(R.string.core_ui_delete),
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.align(Alignment.CenterEnd),
            )

            else -> Unit
        }
    }
}
