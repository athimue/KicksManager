package com.athimue.data.network.dto

import com.athimue.domain.model.PopularSneaker
import com.google.gson.annotations.SerializedName

data class PopularResponseDto(
  val response: PopularResultDto
)

data class PopularResultDto(
  val results: List<PopularSneakerDto>
)

data class PopularSneakerDto(
  @SerializedName("matched_terms")
  val matchedTerms: List<Any?>,
  val labels: Map<String, Any>,
  val data: PopularSneakerDataDto,
  val value: String,
  @SerializedName("is_slotted")
  val isSlotted: Boolean,
)

data class PopularSneakerDataDto(
  val id: String,
  val sku: String,
  val slug: String,
  val color: String,
  val category: String,
  @SerializedName("image_url")
  val imageUrl: String,
  @SerializedName("release_date")
  val releaseDate: Long,
  @SerializedName("product_type")
  val productType: String,
  @SerializedName("release_date_year")
  val releaseDateYear: Long,
  @SerializedName("retail_price_cents")
  val retailPriceCents: Long,
  @SerializedName("retail_price_cents_eur")
  val retailPriceCentsEur: Long,
  @SerializedName("retail_price_cents_myr")
  val retailPriceCentsMyr: Long,
  @SerializedName("retail_price_cents_jpy")
  val retailPriceCentsJpy: Long,
  @SerializedName("retail_price_cents_sgd")
  val retailPriceCentsSgd: Long,
  @SerializedName("retail_price_cents_krw")
  val retailPriceCentsKrw: Long,
  @SerializedName("retail_price_cents_aud")
  val retailPriceCentsAud: Long,
  @SerializedName("retail_price_cents_twd")
  val retailPriceCentsTwd: Long,
  @SerializedName("retail_price_cents_cad")
  val retailPriceCentsCad: Long,
  @SerializedName("retail_price_cents_hkd")
  val retailPriceCentsHkd: Long,
  @SerializedName("retail_price_cents_cny")
  val retailPriceCentsCny: Long,
  @SerializedName("retail_price_cents_gbp")
  val retailPriceCentsGbp: Long,
  @SerializedName("variation_id")
  val variationId: String,
  @SerializedName("discount_tag")
  val discountTag: String,
  @SerializedName("box_condition")
  val boxCondition: String,
  @SerializedName("product_condition")
  val productCondition: String,
  @SerializedName("lowest_price_cents")
  val lowestPriceCents: Long,
  @SerializedName("lowest_price_cents_krw")
  val lowestPriceCentsKrw: Long,
  @SerializedName("lowest_price_cents_myr")
  val lowestPriceCentsMyr: Long,
  @SerializedName("lowest_price_cents_sgd")
  val lowestPriceCentsSgd: Long,
  @SerializedName("lowest_price_cents_gbp")
  val lowestPriceCentsGbp: Long,
  @SerializedName("lowest_price_cents_hkd")
  val lowestPriceCentsHkd: Long,
  @SerializedName("lowest_price_cents_cad")
  val lowestPriceCentsCad: Long,
  @SerializedName("lowest_price_cents_twd")
  val lowestPriceCentsTwd: Long,
  @SerializedName("lowest_price_cents_eur")
  val lowestPriceCentsEur: Long,
  @SerializedName("lowest_price_cents_jpy")
  val lowestPriceCentsJpy: Long,
  @SerializedName("lowest_price_cents_cny")
  val lowestPriceCentsCny: Long,
  @SerializedName("lowest_price_cents_aud")
  val lowestPriceCentsAud: Long,
  @SerializedName("count_for_product_condition")
  val countForProductCondition: Long,
  @SerializedName("instant_ship_lowest_price_cents")
  val instantShipLowestPriceCents: Long,
  @SerializedName("instant_ship_lowest_price_cents_cad")
  val instantShipLowestPriceCentsCad: Long,
  @SerializedName("instant_ship_lowest_price_cents_krw")
  val instantShipLowestPriceCentsKrw: Long,
  @SerializedName("instant_ship_lowest_price_cents_aud")
  val instantShipLowestPriceCentsAud: Long,
  @SerializedName("instant_ship_lowest_price_cents_cny")
  val instantShipLowestPriceCentsCny: Long,
  @SerializedName("instant_ship_lowest_price_cents_jpy")
  val instantShipLowestPriceCentsJpy: Long,
  @SerializedName("instant_ship_lowest_price_cents_hkd")
  val instantShipLowestPriceCentsHkd: Long,
  @SerializedName("instant_ship_lowest_price_cents_eur")
  val instantShipLowestPriceCentsEur: Long,
  @SerializedName("instant_ship_lowest_price_cents_gbp")
  val instantShipLowestPriceCentsGbp: Long,
  @SerializedName("instant_ship_lowest_price_cents_sgd")
  val instantShipLowestPriceCentsSgd: Long,
  @SerializedName("instant_ship_lowest_price_cents_myr")
  val instantShipLowestPriceCentsMyr: Long,
  @SerializedName("instant_ship_lowest_price_cents_twd")
  val instantShipLowestPriceCentsTwd: Long,
)

fun PopularSneakerDto.toPopularSneaker() = PopularSneaker(
  id = data.id,
  name = value,
  picture = data.imageUrl,
)