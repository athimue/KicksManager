package com.athimue.domain.di

import com.athimue.domain.models.usecases.GetSellsUseCase
import com.athimue.domain.models.usecases.GetSellsUseCaseImpl
import com.athimue.domain.models.usecases.GetSpecificSellsUseCase
import com.athimue.domain.models.usecases.GetSpecificSellsUseCaseImpl
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
    abstract fun bindGetSellsUseCase(getSellsUseCase: GetSellsUseCaseImpl): GetSellsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSpecificSellsUseCase(getSpecificSellsUseCase: GetSpecificSellsUseCaseImpl): GetSpecificSellsUseCase
}