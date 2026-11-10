package com.athimue.data.di

import com.athimue.data.repositories.StatisticsSellRepositoryImpl
import com.athimue.data.repositories.StatisticsStockRepositoryImpl
import com.athimue.domain.repositories.StatisticsSellRepository
import com.athimue.domain.repositories.StatisticsStockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class StatisticsFeatureRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideSellRepository(sellRepository: StatisticsSellRepositoryImpl): StatisticsSellRepository

    @Binds
    @ViewModelScoped
    abstract fun provideStockRepository(stockRepository: StatisticsStockRepositoryImpl): StatisticsStockRepository
}