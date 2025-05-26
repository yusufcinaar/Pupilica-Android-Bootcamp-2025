package com.example.foodorderapp.presentation.state

import com.example.foodorderapp.data.model.SepetYemekModel

data class CartState(
    val cartItems: List<SepetYemekModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalPrice: Int = 0
)
