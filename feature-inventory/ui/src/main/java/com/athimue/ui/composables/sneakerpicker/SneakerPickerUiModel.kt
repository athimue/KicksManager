package com.athimue.ui.composables.sneakerpicker

import com.athimue.domain.model.Sneaker

data class SneakerUiModel(
    val name: String,
    val picture: String,
    val brand: String
)

fun Sneaker.toSneakerUiModel() = SneakerUiModel(
    name = name,
    picture = picture,
    brand = brand,
)
