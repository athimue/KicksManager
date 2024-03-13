package com.athimue.data.di

import com.athimue.data.repositories.InventoryRepositoryImpl
import com.athimue.data.repositories.InventorySellRepositoryImpl
import com.athimue.data.repositories.SearchRepositoryImpl
import com.athimue.domain.repository.InventoryRepository
import com.athimue.domain.repository.InventorySellRepository
import com.athimue.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    @ViewModelScoped
    abstract fun provideInventoryRepository(inventoryRepositoryImpl: InventoryRepositoryImpl): InventoryRepository

    @Binds
    @ViewModelScoped
    abstract fun provideInventorySellRepository(inventorySellRepository: InventorySellRepositoryImpl): InventorySellRepository
}