package com.athimue.data.di

import com.athimue.data.source.InventoryStockSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import database.source.InventoryStockSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class InventoryStockModule {
    @Binds
    @Singleton
    abstract fun bindInventoryStockSource(inventoryStockSource: InventoryStockSourceImpl): InventoryStockSource
}
