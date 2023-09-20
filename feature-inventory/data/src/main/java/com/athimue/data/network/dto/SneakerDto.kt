package com.athimue.data.network.dto

import com.athimue.domain.models.Sneaker
import com.google.gson.annotations.SerializedName

data class SneakerDto(
    @SerializedName("id") var id: Long,
    @SerializedName("brand") var brand: String,
    @SerializedName("name") var name: String,
    @SerializedName("productType") var productType: String,
    @SerializedName("image") var image: String,
    @SerializedName("wantedSizes") var wantedSizes: List<String>,
)

fun SneakerDto.toSneaker() = Sneaker(
    id = id,
    name = name,
    picture = image,
    brand = brand
)

data class SearchResultDto(
    @SerializedName("results") var results: List<SneakerDto>,
)

