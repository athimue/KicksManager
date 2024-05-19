package com.athimue.data.di

import com.athimue.data.network.api.GoatApi
import com.athimue.data.network.api.GoatApi.Companion.GOAT_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrendsNetworkModule {
    @Provides
    @Singleton
    fun provideGoatApi(): GoatApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GOAT_API)
            .client(
                OkHttpClient
                    .Builder()
                    .build(),
            )
            .build()
            .create(GoatApi::class.java)
    }
}
