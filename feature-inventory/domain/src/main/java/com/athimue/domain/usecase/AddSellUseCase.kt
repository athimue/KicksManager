package com.athimue.domain.usecase

import com.athimue.domain.model.InventorySell
import com.athimue.domain.repository.InventoryRepository
import com.athimue.domain.repository.InventorySellRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AddSellUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
        private val inventorySellRepository: InventorySellRepository,
    ) {
        suspend operator fun invoke(
            inventoryItemId: Long,
            sellPrice: String,
            sellDate: String,
            sellPlace: String,
        ) {
            inventoryRepository.getInventory(inventoryItemId).first().let {
                inventorySellRepository.addSell(
                    InventorySell(
                        id = 0,
                        name = it.name,
                        picture = it.picture,
                        size = it.size,
                        quantity = it.quantity,
                        buyPrice = it.buyPrice,
                        sellPrice = sellPrice.toDouble(),
                        sellPlace = sellPlace,
                        sellDate = sellDate,
                    ),
                )
                inventoryRepository.deleteInventory(inventoryItemId)
            }
        }
    }
