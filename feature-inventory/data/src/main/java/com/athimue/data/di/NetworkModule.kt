package com.athimue.data.di

import com.athimue.data.network.api.SellWeTheNewApi
import com.athimue.data.network.api.SellWeTheNewApi.Companion.SELL_WETHENEW_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt module that provides network related instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides an instance of [OkHttpClient].
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    /**
     * Provides an instance of [Retrofit] with [OkHttpClient] as client
     * and [BuildConfig.DEEZER_BASE_URL] as a base url.
     */
    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SELL_WETHENEW_API)
            .client(client)
            .build()


    /**
     * Provides an instance of [DeezerApi].
     */
    @Provides
    @Singleton
    fun provideDeezerApi(retrofit: Retrofit): SellWeTheNewApi =
        retrofit.create(SellWeTheNewApi::class.java)

}