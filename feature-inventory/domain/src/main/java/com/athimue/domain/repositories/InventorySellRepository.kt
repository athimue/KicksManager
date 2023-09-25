package com.athimue.domain.repositories

import com.athimue.domain.models.InventorySell

interface InventorySellRepository {

    suspend fun addSell(sell: InventorySell)
}