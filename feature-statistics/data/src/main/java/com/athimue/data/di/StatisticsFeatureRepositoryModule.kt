package com.athimue.data.di

import com.athimue.data.repositories.StatisticsSellRepositoryImpl
import com.athimue.domain.repositories.StatisticsSellRepository
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
}