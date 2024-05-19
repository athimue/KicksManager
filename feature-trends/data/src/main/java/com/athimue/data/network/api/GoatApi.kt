package com.athimue.data.network.api

import com.athimue.data.network.dto.PopularResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoatApi {
  @GET("browse/collection_id/most-wanted-new")
  suspend fun getPopularSneakers(
    @Query("key") key: String = "key_XT7bjdbvjgECO5d8",
    @Query("page") page: Int = 1,
    @Query("num_results_per_page") resultPerPage: Int = 20,
    @Query("filters[web_groups]") group: String = "sneakers",
  ): Response<PopularResponseDto>

  companion object {
    const val GOAT_API = "https://ac.cnstrc.com"
  }
}
