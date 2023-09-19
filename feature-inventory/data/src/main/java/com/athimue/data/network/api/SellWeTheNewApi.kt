package com.athimue.data.network.api

import com.athimue.data.network.dto.SearchResultDto
import retrofit2.Response
import retrofit2.http.GET

interface SellWeTheNewApi {

    @GET("products?keywordSearch=jordan&skip=50&take=50")
    suspend fun getSneakers(): Response<SearchResultDto>

    companion object {
        const val SELL_WETHENEW_API = "https://api-sell.wethenew.com"
    }
}