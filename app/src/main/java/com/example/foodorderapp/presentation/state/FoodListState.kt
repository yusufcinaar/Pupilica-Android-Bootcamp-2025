package com.example.foodorderapp.presentation.state

import com.example.foodorderapp.data.model.YemekModel

data class FoodListState(
    val foods: List<YemekModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
