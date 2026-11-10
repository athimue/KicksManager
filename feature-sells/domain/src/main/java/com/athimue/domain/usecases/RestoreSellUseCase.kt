package com.athimue.domain.usecases

import com.athimue.domain.models.Sell

interface RestoreSellUseCase {
    /** Réenregistre une vente supprimée (bouton « Annuler »). */
    suspend fun restoreSell(sell: Sell)
}
