package com.athimue.domain.di

import com.athimue.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class SellsUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetSellsUseCase(getSellsUseCase: GetSellsUseCaseImpl): GetSellsUseCase

}