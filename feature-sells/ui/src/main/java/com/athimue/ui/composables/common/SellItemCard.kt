package com.athimue.ui.composables.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.athimue.core.ui.InfoChip
import com.athimue.core.ui.ProfitText
import com.athimue.core.ui.SneakerCard
import com.athimue.core.ui.formatPrice
import com.athimue.core.ui.formatShopName
import com.athimue.feature_sells.ui.R
import com.athimue.ui.composables.sells.SellUiModel

@Composable
fun SellItemCard(
    sell: SellUiModel
) {
    SneakerCard(
        name = sell.name,
        picture = sell.picture,
        details = {
            InfoChip(text = stringResource(R.string.sales_size, sell.size))
            if (sell.sellPlace.isNotBlank()) InfoChip(text = formatShopName(sell.sellPlace))
            if (sell.sellDate.isNotBlank()) InfoChip(text = sell.sellDate)
            if (sell.fees > 0) InfoChip(text = stringResource(R.string.sales_fees, formatPrice(sell.fees)))
        },
        trailing = {
            Text(
                text = formatPrice(sell.sellPrice),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            ProfitText(amount = sell.netProfit)
        },
    )
}

@Preview(showBackground = true)
@Composable
fun SellItemCardPreview() {
    SellItemCard(
        sell = SellUiModel(
            id = 1,
            name = "Nike Dunk Low Panda",
            picture = "",
            size = "42",
            buyPrice = 110.0,
            sellPrice = 150.0,
            sellDate = "12/10/2026",
            sellPlace = "VINTED"
        )
    )
}
