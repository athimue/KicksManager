package com.athimue.data.repositories

import com.athimue.domain.model.InventorySell
import com.athimue.domain.repository.InventorySellRepository
import database.dao.SellDao
import database.entity.SellEntity
import javax.inject.Inject

class InventorySellRepositoryImpl @Inject constructor(
    private val sellDao: SellDao
) : InventorySellRepository {

    override suspend fun addSell(sell: InventorySell) {
        sellDao.insert(
            SellEntity(
                name = sell.name,
                picture = sell.picture,
                size = sell.size,
                quantity = sell.quantity,
                buyPrice = sell.buyPrice,
                sellPrice = sell.sellPrice,
                sellPlace = sell.sellPlace,
                sellDate = sell.sellDate,
            )
        )
    }
}