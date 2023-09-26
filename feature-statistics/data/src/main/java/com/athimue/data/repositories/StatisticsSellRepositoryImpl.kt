package com.athimue.data.repositories

import com.athimue.domain.models.StatisticsSell
import com.athimue.domain.models.repositories.StatisticsSellRepository
import database.dao.SellDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StatisticsSellRepositoryImpl @Inject constructor(
    private val sellDao: SellDao
) : StatisticsSellRepository {

    override fun getSells(): Flow<List<StatisticsSell>> =
        sellDao.getAll().map {
            it.map { sellEntity ->
                StatisticsSell(
                    id = sellEntity.id,
                    name = sellEntity.name,
                    picture = sellEntity.picture,
                    size = sellEntity.size,
                    quantity = sellEntity.quantity,
                    buyPrice = sellEntity.buyPrice,
                    sellPrice = sellEntity.sellPrice,
                    sellDate = sellEntity.sellDate,
                    sellPlace = sellEntity.sellPlace
                )
            }
        }
}