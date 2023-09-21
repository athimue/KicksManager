package com.athimue.ui.composables.sells

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SellViewModel @Inject constructor(

) : ViewModel() {
    var uiState = MutableStateFlow(SellUiState())

    init {

    }

    fun deleteSell(sellId: Long) {

    }
}