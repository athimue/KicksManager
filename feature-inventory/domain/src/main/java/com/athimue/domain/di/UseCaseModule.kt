package com.athimue.domain.di

import com.athimue.domain.usecases.GetInventoryUseCase
import com.athimue.domain.usecases.GetInventoryUseCaseImpl
import com.athimue.domain.usecases.SearchSneakerUseCase
import com.athimue.domain.usecases.SearchSneakerUseCaseImpl
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

}