package com.example.foodorderapp.data.repository

import com.example.foodorderapp.data.model.YemekModel

interface FoodRepository {
    suspend fun getFoods(): List<YemekModel>
    suspend fun getFoodsByCategory(categoryId: String): List<YemekModel>
}
