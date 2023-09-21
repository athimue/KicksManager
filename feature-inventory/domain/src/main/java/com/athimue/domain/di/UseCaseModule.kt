package com.athimue.domain.di

import com.athimue.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSearchSneakerUseCase(searchSneakerUseCase: SearchSneakerUseCaseImpl): SearchSneakerUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetInventoryUseCase(getInventoryUseCase: GetInventoryUseCaseImpl): GetInventoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAddInventoryUseCase(addInventoryUseCase: AddInventoryUseCaseImpl): AddInventoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteInventoryUseCase(deleteInventoryUseCase: DeleteInventoryUseCaseImpl): DeleteInventoryUseCase

}