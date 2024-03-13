package com.athimue.domain.repository

import com.athimue.domain.model.InventorySell

interface InventorySellRepository {

    suspend fun addSell(sell: InventorySell)
}