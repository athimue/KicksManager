package com.athimue.data.repositories

import com.athimue.domain.models.Sell
import com.athimue.domain.repositories.SellRepository
import database.dao.SellDao
import database.entity.SellEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SellRepositoryImpl
    @Inject
    constructor(
        private val sellDao: SellDao,
    ) : SellRepository {
        override suspend fun deleteSell(sellId: Long) {
            sellDao.delete(sellId)
        }

        override suspend fun restoreSell(sell: Sell) {
            sellDao.insert(
                SellEntity(
                    id = sell.id,
                    name = sell.name,
                    picture = sell.picture,
                    size = sell.size,
                    quantity = sell.quantity,
                    buyPrice = sell.buyPrice,
                    sellPrice = sell.sellPrice,
                    sellPlace = sell.sellPlace,
                    sellDate = sell.sellDate,
                    fees = sell.fees,
                ),
            )
        }

        override fun getSells(): Flow<List<Sell>> =
            sellDao.getAll().map {
                it.map { sellEntity ->
                    Sell(
                        id = sellEntity.id,
                        name = sellEntity.name,
                        picture = sellEntity.picture,
                        size = sellEntity.size,
                        quantity = sellEntity.quantity,
                        buyPrice = sellEntity.buyPrice,
                        sellPrice = sellEntity.sellPrice,
                        sellDate = sellEntity.sellDate,
                        sellPlace = sellEntity.sellPlace,
                        fees = sellEntity.fees,
                    )
                }
            }
    }
