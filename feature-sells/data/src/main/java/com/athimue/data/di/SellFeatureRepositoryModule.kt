package com.athimue.data.di

import com.athimue.data.repositories.SellRepositoryImpl
import com.athimue.domain.repositories.SellRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class SellFeatureRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideSellRepository(sellRepository: SellRepositoryImpl): SellRepository
}