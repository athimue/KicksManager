package com.athimue.data.di

import com.athimue.data.repositories.TrendsRepositoryImpl
import com.athimue.domain.repository.TrendsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class TrendsRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun provideTrendsRepository(trendsRepositoryImpl: TrendsRepositoryImpl): TrendsRepository
}
