package com.example.foodorderapp.data.repository

import com.example.foodorderapp.data.model.YemekModel
import com.example.foodorderapp.data.remote.FoodApiService
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val api: FoodApiService
) : FoodRepository {

    override suspend fun getFoods(): List<YemekModel> {
        return api.getAllFoods().yemekler
    }

    override suspend fun getFoodsByCategory(categoryId: String): List<YemekModel> {
        val allFoods = getFoods()
        return when (categoryId) {
            "1" -> allFoods.filter { it.yemekAdi.contains("Köfte", ignoreCase = true) || 
                                    it.yemekAdi.contains("Izgara", ignoreCase = true) || 
                                    it.yemekAdi.contains("Et", ignoreCase = true) }
            "2" -> allFoods.filter { it.yemekAdi.contains("Çorba", ignoreCase = true) }
            "3" -> allFoods.filter { it.yemekAdi.contains("Makarna", ignoreCase = true) || 
                                    it.yemekAdi.contains("Pizza", ignoreCase = true) }
            "4" -> allFoods.filter { it.yemekAdi.contains("Salata", ignoreCase = true) || 
                                    it.yemekAdi.contains("Sebze", ignoreCase = true) }
            "5" -> allFoods.filter { it.yemekAdi.contains("Tatlı", ignoreCase = true) || 
                                    it.yemekAdi.contains("Baklava", ignoreCase = true) || 
                                    it.yemekAdi.contains("Sütlü", ignoreCase = true) ||
                                    it.yemekAdi.contains("Tiramisu", ignoreCase = true) }
            "6" -> allFoods.filter { it.yemekAdi.contains("İçecek", ignoreCase = true) || 
                                    it.yemekAdi.contains("Ayran", ignoreCase = true) || 
                                    it.yemekAdi.contains("Su", ignoreCase = true) }
                                    .filterNot { it.yemekAdi.contains("Tiramisu", ignoreCase = true) }
            else -> allFoods
        }
    }
}
