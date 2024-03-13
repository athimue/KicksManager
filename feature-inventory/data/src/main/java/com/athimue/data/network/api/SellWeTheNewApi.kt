package com.athimue.data.network.api

import com.athimue.data.network.dto.SearchResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SellWeTheNewApi {
    @GET("products")
    suspend fun getSneakers(
        @Query("keywordSearch") query: String,
    ): Response<SearchResultDto>

    companion object {
        const val SELL_WETHENEW_API = "https://api-sell.wethenew.com"
    }
}
