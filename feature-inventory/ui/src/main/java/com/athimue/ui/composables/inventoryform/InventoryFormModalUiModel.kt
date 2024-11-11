package com.athimue.ui.composables.inventoryform

data class InventoryFormModalUiModel(
    val isLoading: Boolean = true,
    val id: Long? = null,
    val name: String = "",
    val size: String = "",
    val picture: String = "",
    val buyPrice: Double = 0.0,
    val buyPlace: String = "",
    val buyDate: String = "",
    val showDatePicker: Boolean = false,
    val showSneakerPicker: Boolean = false
)

fun InventoryFormModalUiModel.isCompleted(): Boolean {
    return name != "" && size != "" && picture != "" && buyPrice != 0.0 && buyPlace != "" && buyDate != ""
}