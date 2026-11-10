package com.athimue.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Carte « paire de sneakers » : photo, nom, badges d'infos et une colonne à droite (prix, bénéfice…).
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SneakerCard(
    name: String,
    picture: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    details: @Composable () -> Unit = {},
    trailing: @Composable ColumnScope.() -> Unit = {},
) {
    val content: @Composable () -> Unit = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SneakerImage(url = picture)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    details()
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                content = trailing,
            )
        }
    }
    if (onClick != null) {
        ElevatedCard(onClick = onClick, modifier = modifier.fillMaxWidth()) { content() }
    } else {
        ElevatedCard(modifier = modifier.fillMaxWidth()) { content() }
    }
}

@Preview
@Composable
private fun SneakerCardPreview() {
    SneakerCard(
        name = "Air Jordan 1 Retro High OG Chicago",
        picture = "",
        details = {
            InfoChip(text = "Size 42")
            InfoChip(text = "Qty 1")
        },
        trailing = {
            Text(text = formatPrice(180.0), style = MaterialTheme.typography.titleMedium)
            ProfitText(amount = 45.0)
        },
    )
}
