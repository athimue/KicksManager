package com.athimue.domain.di

import com.athimue.domain.usecases.GetSellsStatisticsUseCase
import com.athimue.domain.usecases.GetSellsStatisticsUseCaseImpl
import com.athimue.domain.usecases.GetSpecificSellsUseCase
import com.athimue.domain.usecases.GetSpecificSellsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class StatisticsUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetSellsUseCase(getSellsUseCase: GetSellsStatisticsUseCaseImpl): GetSellsStatisticsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSpecificSellsUseCase(getSpecificSellsUseCase: GetSpecificSellsUseCaseImpl): GetSpecificSellsUseCase
}