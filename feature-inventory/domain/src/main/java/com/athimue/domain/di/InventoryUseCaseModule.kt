package com.athimue.domain.di

import com.athimue.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class InventoryUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSearchSneakerUseCase(searchSneakerUseCase: SearchSneakerUseCaseImpl): SearchSneakerUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetInventoryUseCase(getInventoryUseCase: GetInventoryUseCaseImpl): GetInventoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAddInventoryUseCase(addInventoryUseCase: AddOrUpdateOrUpdateInventoryUseCaseImpl): AddOrUpdateInventoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteInventoryUseCase(deleteInventoryUseCase: DeleteInventoryUseCaseImpl): DeleteInventoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAddSellUseCase(addSellUseCase: AddSellUseCaseImpl): AddSellUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetInventoryItemUseCase(getInventoryItemUseCase: GetInventoryItemUseCaseImpl): GetInventoryItemUseCase


}