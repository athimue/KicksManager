package com.athimue.domain.usecases

import com.athimue.domain.models.InventorySell
import com.athimue.domain.repositories.InventoryRepository
import com.athimue.domain.repositories.InventorySellRepository
import javax.inject.Inject

class AddSellUseCaseImpl @Inject constructor(
    private val inventoryRepository: InventoryRepository,
    private val inventorySellRepository: InventorySellRepository
) : AddSellUseCase {

    override suspend fun invoke(
        inventoryItemId: Long,
        sellPrice: String,
        sellDate: String,
        sellPlace: String
    ) {
        inventoryRepository.getInventory(inventoryItemId).collect {
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
                )
            )
        }
    }
}