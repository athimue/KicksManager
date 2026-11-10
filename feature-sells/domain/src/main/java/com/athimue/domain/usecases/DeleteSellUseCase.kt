package com.athimue.domain.usecases

import com.athimue.domain.models.Sell

interface DeleteSellUseCase {
    /** Supprime la vente et la renvoie, pour pouvoir annuler la suppression. */
    suspend fun deleteSell(sellId: Long): Sell?
}
