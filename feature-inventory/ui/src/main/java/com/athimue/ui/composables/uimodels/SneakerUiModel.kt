package com.athimue.ui.composables.uimodels

import com.athimue.domain.models.Sneaker

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
