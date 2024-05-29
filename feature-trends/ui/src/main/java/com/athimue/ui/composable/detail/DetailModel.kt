package com.athimue.ui.composable.detail

import kotlinx.serialization.Serializable

@Serializable
data class DetailModel(
    val sku: String,
    val name: String,
    val picture: String,
)
